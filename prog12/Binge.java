package prog12;
import java.util.*;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Binge implements SearchEngine 
{
	HardDisk <PageFile> pageDisk = new HardDisk<PageFile>(); //HardDisk for webpages (pagefiles)
	PageTrie URLToIndex = new PageTrie(); // URL to Index(Disk Number + Block Number) converter
	
	HardDisk<List<Long>> wordFiles = new HardDisk<List<Long>>(); //HardDisk for words (Array List of each word)
	WordTable wordToIndex = new WordTable(); // Word to Index converter
	
	
	
	public Long indexWord(String word) {
		Long index = wordFiles.newFile();
		List<Long> pages = new ArrayList<Long>();
		
    	System.out.println("indexing word " + index + "(" + word + ")" + pages);
		wordFiles.put(index, pages);
		wordToIndex.put(word, index);

		return index;
	}
	
	
	@Override
	public void gather(Browser browser, List<String> startingURLs) {
		System.out.println("gather " + startingURLs);
		Queue <Long> pageQueue = new ArrayDeque<Long>();
		
		for (String urlSearch : startingURLs)
		{
				if(!URLToIndex.containsKey(urlSearch))
				{
					Long newIndex = indexPage(urlSearch);
					pageQueue.offer(newIndex);
				}
		}
				
				while (!pageQueue.isEmpty())
				{
					System.out.println("queue " + pageQueue);
					Long pageIndex = pageQueue.poll();
					PageFile file = pageDisk.get(pageIndex);
					System.out.println("dequeued " + file);
					
					if(browser.loadPage(file.url))
					{
							List<String>  browserURLs = browser.getURLs();	
							Set<Long> pageIndices = new HashSet<Long>();
							Set<Long> wordIndicies = new HashSet<Long>();
							long subIndex;
							
							System.out.println("urls " + browserURLs);
							
							for(String url : browserURLs)
						{
							
								if(!URLToIndex.containsKey(url))
								{
									subIndex = indexPage(url);
									pageQueue.offer(subIndex);
								}
								else
								{
									subIndex = URLToIndex.get(url);
								}
								pageIndices.add(subIndex);
							
						}
							
							for (Long indexs : pageIndices)
							{
								pageDisk.get(indexs).incRefCount();
								System.out.println("inc ref "+ pageDisk.get(indexs));
							}
							System.out.println("words " + browser.getWords());
							
							for(String word: browser.getWords()) {
								
								if(!wordToIndex.containsKey(word)) {
									subIndex = indexWord(word);
								}
								else {
									subIndex = wordToIndex.get(word);
								}
//								
//								
								if(!wordIndicies.contains(subIndex)){
									wordIndicies.add(subIndex);
									wordFiles.get(subIndex).add(pageIndex);
									System.out.println("add page "+ subIndex +"("+ word +")"+ wordFiles.get(subIndex));
							}
						}
					}	
				}
		System.out.println("pageDisk\n" + pageDisk);
		System.out.println("urlToIndex\n" + URLToIndex);
		System.out.println("wordDisk\n" + wordFiles);
		System.out.println("wordToIndex\n" + wordToIndex);
		URLToIndex.write(pageDisk);
		wordToIndex.write(wordFiles);
		}
			
		
		
	
	@Override
	public String[] search(List<String> keyWords, int numResults) {
		
		return new String[0];
	}
	
	public Long indexPage (String url)
	{
		Long index = pageDisk.newFile();
		PageFile newFile = new PageFile(index, url);
		pageDisk.put(index, newFile);
		URLToIndex.put(url, index);
		System.out.println("indexing page " + newFile);
		return index;
		
	}
}