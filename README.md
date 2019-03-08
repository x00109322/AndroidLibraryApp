# AndriodLibraryApp

The purpose of this project is to design and develop a RESTful web service and an Android app which uses the service.

This RESTful service should provide GET operation(s) to retrieve/query/search data in a database table. 
This web service is implemented using ASP.Net Core in C#. It will be deployed on Azure.
The data will be stored in a SQL Azure database and retrieved by the service operation as required. 
The Entity framework will be used with a code first approach to manage data in the database. 

The Android app will consume the web service. This app will focus on displaying a list of books which will be read from the database.
Here the user will be able to view what books are available in the library, order the books in ascending/descending order as well as search 
for a book by name, author or genre.Internationalization of the app into another language will be one feature.
Expresso tests will be used to do end to end testing of the app.