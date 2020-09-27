package prog12;


import java.util.*;

public class Binge2 implements SearchEngine {
	boolean boo = false;
	HardDisk <PageFile> pageDisk = new HardDisk<PageFile>();
	PageTrie URLToIndex = new PageTrie();
	
	HardDisk<List<Long>> wordFiles = new HardDisk<List<Long>>();
	WordTable wordToIndex = new WordTable();
	
	public Long indexPage (String url)
	{
		Long index = pageDisk.newFile();
		PageFile newFile = new PageFile(index, url);
		pageDisk.put(index, newFile);
		URLToIndex.put(url, index);
		System.out.println("indexing page " + newFile);
		return index;
		
	}
	
	public Long indexWord(String word) {
		Long index = wordFiles.newFile();
		List<Long> pages = new ArrayList<Long>();
		
    	//System.out.println("indexing word " + index + "(" + word + ")" + pages);
		wordFiles.put(index, pages);
		wordToIndex.put(word, index);

		return index;
	}
	@Override
	public void gather(Browser browser, List<String> startingURLs) {
		// TODO Auto-generated method stub
		  URLToIndex.read(pageDisk);
	      wordToIndex.read(wordFiles);
	}
	@Override
	
	
	public String[] search(List<String> keyWords, int numResults) {
		// TODO Auto-generated method stub
		System.out.println("pageDisk\n" + pageDisk);
		System.out.println("pageToIndex\n" + URLToIndex);
		System.out.println("wordDisk\n" + wordFiles);
		System.out.println("wordToIndex\n" + wordToIndex);
		Iterator<Long>[] wordFileIterators = (Iterator<Long>[]) new Iterator[keyWords.size()];
		long[] currentPageIndices = new long[keyWords.size()];
		PriorityQueue<Long> bestPageIndices = new PriorityQueue<Long>(numResults, new PageComparator());
		
		int wordFileIteratorPos = 0;
		for (String words: keyWords)
		{
			if(!wordToIndex.containsKey(words))
			{
				return new String [0];
			}
			else
			{
				long wordIndex = wordToIndex.get(words);
				wordFileIterators[wordFileIteratorPos] = wordFiles.get(wordIndex).iterator();
				wordFileIteratorPos++;
			}
		
			
		}
		 while(getNextPageIndices(currentPageIndices, wordFileIterators) == true) {
		    	//point is to move smallest ids forward and update priority queue
		    	if(allEqual(currentPageIndices) == true) {
		    		bestPageIndices.offer(currentPageIndices[0]);	
		    	}
		 }
		    		String [] results = new String[bestPageIndices.size()];
			    	for(int i = results.length-1; i >= 0; i--) {
		    			results[i] = pageDisk.get(bestPageIndices.poll()).url;
		    		}
		    	
		    	return results;
	}

	public class PageComparator implements Comparator<Long>{
		public int compare (Long a, Long b)
		{
			return pageDisk.get(a).getRefCount() - pageDisk.get(b).getRefCount() ;
			
		}
	}
	
		/** If all the currentPageIndices are the same (because are just
	    starting or just found a match), get the next page index for
	    each word: call next() for each word file iterator and put the
	    result into current page indices.
	
	    If they are not all the same, only get the next index if the
	    current index is smaller than the largest.
	
	    Return false if hasNext() is false for any iterator.
	
	    @param currentPageIndices array of current page indices
	    @param wordFileIterators array of iterators with next page indices
	    @return true if all minimum page indices updates, false otherwise
	*/
	private boolean getNextPageIndices (long[] currentPageIndices, Iterator<Long>[] wordFileIterators) {
	
		long lrgst = currentPageIndices[0];
		 
		if(allEqual(currentPageIndices) == true) {
			for(int i = 0; i < wordFileIterators.length; i++) {
				if(wordFileIterators[i].hasNext()) {
					currentPageIndices[i] = wordFileIterators[i].next();
				}
				else
					return false;
				//currentPageIndices[i] = wordFileIterators[i].next();
			}
		}
		else {
			for(Long ind : currentPageIndices) {
				if(ind > lrgst)
					lrgst = ind;
			}

			for(int i = 0; i < currentPageIndices.length; i++) {
				if(currentPageIndices[i] < lrgst) {
					
					if(wordFileIterators[i].hasNext()) {
						currentPageIndices[i] = wordFileIterators[i].next();
					}
					else
						return false;
				}	
			}	
			return true;
		}
		
				
		
		
		return true;
	}
	
	/** Check if all elements in an array are equal.
	    @param array an array of numbers
	    @return true if all are equal, false otherwise
	*/
	private boolean allEqual (long[] array) 
	{
		long first = array[0];
		for (int i = 0; i < array.length; i++)
		{
			if (first != array[i])
			{
				return false;
			}
		}
		return true;
	}
	}
	

