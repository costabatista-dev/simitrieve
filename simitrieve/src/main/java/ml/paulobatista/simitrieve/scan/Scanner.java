/**
 * 
 */
package ml.paulobatista.simitrieve.scan;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ml.paulobatista.simitrieve.error.ErrorHandler;

/**
 * @author paulo
 *
 */
public class Scanner {

	public List<File> getFiles(String root) {
		File rootFile;
		List<File> files = new ArrayList<File>();
		try {
			rootFile = new File(root);
			if (rootFile.isDirectory()) {
				File[] leavesFile = rootFile.listFiles();

				for (File iteratorFile : leavesFile) {

					if (iteratorFile.isDirectory()) {
						root = iteratorFile.getAbsolutePath();
						files.addAll(getFiles(root));
					}

					else {
						files.add(iteratorFile);
					}
				}
			}
		} catch (NullPointerException ex) {

			ErrorHandler.fileNotExist();

		}

		return files;
	}
}
