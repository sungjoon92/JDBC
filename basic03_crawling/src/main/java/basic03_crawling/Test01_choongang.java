package basic03_crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Test01_choongang {

	public static void main(String[] args) {

//		● 크롤링(crawling), 스크레핑(scraping)
//	     - 웹 페이지를 그대로 가져와서 거기서 데이터를 추출해 내는 행위다
//	     - 로봇 배제 표준(robots.txt) : 웹 사이트에 로봇이 접근하는 것을 방지하기 위한 규약
//	       https://www.daum.net/robots.txt
//	       http://world.kbs.co.kr/robots.txt
//	       http://ytn.co.kr/robots.txt
//	       
//	   ● jsoup 라이브러리
//	     - 자바로 만들어진 HTML parser다.
//	     - Jsoup는 DOM 구조를 추적하거나 CSS 선택자를 사용하여 데이터를 찾아 추출할 수 있다.
//	     - 다운로드
//	       https://jsoup.org/download -> jsoup-1.15.4.jar
//	 
//	   ● jsoup 라이브러리 핵심 클래스           
//	     - Document 클래스 : 연결해서 얻어온 HTML전체 문서
//	     - Elements 클래스 : Element가 모인 자료형
//	     - Element  클래스 : Document의 HTML요소
//
//	   ● [Dynamic Web Project 생성]
//	     - 프로젝트 명 : basic03_crawling
//	     - /src/main/webapp/WEB-INF/lib/jsoup-1.15.4.jar 붙여넣기
//	     
//	   ● 외부라이브러리 추가하면 해당 프로젝트 새로고침
//	     - 해당프로젝트 우클릭 F5
//	     - 해당프로젝트 우클릭 Validate
//	     - 메뉴  Project -> Clean

		// 참조 http://pretyimo.cafe24.com/lectureRead.do?lectureno=333
		// 개인 혹은 단체에서 필요한 데이터가 있는 웹(web)페이지의 구조를 분석하고 파악하여 긁어온다
		// 예) https://www.daum.net/robots.txt
		// https://www.naver.com/robots.txt
		// http://world.kbs.co.kr/robots.txt
		// http://ytn.co.kr/robots.txt

		// https://www.choongang.co.kr/

		// 중앙정보처리학원의 공지사항 웹페이지 소스 가져오기
		try {

			String URL = "https://www.egovframe.go.kr/home/ntt/nttList.do?searchKey=&searchValue=&menuNo=74&bbsId=6&pagerOffset=0";
			// 웹페이지 소스 가져오기
			Document doc = Jsoup.connect(URL).get();
			System.out.println(doc);

		} catch (Exception e) {
			System.out.println("크롤링 실패: " + e);
		} // try ~ catch end

	}// main end
}// class end
