package unitTesting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class TestTimings {

	
	public static void main(String[] args) {
		
		String line;
		try 
		{ 
			long startTime=System.currentTimeMillis();
			System.out.println();
			URL url = new URL( "http://ec2-50-112-143-235.us-west-2.compute.amazonaws.com:8080/webTesting_lucene/s?q=how%20t" ); 
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream())); 
			long endTime=System.currentTimeMillis();
			System.out.println("time spend in query" + (endTime-startTime));
			line = in.readLine(); 

			System.out.println( line );	

			in.close(); 
		}
		catch (Exception e)
		{ 
			e.printStackTrace(); 
		} 


	}

}
