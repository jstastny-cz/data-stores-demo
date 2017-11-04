# data-stores-demo
A repository demonstrating usage of various data stores

## mongodb
### installation
Fedora System
```
# dnf install mongodb mongodb-server mongoimport
# service mongod start
```
### insert data
From the root of the repository run:
```
mongoimport --db data-store-demo --collection people --drop --file mongodb-data.json --jsonArray
```
Verify by:
```
mongo data-store-demo --eval "db.people.find({})"
```
will return similar:
```
{ "_id" : ObjectId("59fe3203c260a81d81e925e2"), "firstName" : "Adam", "lastName" : "Albrecht", "address" : { "street" : "Avenue A", "number" : 1, "city" : "Amsterdam" } }
{ "_id" : ObjectId("59fe3203c260a81d81e925e3"), "firstName" : "Evan", "lastName" : "Evans", "address" : { "street" : "Elsewhere E", "number" : 5, "city" : "Everton" } }
{ "_id" : ObjectId("59fe3203c260a81d81e925e4"), "firstName" : "Frank", "lastName" : "Finch", "address" : { "street" : "Faraway F", "number" : 6, "city" : "Frankfurt" } }
{ "_id" : ObjectId("59fe3203c260a81d81e925e5"), "firstName" : "Gery", "lastName" : "Goodguy", "address" : { "street" : "Groundfloor G", "number" : 7, "city" : "Geneve" } }
{ "_id" : ObjectId("59fe3203c260a81d81e925e6"), "firstName" : "Clint", "lastName" : "Cantrell", "address" : { "street" : "Crossroad C", "number" : 3, "city" : "Caterham" } }
{ "_id" : ObjectId("59fe3203c260a81d81e925e7"), "firstName" : "Ben", "lastName" : "Brahms", "address" : { "street" : "Boulevard B", "number" : 2, "city" : "Bologna" } }
{ "_id" : ObjectId("59fe3203c260a81d81e925e8"), "firstName" : "Dan", "lastName" : "Doyle", "address" : { "street" : "Driveway D", "number" : 4, "city" : "Denver" } }
```
Then you can proceed with Java classes included.

## postgresql
### installation
Fedora System
```
# dnf install postgresql postgresql-server
# /usr/bin/postgresql-setup --initdb
# service postgresql start
```
### insert data
```
psql
```
run following:
```
CREATE TABLE people(firstName VARCHAR(15), lastName VARCHAR(15), street VARCHAR(25), street_number INTEGER, city VARCHAR(20));
COPY people(firstName,lastName,street,street_number,city) FROM '/tmp/data.csv' ( FORMAT CSV, DELIMITER(',') );
```
NOTE: If you have to use postgres user for login and have troubles accessing the file, move him to a commonly accessible location - here we used /tmp
	
Verify in psql by running:
```
SELECT * FROM people;
```
will return:
```
 firstname | lastname |    street     | street_number |   city    
-----------+----------+---------------+---------------+-----------
 Adam      | Albrecht | Avenue A      |             1 | Amsterdam
 Ben       | Brahms   | Boulevard B   |             2 | Bologna
 Clint     | Cantrell | Crossroad C   |             3 | Caterham
 Dan       | Doyle    | Driveway D    |             4 | Denver
 Evan      | Evans    | Elsewhere E   |             5 | Everton
 Frank     | Finch    | Faraway F     |             6 | Frankfurt
 Gery      | Goodguy  | Groundfloor G |             7 | Geneve
(7 rows)

```
Then you can proceed with Java classes included.
