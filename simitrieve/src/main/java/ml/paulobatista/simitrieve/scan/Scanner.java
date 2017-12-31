/**
 * 
 */
package ml.paulobatista.simitrieve.scan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ml.paulobatista.simitrieve.entity.ProgrammingLanguage;
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

	public List<File> getFiles(String root, ProgrammingLanguage programmingLanguage) {

		List<File> files = getFiles(root);
		String[] pattern = null;
		if (programmingLanguage.equals(ProgrammingLanguage.JAVA)) {
			pattern = Pattern.java();
		} else if (programmingLanguage.equals(ProgrammingLanguage.JAVASCRIPT)) {
			pattern = Pattern.javascript();
		} else if (programmingLanguage.equals(ProgrammingLanguage.PYTHON)) {
			pattern = Pattern.python();
		} else if (programmingLanguage.equals(ProgrammingLanguage.RUBY)) {
			pattern = Pattern.ruby();
		} else if (programmingLanguage.equals(ProgrammingLanguage.CPP)) {
			pattern = Pattern.cpp();
		} else {
			ErrorHandler.unexpectedProjectProgrammingLanguage();
		}

		if (pattern == null) {
			ErrorHandler.patternIsNULL();
		}

		List<File> projectFiles = new ArrayList<File>();
		String fileName;

		if (!programmingLanguage.equals(ProgrammingLanguage.CPP)) {
			for (File iteratorFile : files) {
				fileName = iteratorFile.getName();
				if (fileName.endsWith(pattern[0]) && !isUndesirableFile(iteratorFile, programmingLanguage)) {
					projectFiles.add(iteratorFile);
				}
			}
		}

		else {
			for (File iteratorFile : files) {
				fileName = iteratorFile.getName();

				for (String iteratorPattern : pattern) {
					if (fileName.endsWith(iteratorPattern)) {
						projectFiles.add(iteratorFile);
					}
				}
			}
		}

		if (projectFiles.size() == 0 || projectFiles == null) {
			ErrorHandler.projectIsEmpty();
		}

		return projectFiles;
	}

	private boolean isUndesirableFile(File file, ProgrammingLanguage programmingLanguage) {
		String fileName = file.getName();
		boolean isUndesirable = false;
		if (programmingLanguage.equals(ProgrammingLanguage.JAVA) && fileName.equals("package-info.java")) {
			isUndesirable = true;
		} else if (programmingLanguage.equals(ProgrammingLanguage.PYTHON) && fileName.equals("__init__.py")) {
			isUndesirable = true;
		}
		return isUndesirable;
	}
	
	
	@SuppressWarnings("null")
	public List<String> getTextFromFile(File file) {

		if (!file.exists()) {
			ErrorHandler.fileNotExist();
		}

		List<String> text = new ArrayList<String>();
		FileReader fileReader = null;
		BufferedReader buffer = null;
		Integer line = null;
		try {
			fileReader = new FileReader(file);
			buffer = new BufferedReader(fileReader);

			String currentLine;
			line = new Integer(1);
			while ((currentLine = buffer.readLine()) != null) {
				text.add(currentLine);
	
				line++;
			}

		} catch (IOException ex) {
			ex.printStackTrace();
			ErrorHandler.errorInFileReader(file.getName(), line.toString());
		} finally {
			try {
				if (buffer != null) {
					buffer.close();
				}
				
				if (fileReader != null) {
					fileReader.close();
				}
			} catch(IOException ex) {
				ex.printStackTrace();
				ErrorHandler.errorInFileReader(file.getName(), line.toString());
			}
		}
		
		return text;
	}
}
