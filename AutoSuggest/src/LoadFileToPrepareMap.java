import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.gson.Gson;


public class LoadFileToPrepareMap implements ServletContextListener{

	ServletContext context;
	

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		System.out.println("context destroyed::");
	}


	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		LuceneAutoSuggester autoSuggester= LuceneAutoSuggester.getSuggester();	
		//getting the relative path of data files
		String relativePath= contextEvent.getServletContext().getInitParameter("DATAFILE_LOCATION");
		System.out.println("relative path::" + relativePath);
		
		//getting the absolute path of data files
		String absolutePath=contextEvent.getServletContext().getRealPath(relativePath);
		System.out.println("absolulte path::" + absolutePath);
		autoSuggester.prepareMapping(new File(absolutePath));
		context=contextEvent.getServletContext();
		//setting the attribute
		context.setAttribute("autoSuggestor", autoSuggester);

		Gson gson = new Gson();
		context.setAttribute("gson", gson);
		System.out.println("created gson object");
	}

}
