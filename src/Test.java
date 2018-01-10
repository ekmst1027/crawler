import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class Test {
   // http://gall.dcinside.com/board/lists/?id=smartphone&page=1
   
   static String BASE_URL_F = "http://gall.dcinside.com/board/lists/?id=smartphone&page=";
   static int BASE_URL_PAGE = 1;
   static String COMPLETE_URL = BASE_URL_F + BASE_URL_PAGE;

   public static void main(String[] args) throws IOException {
      Document doc = Jsoup.connect(COMPLETE_URL).get();   // dc사이트 연결
      Elements trElements = doc.select("tbody.list_tbody tr"); // 필요한 부분만 자르기
      
      for(Element trElement: trElements) {
    	  	Element number = trElement.select("td.t_notice").first();
    	  	Element title = trElement.select("td.t_subject").first();
    	  	Element id = trElement.select("td.t_writer").first();
    	  	Element date = trElement.select("td.t_date").first();
    	  	Element view = trElement.select("td.t_hits").first();
    	  	Element like = trElement.select("td.t_hits").last();
    	  	Element a = title.select("a").first();
    	  	String URLDetails = "http://gall.dcinside.com" + a.attr("href");
    	  	
    	  	if(!number.text().equals("공지")) {
    	  		System.out.println("------------리스트 파트-------------");
    	  		System.out.println(number.text());
        	  	System.out.println(title.text());
        	  	System.out.println(id.text());
        	  	System.out.println(date.text());
        	  	System.out.println(view.text());
        	  	System.out.println(like.text());
        	  	System.out.println(URLDetails);
        	  	System.out.println();
    	  		
    	  		
    	  		Document docDetail = Jsoup.connect(URLDetails).get();
    	        Elements d_trElements = docDetail.select("div#dgn_content_de"); // 필요한 부분만 자르기
    	        
    	        for(Element d_trElement: d_trElements) {
    	        	Element d_title = d_trElement.select("dl.wt_subject dd").first();
        	  		Element d_nick = d_trElement.select("span.user_nick_nm").first();
        	  		Element d_date = d_trElement.select("div.w_top_right").first();
        	  		Element d_content = d_trElement.select("table").first();
        	  		Elements r_trElements = d_trElement.select("table#gallery_re_contents.gallery_re_contents tbody");
            	  	System.out.println(r_trElements);

        	  		System.out.println("------------디테일 파트-------------");
            	  	System.out.println(d_title.text());
            	  	System.out.println(d_nick.text());
            	  	System.out.println(d_date.text());
            	  	System.out.println(d_content.text());
            	  	System.out.println();
            	  	
            	  	/*
            	  	for(Element r_trElement: r_trElements) {
            	  		Element r_nick = r_trElement.select("td.user.user_layer").first();
            	  		Element r_content = r_trElement.select("td.reply").first();
            	  		Element r_date = r_trElement.select("td.retime").first();
            	  		
            	  		System.out.println("-----------댓글 파트--------------");
                	  	System.out.println(r_nick.text());
                	  	System.out.println(r_content.text());
                	  	System.out.println(r_date.text());
                	  	System.out.println();
                	  
            	  	}
            	  	*/
            	  	
    	        }      
    	        
    	  	}
      }      
   }
}