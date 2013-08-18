package unitTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallService  {
	
	
	public static void main(String args[]) {
		
	    ExecutorService service = Executors.newFixedThreadPool(100);
	    for (int i =0; i<1000; i++){
	        service.submit(new Task());
	    }
	  
		
	}

}

final class Task implements Runnable{
 String [] str=new String[]{"how to ","how t","how to french","how to write a cover letter","create index","term frequency","inverse document frequency","document frequency"};


 @Override
 public void run() {
 
	 Random rng=new Random();
	 int i=rng.nextInt(str.length);
	 String query=str[i];
	 /*String url_name="http://ec2-50-112-143-235.us-west-2.compute.amazonaws.com:8080/s?q="+query;
	 System.out.println( url_name);*/
	 
	 //long startTime=System.currentTimeMillis();
		System.out.println();
		URL url;
		try {
			long startTime=System.currentTimeMillis();
			url = new URL("http://ec2-50-112-143-235.us-west-2.compute.amazonaws.com:8080/s?q=bu");
			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			long endTime=System.currentTimeMillis();
			System.out.println("time spend in query" + (endTime-startTime));
			String line = in.readLine(); 

			System.out.println( line );	
			/*System.out.println("time spend in query" + (endTime-startTime));*/

		}catch (MalformedURLException e) {
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	 
 }
}