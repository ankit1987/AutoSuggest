This projet is devloped to make Google autocomplete functionality. 

I have used following opensource api
	1-Lucene (to create the in memory map for queries according to weight)
	2-Serlvet(to get the json response)
	3-Gson (to change httpservlet response into json object)

query data file is needed to be placed WebContent\WEB-INF\DataFiles. i am expecting file in the following format

	<query, weight>
ex. how to boil an egg,4


currently I am returning top 4 queries according to weight. this is configuration parameter(MAX_RETURN_QUERIES) in web.xml.	
	
	
===Creating war to deploy in webapps folder of tomcat===
use ant war, this will create war file in dist folder. you can deploy the generated war(AutoSuggest.war) to local webapps folder for localtesting.
if you running tomcat locally then use the following link to test http://localhost:8080/AutoSuggest/s?q=[place holder]
ex. http://localhost:8080/AutoSuggest/s?q=how%20t


============demo has been deployed over aws=====
http://ec2-50-112-143-235.us-west-2.compute.amazonaws.com:8080/s?q=[placed holder]
use above  link to see the demo.


==========Unit Testing==========
i have created CallService.java for unit testing. here i am creting thread pool and hitting webservice to get the response. i have created 100 thread, and 1000 task which are being used to hit the service.

NOTE: though i haven't used node.js or Erlang, but my service is able to handle 1000req/sec

======================================================SIEGE OUTPUT=========================================================

 =================with 1 user=============================================
root@ip-172-31-25-31:/home/ubuntu# siege -c1 -d1 -r1 -v http://ec2-50-112-143-235.us-west-2.compute.amazonaws.com:8080/s?q=ch
** SIEGE 2.70
** Preparing 1 concurrent users for battle.
The server is now under siege...
HTTP/1.1 200   0.01 secs:      57 bytes ==> /s?q=ch
done.                                                                                                                                      Transactions:                       1 hits
Availability:                 100.00 %
Elapsed time:                   1.01 secs
Data transferred:               0.00 MB
Response time:                  0.01 secs
Transaction rate:               0.99 trans/sec
Throughput:                     0.00 MB/sec
Concurrency:                    0.01
Successful transactions:           1
Failed transactions:               0
Longest transaction:            0.01
Shortest transaction:           0.01

 ========================with 100 concurrent user in 1 sec===================================================
  
  root@ip-172-31-25-31:/home/ubuntu# siege -c100 -d1 -r1 -v http://ec2-50-112-143-235.us-west-2.compute.amazonaws.com:8080/s?q=bo
** SIEGE 2.70
** Preparing 100 concurrent users for battle.
done.                                                                                                                                      Transactions:                     100 hits
Availability:                 100.00 %
Elapsed time:                   1.11 secs
Data transferred:               0.00 MB
Response time:                  0.00 secs
Transaction rate:              90.09 trans/sec
Throughput:                     0.00 MB/sec
Concurrency:                    0.36
Successful transactions:         100
Failed transactions:               0
Longest transaction:            0.01
Shortest transaction:           0.00



=============================with 1000 users in 1 sec===========================
root@ip-172-31-25-31:/home/ubuntu# siege -c1000 -d1 -r1 -v http://ec2-50-112-143-235.us-west-2.compute.amazonaws.com:8080/s?q=bo
** SIEGE 2.70
** Preparing 1000 concurrent users for battle.

done.                                                                                                                                      Transactions:                    1000 hits
Availability:                 100.00 %
Elapsed time:                   8.65 secs
Data transferred:               0.04 MB
Response time:                  0.00 secs
Transaction rate:             115.61 trans/sec
Throughput:                     0.00 MB/sec
Concurrency:                    0.44
Successful transactions:        1000
Failed transactions:               0
Longest transaction:            0.03
Shortest transaction:           0.00
