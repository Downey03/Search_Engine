package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

public class Crawler {
    private HashSet<String> urlLink;
    private int MAX_DEPTH = 2;
    public Connection connection;

    public Crawler(){
        connection = DatabaseConnection.getConnection();
        urlLink =new HashSet<String>();
    }

    public void getPageTextAndLink(String url,int depth){
        if(!urlLink.contains(url)){
            if(urlLink.add(url)){
                System.out.println(url);
            }
            try {
                Document document = Jsoup.connect(url).timeout(5000).get();
                String text = document.text().length()<500 ? document.text():document.text().substring(0,499);
                System.out.println( text);
                PreparedStatement preparedStatement = connection.prepareStatement("Insert into pages values(?,?,?)");
                preparedStatement.setString(1,document.title());
                preparedStatement.setString(2,url);
                preparedStatement.setString(3,text);
                preparedStatement.executeUpdate();
                depth++;
                if(depth>MAX_DEPTH)
                    return;
                Elements availableLinksOnPage = document.select("a[href]");
                for(Element currentLink: availableLinksOnPage)
                {
                    getPageTextAndLink(currentLink.attr("abs:href"),depth);
                }
            } catch (IOException  e) {
                e.printStackTrace();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.getPageTextAndLink("https://www.javatpoint.com",0)  ;
    }

}