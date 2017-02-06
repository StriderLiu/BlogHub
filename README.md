# BlogHub
Blog Website built on Spring MVC and Hibernate

# Introduction	
* Implemented various functionalities including publishing and commenting on blogs, managing friend relationships and exchanging messages within multiple users, as well as login and registration modules
	
* Strengthened the security of the website with Spring Security module, imported an administrator role to control the access of regular users; Deployed MySQL database to interact with Hibernate
	
* Applied Bootstrap to decorating the front end, implement the partial refresh and page flip functions with JQuery and AngularJS

# Notes

* Back end is built on 4-layers model: Controller, Service, DAO, Domain

1. Controller deals with web requests and dispatch them to service
2. Service will complete the task and interact with database using DAO interfaces
3. DAO is responsible for data processing (via Hibernate and HQL) and manipulate basic domain objects
4. Domain classes are java beans generated based on system design, they are also binded with corresponding database schema via Hibernate

* All configuration is done via annotations instead of XML because annotatioins are cleaner and more maintainable.
