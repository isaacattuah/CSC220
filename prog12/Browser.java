package prog12;

import java.util.List;

public interface Browser {

	boolean loadPage(String url);

	List<String> getWords();

	List<String> getURLs();
}
