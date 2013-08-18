

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.search.suggest.Lookup.LookupResult;

import com.google.gson.Gson;

/**
 * Servlet implementation class HelloWorld
 */
public class EntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  int MAX_RETURN_RESULT;
    
	Gson gson=null;
     
	/**
     * @see HttpServlet#HttpServlet()
     */
    public EntryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException  {
        super.init(config);
        ServletContext context = getServletContext();
        MAX_RETURN_RESULT = Integer.valueOf(context.getInitParameter("MAX_RETURN_QUERIES"));
         gson = (Gson)context.getAttribute("gson");
      }
    

    /**
     * this function returns the suggesting queries in json form
     * @param results
     * @param query
     * @return
     */
    public String convertingToJson(List<LookupResult> results, String query){
    	List<String> list=new ArrayList<String>();
    	int count=0;
    	for(LookupResult lookupResult : results){
    		list.add((String) lookupResult.key);
    		count++;
    		if(count>=MAX_RETURN_RESULT){
    			break;
    		}
    	}

    	ResultType result=new ResultType(query, list);
    	//Gson gson = (Gson)getServletContext().getAttribute("gson");
    	return gson.toJson(result);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	long timeIn=System.currentTimeMillis();
    	ServletContext ctx= getServletContext();
    	LuceneAutoSuggester autoseggerster=(LuceneAutoSuggester)ctx.getAttribute("autoSuggestor");
    	String req=request.getParameter("q");
    	List<LookupResult> results=autoseggerster.suggestString(req);
       
  //  	long timeINForJson=System.currentTimeMillis();
    	String jsonResult=convertingToJson(results,req);
//    	long timeOutForJson=System.currentTimeMillis();

    	response.setContentType("application/json");
    	//PrintWriter printWriter  = response.getWriter();
    	response.getOutputStream().print(jsonResult);
    	
    //	printWriter.print(jsonResult);
    	//printWriter.flush();
    	long timeOut=System.currentTimeMillis();
    	System.out.println("===TOTAL TIME SPEND IN DO CALL=====" + (timeOut-timeIn));
    	
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
