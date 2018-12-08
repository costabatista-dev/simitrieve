/**
 * 
 */
package ml.paulobatista.simitrieve.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ml.paulobatista.simitrieve.entity.csv.CSVData;

/**
 * @author costa
 *
 */
public class CSVManager {
	private CSVData csvData;

	public CSVManager(CSVData csvData) {
		this.csvData = csvData;
	}

	public void setCSVData(CSVData csvData) {
		this.csvData = csvData;
	}

	public CSVData getCSVData() {
		return this.csvData;
	}

	private String getDataToWrite(char separator) {
		String[] header = this.csvData.getHeader();
		List<String[]> content = this.csvData.getContent();
		String[][] dataToWrite = new String[content.size() + 1][header.length];

		for (int j = 0; j < header.length; j++) {
			dataToWrite[0][j] = header[j];
		}

		for (int i = 0; i < content.size(); i++) {
			String[] line = content.get(i);
			for (int j = 0; j < line.length; j++) {
				dataToWrite[i + 1][j] = line[j];
			}
		}

		StringBuilder builder = new StringBuilder();
		int lastIndexOfSeparator;
		for (String[] line : dataToWrite) {
			for (String s : line) {
				builder.append(s);
				builder.append(separator);
			}
			lastIndexOfSeparator = builder.lastIndexOf(String.valueOf(separator));
			builder.setCharAt(lastIndexOfSeparator, '\n');
		}

		return builder.toString();
	}

	public void writeCSV(char separator) {
		String filename = (this.csvData.getDataName() == null) ? "outfile.csv" : this.csvData.getDataName();
		filename = (filename.endsWith(".csv")) ? filename : filename + ".csv";
		File file = new File(filename);
		String dataToWrite = this.getDataToWrite(separator);
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(file);
			writer.write(dataToWrite);
			writer.flush();
			writer.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	public void writeCSV() {
		this.writeCSV(',');
	}

}
