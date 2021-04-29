package view;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {
	public static void main(String[] args) throws Exception {

		//Document document = Jsoup.connect("http://www.koreabaseball.com/TeamRank/TeamRank.aspx").get();
		Document document = Jsoup.connect("https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&oquery=EPL&ie=utf8&query=KBO").get();		
		ArrayList list = new ArrayList();
		
		if (null != document) {
			Elements data = document.select("td");
			String text = data.text();
			StringTokenizer st = new StringTokenizer(text);
			while(st.hasMoreTokens()){
				//list.add(st.nextToken());
				System.out.println(st.nextToken());
				
			}
			
		}
		/*
		for(int i = 0 ; i < 110 ;i++){
				if (i % 11 == 0) {
					System.out.println();
				}
				System.out.print(list.get(i) + " ");
		}
		*/
		
	}

}
