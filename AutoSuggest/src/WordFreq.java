
import org.apache.lucene.util.BytesRef;

public class WordFreq {
// this variable is priority of query 
public long count;
 // this variable is used to store the query
 public BytesRef term;



 public WordFreq(String term, long count) {
  this(new BytesRef(term), count);
 }
 
 
 public WordFreq(BytesRef term, long count) {
  this.term = term;
  this.count = count;
 }

}
