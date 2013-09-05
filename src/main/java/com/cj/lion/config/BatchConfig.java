package com.cj.lion.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cj.domain.received.ImageReceivedMessage;
import com.cj.lion.domain.StudentInfo;
import com.cj.lion.repository.StudentInfoRepository;
import com.cj.repository.received.ImageReceivedMessageRepository;

@Slf4j
@Configuration
@EnableBatchProcessing
@EnableAsync
@EnableScheduling
public class BatchConfig {
	
	private static final String FOLDER_NAME="studentPic";
	
	@Autowired
	private JobRepository jobRepository;
	
    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private ImageReceivedMessageRepository imageReceivedMessageRepository;
    
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public Job job() {
    	SimpleJob simpleJob=new SimpleJob();
    	simpleJob.setName("readPicFromWechat");
    	simpleJob.addStep(step());
    	simpleJob.setJobRepository(jobRepository);
    	simpleJob.setRestartable(true);
        return simpleJob;
    }

    @Bean
    public Step step() {
        return steps.get("readPicFromWechat")
            .<StudentInfo,  ImmutablePair<Path,InputStream>> chunk(10)
            .reader(itemReader())
            .processor(itemProcessor())
            .writer(itemWriter())
            .build();
    }

    @Bean
    public ItemReader<StudentInfo> itemReader(){
    	JpaPagingItemReader<StudentInfo> itemReader = new JpaPagingItemReader<StudentInfo>();
    	itemReader.setEntityManagerFactory(entityManagerFactory);
    	itemReader.setQueryString("SELECT si FROM StudentInfo si WHERE si.stored=false");
    	return itemReader;
    }
    
    @Bean
    public  ItemProcessor<StudentInfo, ImmutablePair<Path,InputStream>> itemProcessor(){
    	return new ItemProcessor<StudentInfo, ImmutablePair<Path,InputStream>>(){
			@Override
			public ImmutablePair<Path,InputStream> process(StudentInfo studentInfo) throws Exception {
				List<ImageReceivedMessage> imageReceivedMessages=imageReceivedMessageRepository.findByOtherOrderByCreateTimeDesc(studentInfo.getWechatId());
				if(imageReceivedMessages.size()==0){
					return null;
				}
				String url = imageReceivedMessages.get(0).getPicUrl();
				InputStream inputStream=new URL(url).openStream();
				
				studentInfo.setStored(true);
				studentInfoRepository.save(studentInfo);
				return ImmutablePair.of(getStoredLink(studentInfo.getWechatId()),inputStream);
			}
    	};
    }
    
    @Bean
    public ItemWriter< ImmutablePair<Path,InputStream>> itemWriter(){
    	return new ItemWriter<ImmutablePair<Path,InputStream>>(){
			@Override
			public void write(List<? extends  ImmutablePair<Path,InputStream>> items)
					throws Exception {
				for( ImmutablePair<Path,InputStream> pair :items){
					Files.copy(pair.getRight(), pair.getLeft(), StandardCopyOption.REPLACE_EXISTING);
				}
			}
    	};
    }


    
    private Path getStoredLink(String wechatId) throws IOException{
		SimpleDateFormat dateformat= new SimpleDateFormat("yyyyMMdd");
		String dateString = dateformat.format(new Date());
		String pathString=System.getProperty("user.home")+File.separator+FOLDER_NAME;
		File folder=new File(pathString);
		if(!folder.exists()){
			folder.mkdir();
		}
		pathString=pathString+File.separator+dateString;
		folder=new File(pathString);
		if(!folder.exists()){
			folder.mkdir();
		}
		pathString=pathString+File.separator+wechatId+".jpg";
		File file=new File(pathString);
		if(!file.exists()){
			file.createNewFile();
		}
		
		Path path=file.toPath();
		return path;
	}
}
