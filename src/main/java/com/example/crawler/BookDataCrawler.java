package com.example.crawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
public class BookDataCrawler implements DataCrawler{

	@Value("${apiUrl}")
	private String apiUrl;

	@Value("${authKey}")
	private String authKey;

	private int pageNo;

	String api = "https://data4library.kr/api/libSrch?authKey=55db267f8f05b0bf8e23e8d3f65bb67d206a6b5ce24f5e0ee4625bcf36e4e2bb";

	public BookDataCrawler() {
		this.pageNo = 1;
	}

	@Override
	public Document getCrawledData(int pageNo) {
		this.pageNo = pageNo;

		Document doc = null;
		int pageSize = 10;
		try {
			String url = apiUrl + authKey +"&pageNo="+pageNo+"&pageSize="+pageSize;
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.pageNo++;
		return doc;
	}

	@Override
	public Map<String, String> extractData(Elements libCodes, Elements libNames) {
		Map<String, String> libMap = new HashMap<>();

		for (int i = 0; i < libCodes.size(); i++) {
			String libCode = libCodes.get(i).text();
			String libName = libNames.get(i).text();
			libMap.put(libCode, libName);
		}

		return libMap;
	}
}