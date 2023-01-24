# Search_Engine
Search Engine that retrives a website from the database.

----------------------------------------------------------------------------------------------------------------------
To run this program successfully you need a database and you have to create two tables in the database
The command to create the tables are given below:

create table history(keyword text,searchLink text);
create table pages(pageTitle varchar,pageLink text,pageData text);

----------------------------------------------------------------------------------------------------------------------
First you need to enter the credentials of your database in the database connection class under Web_Crawler folder
and run the Web_Crawler program
The web crawler collects stores all the pageTitle, pageLink and pageData in the pages table

Before proceeding to the next steps start your database server

Next, you need to enter the credentials of your database in the database connection class under Search_Engine folder
and run the search engine folder

It will retrieve the data from the pages table and also store the keyword in the history table

-----------------------------------------------------------------------------------------------------------------------
