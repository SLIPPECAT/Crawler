package com.example.crawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;


public class BookDataCrawler implements DataCrawler{

	String apiUrl = "https://data4library.kr/api/libSrch?authKey=";

	@Value("${authKey}")
	String authKey;


	private int pageNo;

	public BookDataCrawler() {
		this.pageNo = 1;
	}

	@Override
	public Document getCrawledData(int pageNo) {
		this.pageNo = pageNo;

		Document doc = null;
		int pageSize = 10;
		try {
			String url = apiUrl + authKey+"&pageNo="+pageNo+"&pageSize="+pageSize;
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.pageNo++;
		return doc;
	}

	@Override
	public Map<String, String> extractData(Elements libNames, Elements libCodes) {
		Map<String, String> libMap = new HashMap<>();

		for (int i = 0; i < libNames.size(); i++) {
			String libName = libNames.get(i).text();
			String libCode = libCodes.get(i).text();
			libMap.put(libCode, libName);
		}

		return libMap;
	}
}