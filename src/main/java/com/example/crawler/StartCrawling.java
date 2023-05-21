package com.example.crawler;

public class StartCrawling {

	public static void main(String[] args) throws Exception {
		SSLUtil.trustAllCertificates();
		DataCrawler crawler = new BookDataCrawler();
		CSVMaker csvMaker = new CSVMaker(crawler);
		csvMaker.generateCSV();
	}

}
