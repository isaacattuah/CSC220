package prog12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirectoryTrie<T> {

	protected Path path;

	protected static final String FILE_NAME = "page  file";

	private static final String ROOT_NAME = "DirectoryTrie.root";

	public DirectoryTrie() throws IOException {
		this(ROOT_NAME);
	}

	public DirectoryTrie(String dir) throws IOException {

		String s = System.getProperty("user.dir");

		path = Paths.get(System.getProperty("user.dir"), dir);

		if (!Files.exists(path))
			Files.createDirectory(path);
	}

	public Path add(String s, T data) {
		return add(s.replaceAll("\\.", "\\.\\.\\.").replaceAll("/", "#"), data, path);
	}

	private Path add(String s, T data, Path p) {

		Path prefixPath;
		Path newSPath;
		Path suffixPath;

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(p)) {

			for (Path file : stream) {

				if(!Files.isDirectory(file)) continue;
				
				String fileName = file.getFileName().toString();
				int n = commonPrefix(s, fileName);

				if (n == fileName.length()) {
					return add(s.substring(n), data, file);
				} else if (n > 0) {

					String prefixDirectory = fileName.substring(0, n);
					String suffixDirectory = fileName.substring(n);
					String newSDirectory = s.substring(n);

					prefixPath = p.resolve(prefixDirectory);

					if (!Files.exists(prefixPath))
						Files.createDirectory(prefixPath);

					newSPath = prefixPath.resolve(newSDirectory);

					if (!Files.exists(newSPath)) {
						Files.createDirectory(newSPath);
					}

					Charset charset = Charset.forName("UTF-8");
					try (BufferedWriter writer = Files.newBufferedWriter(
							newSPath.resolve(FILE_NAME), charset)) {
						String line = data.toString();
						writer.write(line);
					} catch (IOException x) {
						System.err.format("IOException: %s%n", x);
					}

					suffixPath = prefixPath.resolve(suffixDirectory);

					Files.move(file, suffixPath);

					return newSPath;
				}

			}
			Path sPath = p.resolve(s);
			if (!Files.exists(sPath)) {
				Files.createDirectory(sPath);
			}

			Charset charset = Charset.forName("UTF-8");
			try (BufferedWriter writer = Files.newBufferedWriter(
					sPath.resolve(FILE_NAME), charset)) {
				String line = data.toString();
				writer.write(line);
			} catch (IOException x) {
				System.err.format("IOException: %s%n", x);
			}

			return sPath;

		} catch (IOException | DirectoryIteratorException x) {
			// IOException can never be thrown by the iteration.
			// In this snippet, it can only be thrown by newDirectoryStream.
			System.err.println(x);
		}

		return null;
	}

	public List<String> traverse() {
		List<String> list = new ArrayList<String>();
		traverse(path, list);
		return list;
	}

	private void traverse(Path p, List<String> list) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(p)) {

			for (Path file : stream) {
				if (file.getFileName().toString().equals(FILE_NAME)) {
					Charset charset = Charset.forName("UTF-8");
					list.add(join(Files.readAllLines(file, charset), "\n"));
				} else {
					traverse(file, list);
				}
			}

		} catch (IOException | DirectoryIteratorException x) {
			// IOException can never be thrown by the iteration.
			// In this snippet, it can only be thrown by newDirectoryStream.
			System.err.println(x);
		}
	}

	private static String join(List<String> list, String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i != 0)
				sb.append(s);
			sb.append(list.get(i));
		}
		return sb.toString();
	}

	private int commonPrefix(String a, String b) {
		int i = 0;
		int n = Math.min(a.length(), b.length());

		while (i < n && a.charAt(i) == b.charAt(i))
			i++;

		return i;
	}

}
