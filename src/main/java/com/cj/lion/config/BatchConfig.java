package com.cj.lion.config;

import java.net.URL;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cj.domain.received.ImageReceivedMessage;
import com.cj.lion.domain.StudentInfo;
import com.cj.repository.received.ImageReceivedMessageRepository;
import com.google.common.io.ByteStreams;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private ImageReceivedMessageRepository imageReceivedMessageRepository;
    
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    @Bean
    public Job job() {
        return jobs.get("readPicFromWechat").start(step()).build();
    }

    @Bean
    public Step step() {
        return steps.get("readPicFromWechat")
            .<StudentInfo, StudentInfo> chunk(1)
            .reader(itemReader())
            .processor(itemProcessor())
            .writer(itemWriter())
            .build();
    }

    @Bean
    public ItemReader<StudentInfo> itemReader(){
    	JpaPagingItemReader<StudentInfo> itemReader = new JpaPagingItemReader<StudentInfo>();
    	itemReader.setEntityManagerFactory(entityManagerFactory);
    	itemReader.setPageSize(5);
    	itemReader.setQueryString("SELECT si FROM StudentInfo si");
    	return itemReader;
    }
    
    @Bean
    public  ItemProcessor<StudentInfo, StudentInfo> itemProcessor(){
    	return new ItemProcessor<StudentInfo, StudentInfo>(){

			@Override
			public StudentInfo process(StudentInfo studentInfo) throws Exception {
				ImageReceivedMessage imageReceivedMessage=imageReceivedMessageRepository.findByOther(studentInfo.getWechatId());
				String url = imageReceivedMessage.getPicUrl();
				studentInfo.setPic(ByteStreams.toByteArray(new URL(url).openStream()));
				return studentInfo;
			}
    	};
    }
    
    @Bean
    public ItemWriter<StudentInfo> itemWriter(){
    	JpaItemWriter itemWriter= new JpaItemWriter<StudentInfo>();
    	itemWriter.setEntityManagerFactory(entityManagerFactory);
    	return itemWriter;
    }

}
