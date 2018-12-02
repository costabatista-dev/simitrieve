/**
 * 
 */
package ml.paulobatista.simitrieve;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author paulo
 *
 */
public class ProjectReader {

	public List<String> getProjectStructure(String root) {
		List<Path> list = null;
		try (Stream<Path> paths = Files.walk(Paths.get(root))) {
			list = paths.collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> listOfPaths = new ArrayList<>();

		for (Path p : list) {
			listOfPaths.add(p.toString());
		}

		return listOfPaths;
	}

	public List<String> getProgrammingFilePaths(String root, String[] ext) {
		List<String> filePaths = this.getProjectStructure(root);
		List<String> programmingFilePaths = new ArrayList<>();

		for (String p : filePaths) {
			for (String e : ext) {
				e = (e.charAt(0) == '.') ? e : ('.' + e);
				if (p.endsWith(e)) {

					programmingFilePaths.add(p);
				}
			}
		}

		return programmingFilePaths;
	}
}
