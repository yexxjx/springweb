package example.day12.웹크롤링;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class CrawlingService {

    // [1] Jsoup 이용한 특정 url html 정보 수집
    public List<String> test1(){
        List<String> list=new ArrayList<>(); // 여러 개 문자열 저장 리스트
        // 1) 크롤링할 URL 웹페이지 주소
        String url="https://www.karnews.or.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm";
        // 2) 크롤링할 url 요청하여 HTML 전체를 가져온다. Jsoup.connect(주소).get();
        // import org.jsoup.nodes.Document;
        try {
            Document document = Jsoup.connect(url).get(); // 외부 통신은 일반 예외가 주로 발생해서 try-catch 쓰기
            // System.out.println("document = " + document);
            // 3) 특정한 마크업/요소 식별자, document.select("식별자");
            Elements elements = document.select(".titles>a"); // 클래스가 titles인 마크업 아래에 <a> 가져온다.
            System.out.println("elements = " + elements);
            // 4) 여러 개 가져왔다면 반복문 이용한 요소/마크업(Element) 1개씩 순회
            for(Element element : elements){
                String title = element.text(); // vs innerHTML 비슷하게 마크업 사이 텍스트를 반환 <a> 여기에 있는 거 </a>
                // 만약에 텍스트가 존재하면 리스트에 담기
                if(title.isBlank()){continue;} // 반복문 위로 이동
                else{list.add(title);}
            }
        } catch(Exception e){System.out.println(e);}
        return list;
    }

    // [2]
    public List<Map<String, Object>> test2(){
        List<Map<String, Object>> list=new ArrayList<>(); // 책 정보들을 담는 리스트 선언
        try {
            for (int page = 1; page <= 3; page++) { // page는 1부터 3까지
                // 1) 크롤링 URL 주소 (예스24 베스트셀러 일별) ++ 반복문 이용하여 페이지 번호 여러 개 요청
                String url = "https://www.yes24.com/product/category/daybestseller";
                url += "?categoryNumber=001"; // 베스트셀러 카테고리 번호
                url += "pageNumber=1" + page; // 크롤링할 페이지 번호 <page 변수로 활용>
                url += "pageSize=24"; // 페이지당 제품 수
                // 2) url 연결
                Document document = Jsoup.connect(url).get();
                // 3) 식별자, 가져올 텍스트가 위치한 식별자와 상위 식별자 1~2개 같이 선택하기 <중복 배제>
                Elements nameList = document.select(".info_name .gd_name");// 책 이름 .info_name .gd_name
                Elements priceList = document.select(".info_price .txt_num .yes_b");// 책 가격 .info_price .txt_num .yes_b
                Elements imageList = document.select(".img_bdr .lazy");// 책 이미지 .img_bdr. lazy
                // 4) 반복문을 이용하여 여러 개 요소/마크업들을 도서별 MAP 구성하여 List 저장
                for(int index=0; index<nameList.size(); index++){ // 첫번째 도서부터 마지막 도서까지
                    String name = nameList.get(index).text(); // index번째 요소의 책 이름(텍스트) 반환
                    String price = priceList.get(index).text();
                    // text(): 마크업 사이 텍스트 반환, attr(속성명): 해당 속성명의 속성값 가져옴
                    String image = imageList.get(index).attr("data-original");
                    // 5) DTO/MAP 구성
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name); map.put("price", price); map.put("image", image);
                    // 6)
                    list.add(map);
                }
            }
        } catch (Exception e){System.out.println(e);}
        return list;
    }

    // [3]
    public Map<String, Object> test3(){
        // 1) 크롬 드라이버 설치
        WebDriverManager.chromedriver().setup();
        // 2) 크롤링할 웹 주소
        String url= "https://weather.daum.net/";
        // 3) 크롬 드라이버 객체 생성
            // * 드라이버 옵션
        ChromeOptions options = new ChromeOptions();
            // 크롬 백그라운드로 실행
        // options.addArguments("--headless=new", "--disable-gpu");
        WebDriver webDriver=new ChromeDriver(options);
        // 4) 크롬 드라이버에 크롤링할 주소 넣기
        webDriver.get(url);
        // 5) 해당 페이지는 동적(데이터를 표현하는데 부분적 시간 필요) 페이지
            // new WebDriverWait(현재크룸객체,Duration.ofXXX(대기단위));
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(1));
        // 6) 크롤링할 선택자, element/요소/마크업/<마크업>
        // WebElement 변수명 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(선택자)));
        // 6-1) 온도: .info_weather .num_deg
        WebElement temp = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".info_weather .num_deg")));
        System.out.println(temp.getText()); // 크롤링된 요소/마크업의 텍스트 확인
        WebElement temp2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".tooltip_icon .ico_airstat2")));
        System.out.println(temp2.getText());
        // 7) 가져온 정보들을 DTO/MAP 구성
        Map<String, Object> map = new HashMap<>();
        map.put("온도", temp2.getText());
        map.put("초미세먼지", temp.getText());
        // 8) 안전하게 드라이버 객체 직접 종료
        webDriver.quit();
        // 9) MAP 반환
        return map; // 임의
    }

    // [4] CGV 특정 영화 관람평 크롤링
    public List<String> test4(){
        // 1) 크롬 설치
        WebDriverManager.chromedriver().setup();
        // 2) 크롤링할 웹 주소
        String url="https://cgv.co.kr/cnm/cgvChart/movieChart/30000927";
        // 3) 크롬 드라이버 객체 생성
        WebDriver webDriver = new ChromeDriver();
                // 4) 크롬 드라이버에 주소 넣기
        webDriver.get(url);
        // *** 자바에서 JS 제어하여 스크롤을 내리는 작업 ***
        JavascriptExecutor js = (JavascriptExecutor) webDriver; // 크롬객체에서 js 객체 꺼내기
        js.executeScript("window.scrollTo(100, document.body.scrollHeight ); "); // .executeScript
        // window.scrollTo(100, document.body.scrollHeight
            // document.body.scrollHeight: 현재 화면에서 스크롤 전체 길이 = 높이 = 300px, 상단꼭지점=0, 하단꼭지점=300
            // .scrollTo( 이동할위치, 전체길이)
        try{Thread.sleep(1000);} catch (Exception e){}

            // *** 크롤링할 선택자로 요소 크롤링 .reveiwCard_txt__RrTgu
        List<String> list = new ArrayList<>();
        for(int page=1; page<=10; page++){
            int startCount = list.size(); // 특정 반복문 시작, 현재 리뷰 개수 판단
            // WebElement 1개 요소 vs List<WebElement> 여러 개 요소
            // wait.until() vs webDriver find
            List<WebElement> elements = webDriver.findElements(By.cssSelector(".reveiwCard_txt__RrTgu"));
            for(WebElement element : elements){
                // 만약에 리스트에 없는 리뷰이면 추가, 아니면 추가x
                String review = element.getText();
                if(list.contains(review)){continue;} // .contains(찾을값) 만약에 찾을값이 존재하면 true 아니면 false
                else{list.add(review);}
            }
            int endCount = list.size(); // 특정 반복문이 한 번 종료 되었을 때
            if(startCount==endCount){break;} // 리뷰 개수가 시작과 끝 개수가 같다면 크롤링 존재

            // *** 스클롤 내리기 작업 ***
            js.executeScript("window.scrollTo(100, document.body.scrollHeight ); ");
            try { Thread.sleep( 1000 ); } catch (Exception e) {}
        }
        return list;
    }
}
/*
웹크롤링: 웹(페이지) HTML 정보/자료 수집 과정
웹페이지마다 크롤링 허용 여부 확인 가능: URL/robots.txt
정적페이지: HTML, 동적페이지: JS(AXIOS, REACT)
 - 정적페이지: Jsoup 라이브러리
 - 동적페이지: Selenium 라이브러리(* 파이썬과 동일*)
Jsoup 라이브러리: implementation 'org.jsoup:jsoup:1.22.1'
Selenium 라이브러리
 - 스프링 지원하는 공식 라이브러리: https://start.spring.io/
 - 그 외 라이브러리: https://mvnrepository.com/
 */