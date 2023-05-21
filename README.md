# Crawler

<h2>목적</h2>
도서관 코드를 통해 도서관의 도서 소장 여부를 확인하기 전, 도서관 코드와 도서관 이름을 크롤링  

<h3>활용한 API</h3>
정보 도서나루 공개 API의 **정보공개 도서관API**  
활용 API 예시 : http://data4library.kr/api/libSrch?authKey=[발급받은키]&pageNo=1&pageSize=10  

<h3>코드 구성</h3>
(interface) DataCrawler : getCrawledData,() extractData(). 
(class) BookDataCrawler : DataCrawler를 구현한 클래스. 
(class) SSLUtil : 공개 도서나루 Api에 요청을 보낼 때, 인증서 신뢰를 시키게 하기 위한 클래스 (보완 필요)  
(class) CSVMaker : BookDataCrawler의 extractData를 활용하여 크롤링 한 이후, CSV 파일 생성  
