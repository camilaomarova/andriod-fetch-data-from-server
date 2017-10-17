package camcompany.camnews;

import com.orm.SugarRecord;

import java.util.ArrayList;


public class News extends SugarRecord<News> {
    //Integer news_id;
    String news_title;
    String news_text;
    String news_date;

    public News() {
    }

    public News(String news_title,  String news_text, String news_date) {
        //this.news_id = news_id;
        this.news_title = news_title;
        this.news_text = news_text;
        this.news_date = news_date;
    }

    public String getTitle() {
        return this.news_title;
    }
    public String getText() {
        return this.news_text;
    }
    public String getDate() {
        return this.news_date;
    }

    //private static int lastContactId = 0;
    /*
    public static ArrayList<News> createNewsList() {
        ArrayList<News> news = new ArrayList<News>();

        news.add(new News(1, "Facebook reports jumps in profit", "lorem ipsum doler ammet", "23 August, 2017"));
        news.add(new News(2, "Windows 10? Try 'Windows 10 years ago'", "lorem ipsum doler ammet", "23 August, 2017"));
        news.add(new News(3, "First News", "lorem ipsum doler ammet", "20 August, 2017"));
        news.add(new News(4, "First News", "lorem ipsum doler ammet", "20 August, 2017"));
        news.add(new News(5, "First News", "lorem ipsum doler ammet", "20 August, 2017"));
        news.add(new News(6, "First News", "lorem ipsum doler ammet", "20 August, 2017"));

        return news;
    }
    */

}
