package com.example.crawler;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:application.yml"})
public class AppConfig {

	@Bean
	public CSVMaker csvMaker(BookDataCrawler crawler) {
		return new CSVMaker(crawler);
	}

	@Bean
	public BookDataCrawler bookDataCrawler(){
		return new BookDataCrawler();
	}

}

