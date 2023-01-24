package com.Searchengine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Search")
public class Search extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String keyword = request.getParameter("keyword");
        System.out.print(keyword);
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into history values(?,?);");
            preparedStatement.setString(1,keyword);
            preparedStatement.setString(2,"https://localhost:8080/Search_Engine/Search?keyword="+keyword);
            preparedStatement.executeUpdate();
            ResultSet resultset = connection.createStatement().executeQuery("select pageTitle,pageLink,(length(lower(pageData))-length(replace(lower(pageData),'"+keyword+"',\"\")))/length('"+keyword+"') as countoccurence from pages order by countoccurence desc limit 30");
            ArrayList<SearchResult> results = new ArrayList<>();
            while(resultset.next())
            {
                SearchResult searchResult = new SearchResult();
                searchResult.setPageTitle(resultset.getString("pageTitle"));
                searchResult.setPageLink(resultset.getString("pageLink"));
                results.add(searchResult);

            }
            request.setAttribute("results",results);
            request.getRequestDispatcher("/search.jsp").forward(request,response);
            response.setContentType("text/html");
            PrintWriter out= response.getWriter();
        }catch(SQLException e){
            e.printStackTrace();
        }
        catch (ServletException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
