This projet is devloped to make Google autocomplete functionality. 

I have used following opensource api
	1-Lucene (to create the in memory map for queries)
	2-Serlvet(to get the json response)
	3-Gson (to change httpservlet response into json object)

query data file is needed to be placed WebContent\WEB-INF\DataFiles. i am expecting file in the following format

		<query, weight>
example how to boil an egg,4


currently I am returning top 4 queries according to weight. this is configuration parameter(MAX_RETURN_QUERIES) in web.xml.	
	
	
===Creating war to deploy in webapps folder of tomcat===
use ant war, this will create war file in dist folder.


============demo has been deployed over aws=====
http://ec2-50-112-143-235.us-west-2.compute.amazonaws.com:8080/s?q=[placed holder]
use above  link to see the demo.
