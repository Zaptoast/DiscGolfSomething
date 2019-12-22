# DiscGolf

### Link
http://3.19.39.71:8080/DiscGolfBoot/

### Overview
This website is a full CRUD site with a multi-table database that will track different disc golf courses with addresses, course length, description, ratings, and a comments section. users will be able to login, rate and comment on existing courses, as well as submit new courses not already in the database.

### Technologies used
* Java
* Rest API
* SQL
* Git
* AWS

### Lessons learned
While completing this project I learned alot about relational mapping in entities and gained additional experience in creating repositories, service interfaces, and controllers using the Rest API.

### API paths
* GET     "addresses"     gets all addresses
* GET     "addresses/{id}"      get one address
* POST      "addresses"     create an address
* PUT     "addresses/{id}"      replace an address
* DELETE      "addresses/{id}"      delete an address
* GET     "courses"     gets all courses
* GET     "courses/{id}"      get one course
* POST      "courses"     create an course
* PUT     "courses/{id}"      replace an course
* DELETE      "courses/{id}"      delete an course
* GET     "courses/{keyword}"     get courses by name/description
* GET     "courses/{length}"      get courses by length
