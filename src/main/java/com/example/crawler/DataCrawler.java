package com.example.crawler;

import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public interface DataCrawler {

	Document getCrawledData(int PageNo);
	Map<String, String> extractData(Elements libNames, Elements libCodes);
}
