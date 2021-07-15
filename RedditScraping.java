import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RedditScraping {

	Document document = null;
	Elements posts = null;
	
	public RedditScraping(String subreddit) {
		
		
		try {
			String full_url= "https://www.reddit.com/r/" + subreddit;
			document = Jsoup.connect(full_url).timeout(1000000).get();
			posts = document.getElementsByClass("SQnoC3ObvgnGjWt90zD9Z _2INHSNB8V5eaWp4P0rY_mE");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printPosts(){
        for (Element post : posts){
            
            String href = post.attributes().get("href");
            String full_link = "https://www.reddit.com" + href;
            String[] tokens = href.split("/");
          
            System.out.println("*******************************************************************");
            System.out.println(tokens[2] + " ------ " + tokens[tokens.length-1]);
            System.out.println("Link: \n" + full_link);
            System.out.println("*******************************************************************");
        }
       
    }
	
	public static void main(String[] args){
        RedditScraping rs = new RedditScraping("all");
        rs.printPosts();
    }
}
