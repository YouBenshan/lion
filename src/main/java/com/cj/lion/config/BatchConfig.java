package com.cj.lion.config;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cj.domain.received.ImageReceivedMessage;
import com.cj.lion.domain.StudentInfo;
import com.cj.lion.repository.StudentInfoRepository;
import com.cj.repository.received.ImageReceivedMessageRepository;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	private static final String FOLDER_NAME="studentPic";
	
	private File folder;
	
	@PostConstruct
	public void createFolder(){
		folder=new File(System.getProperty("user.home")+File.separator+FOLDER_NAME);
		folder.mkdir();
	}

    @Autowired
    private JobBuilderFactory jobs;

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
        return jobs.get("readPicFromWechat").start(step()).build();
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
				String link=folder.getAbsolutePath()+File.separator+studentInfo.getWechatId()+".jpg";
				log.info(link);
				File file=new File(link);
				if(!file.exists()){
					file.createNewFile();
				}
				
				Path path=file.toPath();
				log.info(path.toString());
				studentInfo.setStored(true);
				studentInfoRepository.save(studentInfo);
				return ImmutablePair.of(path,inputStream);
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
    

}
