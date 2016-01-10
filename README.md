# spring-example
@author: akursat

@website: akursat.com
Technologies used
-------------
Spring Mvc   
Spring Security  
Jasper Report  
Hibernate  
Mysql  
Glassfish  
Maven  

Project Structure
-------------
  
    ├── ...
    ├── webapp                    
    │   ├── controller ...         
    │   ├── dao   ...         
    │   ├── model   ...         
    │   ├── service  ...         
    │   └── util  ...               
    └── webapp                    
    │   ├── jasper   ...         
    │   ├── resources  ...          
    │   ├── web-inf  ...
    │ 	│       ├── pages ...           
    │   │ 	├── glasfish-resources.xml           
    │   │ 	├── jdbc.properties           
    │   │ 	└── web.xml          
    │   └── meta-inf          
    └── ...



Introduction
-------------
This sample application has different roles, named admin and user. A visitor can sing up and log in as user. A user can log in to see the home page. An admin list users to export as pdf file.


Building
-------------
Firstly, you must create tables. I share sample sql query. See below paragraph.   
Secondly, you must edit JasperConnection.java, glassfish-resources.xml and jdbc.properties files.   
You must change [username] and [password] to your username and password of the database. The default username is [root], password is [akursat].  
Lastly, go to line 123 in the ListUserController.java 
[File f = new File("/home/akursat/SpringDemo/src/main/webapp/jasper/UserReport.pdf");] Select the correct directory and folder where you want to export the report file.

Run SQL statement 

```
CREATE TABLE users ( 
username character varying(50) NOT NULL, 
password character varying(50) NOT NULL, 
email character varying(50) NOT NULL, 
birthday date, 
sex smallint, 
enabled boolean, 
CONSTRAINT users_pkey PRIMARY KEY (username) 
)

CREATE TABLE authorities ( 
username character varying(50) NOT NULL, 
authority character varying(50) NOT NULL, 
CONSTRAINT fk_authorities_users FOREIGN KEY (username) 
REFERENCES users (username) 
MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION 
)
```
