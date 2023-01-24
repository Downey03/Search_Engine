<%@page import = "java.util.ArrayList"%>
<%@page import = "com.Searchengine.SearchResult"%>
<head>
<link rel = "stylesheet" type = "text/css" href = "styles.css" />
</head>
<html>
    <body>
         <form action = "Search">
                <input type = "text" name="keyword">
                <button type = "submit">Search</button>
            </form>
            <form action = "History" >
                    <button type = "submit" > History </button>
                </form>
        <div class=table >
        <table border = 1.5px>
            <tr>
                <td>Title</td>
                <td>Link</td>
            </tr>
            <%
                ArrayList<SearchResult> results = (ArrayList<SearchResult>)request.getAttribute("results");
                for(SearchResult result : results){
             %>
                <tr>
                    <td><%  out.println(result.getPageTitle());%></td>
                    <td><a href="<%out.println(result.getPageLink());%>"><%out.println(result.getPageLink());%></a></td>
                </tr>
             <% } %>
        </table>
        </div>
    </body>
</html>