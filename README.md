# Crawler

<h2>목적</h2>
도서관 코드를 통해 도서관의 도서 소장 여부를 확인하기 전, 도서관 코드와 도서관 이름을 크롤링  

<h3>Jsoup 라이브러리, openCsv 라이브러리 활용</h3>

<h3>활용한 API</h3>
정보 도서나루 공개 API의 **정보공개 도서관API**<br>  
활용 API 예시 : http://data4library.kr/api/libSrch?authKey=[발급받은키]&pageNo=1&pageSize=10<br>  

<h3>클래스 구성</h3>
(class) AppConfig : CSVMaker, BookDataCralwer를 빈 등록, Value값을 불러옴  <br>
(interface) DataCrawler : getCrawledData,() extractData(). <br>  
(class) BookDataCrawler : DataCrawler를 구현한 클래스. <br>  
(class) SSLUtil : 공개 도서나루 Api에 요청을 보낼 때, 인증서 신뢰를 시키게 하기 위한 클래스 (보완 필요)  <br>  
(class) CSVMaker : BookDataCrawler의 extractData를 활용하여 크롤링 한 이후, CSV 파일 생성  <br>  
(class) CSVMaker2 : BookDataCrawler의 extractData를 활용하여 전체 데이터에 대해 크롤링 이후, CSV 파일 생성  <br>  
(class) StartCrawling : 크롤링 실행 <br>
