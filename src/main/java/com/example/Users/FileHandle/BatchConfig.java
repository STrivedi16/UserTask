package com.example.Users.FileHandle;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

//@Configuration
//@EnableBatchProcessing
//public class BatchConfig {
//
//	@Autowired
//	private DataSource dataSource;
//	
//	@Autowired
//	private JobBuilderFactory builderFactory;
//	
//	@Autowired
//	private StepBuilderFactory stepBuilderFactory;
//	
//	
//	@Bean
//	public FlatFileItemReader<UserFileCsv> reader()
//	{
//		FlatFileItemReader<UserFileCsv> reader=new FlatFileItemReader<>();
//		
//		reader.setResource(new ClassPathResource("BasicUser.csv"));
//		reader.setLineMapper(getLineMapper());
//		reader.setLinesToSkip(1);
//		
//		return reader;
//	}
//
//
//	private LineMapper<UserFileCsv> getLineMapper() {
//		DefaultLineMapper<UserFileCsv>mapper=new DefaultLineMapper<>();
//		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
//		lineTokenizer.setNames(new String[] {"Id","Name","City","State"});
//		lineTokenizer.setIncludedFields(new int[] {0,1,2,3});
//			
//		BeanWrapperFieldSetMapper<UserFileCsv> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
//		fieldSetMapper.setTargetType(UserFileCsv.class);
//		
//		
//		
//		mapper.setLineTokenizer(lineTokenizer);
//		mapper.setFieldSetMapper(fieldSetMapper);
//		return mapper;
//	}
//	
//	@Bean
//	public UserFileCsvProcessesor csvProcessesor()
//	{
//		return new UserFileCsvProcessesor();
//	}
//	
//	
//}
