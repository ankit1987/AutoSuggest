import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.suggest.Lookup.LookupResult;
import org.apache.lucene.search.suggest.analyzing.AnalyzingSuggester;
import org.apache.lucene.search.suggest.analyzing.FuzzySuggester;
import org.apache.lucene.util.Version;

/**
 * this is sinlgeTon class which contains suggestorMap.
 * @author ivy4127
 *
 */
public class LuceneAutoSuggester {
	
	private List<WordFreq> lookUpList;
	
	private AnalyzingSuggester analyzingSuggester ;
	

	static LuceneAutoSuggester autoSuggester=null;
	
	private LuceneAutoSuggester() {
		lookUpList=new ArrayList<WordFreq>();
		//using keyworrdAnalyzer to serach result with spaces also
		analyzingSuggester = new AnalyzingSuggester(new KeywordAnalyzer());
	}
	

	public static LuceneAutoSuggester getSuggester() {

		if(autoSuggester==null){
			synchronized(LuceneAutoSuggester.class){
				if(autoSuggester == null){
					System.out.println("initializing the singleTon::" );
					autoSuggester = new LuceneAutoSuggester();
				}
			}
		}
		return autoSuggester;    
	}

   /**
    * this function is called to prepare the map for autoSuggestor
    * @param dataDir
    */
	public void prepareMapping(File dataDir){
	   prepareLookupMap( dataDir);
	   prepareAutoSuggestor();
	   
   }
 
	/**
	 * this function read all the files in specified directory and prepare the map
	 * @param dataDir
	 */
	private void prepareLookupMap(File dataDir){

		File[] files = dataDir.listFiles();
		for(int i=0;i<files.length;i++){
			if(files[i].isDirectory()){
				prepareLookupMap(files[i]);
			}else{
				prepareLookupList(files[i]);
			}

		}
	}
	
    /**
     * this function is used to prepare loop map with the given file
     * @param f
     */
	private void prepareLookupList(File f ) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(f));
			String line=null;
			while((line=reader.readLine())!=null){
				if(line.length()!=0){
					String[] splits=line.split(",");
					String query=splits[0];
					String Number=splits[1];
					lookUpList.add(new WordFreq(query, Long.valueOf(Number)));
					
				}
			}
			System.out.println("looup list size::" + lookUpList.size());	
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	

	public void prepareAutoSuggestor(){
		try {
			analyzingSuggester.build(new WordFreqArrayIterator(lookUpList.iterator()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public List<LookupResult> suggestString(String query){
		List<LookupResult> results = analyzingSuggester.lookup(query, false, 100);
		return results;
	}


	public List<WordFreq> getLookUpList() {
		return lookUpList;
	}


	public void setLookUpList(List<WordFreq> lookUpList) {
		this.lookUpList = lookUpList;
	}


	public void setSuggester(AnalyzingSuggester suggester) {
		this.analyzingSuggester = suggester;
	}

	public AnalyzingSuggester getAnalyzingSuggester() {
		return analyzingSuggester;
	}


	public void setAnalyzingSuggester(FuzzySuggester analyzingSuggester) {
		this.analyzingSuggester = analyzingSuggester;
	}

}