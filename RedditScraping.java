import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RedditScraping {

	static Document document = null;
	static Elements posts = null;
	static Elements comments = null;
	static String html = "";
	
	public static void printPosts(){
		html="";
        for (Element post : posts){
            
            String href = post.attributes().get("href");
            String full_link = "https://www.reddit.com" + href;
            String[] tokens = href.split("/");
            html+="<div><Strong>***************************************************</Strong></div>";
            html+="<div><Strong>Link: "+full_link+"</Strong></div>";
            html+="<div>"+tokens[2]+"---------"+tokens[tokens.length-1]+"</div>";
            html+="<div><Strong>Comments : </Strong></div>";
            printComments(full_link);
            html+="<div><Strong>***************************************************</Strong></div>";
        }
        
       
       
    }
	public static void printComments(String link) {
		try {
			document = Jsoup.connect(link).get();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comments = document.getElementsByClass("_3tw__eCCe7j-epNCKGXUKk _3jJ0c2FXVItDFjup-54-X2");
		for (Element comment : comments) {
			if(!comment.getElementsByClass("_1qeIAgB0cPwnLhDF9XSiJM").isEmpty()) {
			html+="<div>  -"+comment.getElementsByClass("_1qeIAgB0cPwnLhDF9XSiJM").get(0).text()+"</div>";
			}
		}
		
		
	}
	
	public static String scrape(String subreddit) {
	
		try {
			String full_url= "https://www.reddit.com/r/" + subreddit;
			document = Jsoup.connect(full_url).timeout(0).maxBodySize(0).get();
			posts = document.getElementsByClass("SQnoC3ObvgnGjWt90zD9Z _2INHSNB8V5eaWp4P0rY_mE");
			printPosts();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return html;
	}
	
	
}
