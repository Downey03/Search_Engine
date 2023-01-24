<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Searchengine.HistoryResult"%>
<head>
<link rel = "stylesheet" type = "text/css" href = "styles.css" />
</head>
<html>
    <body>
         <form action = "Search">
                <input type = "text" name="keyword">
                <button type = "submit">Search</button>
            </form>
        <div class=table >
        <table border = 1.5px>
            <tr>
                <td>Keyword</td>
                <td>Link</td>
            </tr>
            <%
                ArrayList<HistoryResult> results = (ArrayList<HistoryResult>)request.getAttribute("results");
                for(HistoryResult result : results){
             %>
                <tr>
                    <td><%  out.println(result.getKeyword());%></td>
                    <td><a href="<%out.println(result.getLink());%>"><%out.println(result.getLink());%></a></td>
                </tr>
             <% } %>
        </table>
        </div>
    </body>
</html>