
import java.util.Comparator;
import java.util.Iterator;

import org.apache.lucene.search.spell.TermFreqIterator;
import org.apache.lucene.util.BytesRef;

public final class WordFreqArrayIterator implements TermFreqIterator {
 private WordFreq current;
 private final Iterator<WordFreq> wordFreqIterator;

 public WordFreqArrayIterator(Iterator<WordFreq> wordFreqIterator) {
  this.wordFreqIterator = wordFreqIterator;
 }

/* public WordFreqArrayIterator(List<E> list) {
  this(Arrays.asList(list).iterator());
 }*/

 @Override
 public Comparator<BytesRef> getComparator() {
  return null;
 }

 @Override
 public BytesRef next() {
  if (wordFreqIterator.hasNext()) {
   current = wordFreqIterator.next();
   return current.term;
  }
  return null;
 }

 @Override
 public long weight() {
  return current.count;
 }
}