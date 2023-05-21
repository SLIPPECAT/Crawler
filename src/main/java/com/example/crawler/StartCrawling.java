package com.example.crawler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
@RequiredArgsConstructor
public class StartCrawling {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		// BookDataCrawler 빈 가져오기
		BookDataCrawler crawler = context.getBean(BookDataCrawler.class);
		log.info(crawler.getApiUrl() + crawler.getAuthKey());

		SSLUtil.trustAllCertificates();
		// CSVMaker 빈 가져오기
//		CSVMaker csvMaker = context.getBean(CSVMaker.class);
//		csvMaker.generateCSV();
		CSVMaker2 csvMaker = context.getBean(CSVMaker2.class);
		csvMaker.generateCSV();

		// Spring 컨테이너 종료
		((AnnotationConfigApplicationContext) context).close();
	}
//	public static void main(String[] args) throws Exception {
//
//		SSLUtil.trustAllCertificates();
//		DataCrawler crawler = new BookDataCrawler();
//		System.out.println(crawler.getCrawledData(1).text());
////		CSVMaker csvMaker = new CSVMaker(crawler);
////		csvMaker.generateCSV();
//	}
}
