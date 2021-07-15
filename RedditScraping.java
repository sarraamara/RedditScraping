import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RedditScraping {

	Document document = null;
	Elements posts = null;
	Elements comments = null;
	
	public RedditScraping(String subreddit) {
		
		
		try {
			String full_url= "https://www.reddit.com/r/" + subreddit;
			document = Jsoup.connect(full_url).timeout(0).maxBodySize(0).get();
			posts = document.getElementsByClass("SQnoC3ObvgnGjWt90zD9Z _2INHSNB8V5eaWp4P0rY_mE");
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printPosts(){
        for (Element post : posts){
            
            String href = post.attributes().get("href");
            String full_link = "https://www.reddit.com" + href;
            String[] tokens = href.split("/");
          
            System.out.println("*******************************************************************");
            System.out.println("Link: \n" + full_link);
            System.out.println(tokens[2] + " ------ " + tokens[tokens.length-1]);
            System.out.println("Comments : ");
            printComments(full_link);
            System.out.println("*******************************************************************");
        }
       
    }
	public void printComments(String link) {
		try {
			document = Jsoup.connect(link).get();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comments = document.getElementsByClass("_3tw__eCCe7j-epNCKGXUKk _3jJ0c2FXVItDFjup-54-X2");
		for (Element comment : comments) {
			if(!comment.getElementsByClass("_1qeIAgB0cPwnLhDF9XSiJM").isEmpty()) {
			System.out.println(" -"+comment.getElementsByClass("_1qeIAgB0cPwnLhDF9XSiJM").get(0).text());
			}
		}
		
		
	}
	
	public static void main(String[] args){
        RedditScraping rs = new RedditScraping("all");
        rs.printPosts();
    }
}
