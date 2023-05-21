package com.example.crawler;

import com.opencsv.CSVWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class CSVMaker {

	private final DataCrawler crawler;

	public void generateCSV() {
		log.info("크롤링 작업을 시작합니다.");
		try {
			int pageNo = 1;
			Document doc = crawler.getCrawledData(pageNo);

			while (doc != null) {
				String xmlData = String.valueOf(doc);

				Document document = Jsoup.parse(xmlData, "", Parser.xmlParser());

				Elements libNames = document.select("libName");
				Elements libCodes = document.select("libCode");

				Map<String, String> libMap = crawler.extractData(libNames, libCodes);

				// CSV 파일 경로
				String csvDirectory = "/Users/mac/downloads/";
				String csvFileName = "도서관 코드, 이름 크롤링 file";
				String csvFilePath = csvDirectory + csvFileName + ".csv";

				int fileCount = 1;

				while (Files.exists(Path.of(csvFilePath))) {
					String newFileName = csvFileName + "(" + fileCount + ")";
					csvFilePath = csvDirectory + newFileName + ".csv";
					fileCount++;
				}

				try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
					// CSV 파일에 헤더 작성
					writer.writeNext(new String[]{"libCode", "libName"});

					// 데이터 작성
					for (Map.Entry<String, String> entry : libMap.entrySet()) {
						String libCode = entry.getKey();
						String libName = entry.getValue();
						writer.writeNext(new String[]{libCode, libName});
					}
					log.info(csvFilePath);
				} catch (IOException e) {
					log.info("CSV 파일 생성 중 오류가 발생했습니다: " + e.getMessage());
				}
				pageNo++;
				doc = crawler.getCrawledData(pageNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}