package prog12;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BetterBrowser implements Browser {

	private Document doc;

	public static String reversePathURL(String urlString) {

		if (!urlString.startsWith("http")) {
			urlString = "http://" + urlString;
		}

		URL url;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}

		List<String> list = Arrays.asList(url.getHost().split("\\."));
		Collections.reverse(list);

		int hIndex = urlString.indexOf("#");
		if (hIndex == -1)
			hIndex = urlString.length();

		return encodeSpace(join(list, ".")
                                   + urlString.substring(urlString.indexOf(url.getHost())
						+ url.getHost().length(), hIndex)).toLowerCase();
                
	}

	public static String inverseReversePathURL(String urlString) {

		String[] sa = urlString.split("/");

		String port = "";
		if (sa[0].contains(":")) {
			port = sa[0].substring(sa[0].indexOf(":"), sa[0].length());
			sa[0] = sa[0].substring(0, sa[0].indexOf(":"));
		}

		List<String> list = Arrays.asList(sa[0].split("\\."));
		Collections.reverse(list);

		sa[0] = join(list, ".") + port;
		return "http://" + join(Arrays.asList(sa), "/");
	}

	// REMOVE THIS
	public static String inverseReversePathURLOld(String urlString) {

		String[] sa = urlString.split(" ");

		String port = "";
		if (sa[0].contains(":")) {
			port = sa[0].substring(sa[0].indexOf(":"), sa[0].length());
			sa[0] = sa[0].substring(0, sa[0].indexOf(":"));
		}

		List<String> list = Arrays.asList(sa[0].split("\\."));
		Collections.reverse(list);

		sa[0] = join(list, ".") + port;
		return "http://" + join(Arrays.asList(sa), "/");
	}

	public static String join(List<String> list, String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i != 0)
				sb.append(s);
			sb.append(list.get(i));
		}
		return sb.toString();
	}

	public static String encodeSpace(String s) {
		
		String r = s.replaceAll("/+", "/");
		while(r.charAt(r.length()-1) == '/') {
			r = r.substring(0, r.length()-1);
		}
		return r;
	}

	@Override
	public boolean loadPage(String url) {

		if (!url.startsWith("http"))
			url = inverseReversePathURL(url);

		try {
			doc = Jsoup.connect(url).get();
			return true;
		} catch (Exception e) {
			System.out.println("Error: unable to load page: " + url);
			System.out.println(e);
			return false;
		}
	}

	@Override
	public List<String> getWords() {
		if (doc != null && doc.body() != null && !doc.body().text().equals(""))
			return Arrays.asList(doc.body().text().split("[ ]+"));
		else
			return new ArrayList<String>();
	}

	@Override
	public List<String> getURLs() {

		if (doc == null)
			return new ArrayList<String>();

		List<String> list = new ArrayList<String>();
		for (Element e : doc.select("a[href]")) {
			try {
				if (new URL(e.attr("abs:href")).getProtocol()
						.startsWith("http")) {
					list.add(reversePathURL((e.attr("abs:href"))));
				}
			} catch (MalformedURLException e1) {

			}
		}
		return list;
	}

	public static void main(String[] args) {

		Browser b = new BetterBrowser();
		boolean load = b.loadPage("http://www.miami.edu");
		if (load)
			System.out.println(b.getWords());

                String url = "http://www.cs.miami.edu/~vjm/csc220/prog02/lab.txt";
                String lru = reversePathURL(url);
                System.out.println(lru);
                System.out.println(inverseReversePathURL(lru));
	}

}
