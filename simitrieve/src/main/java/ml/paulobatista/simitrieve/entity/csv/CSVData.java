/**
 * 
 */
package ml.paulobatista.simitrieve.entity.csv;

import java.util.ArrayList;
import java.util.List;

/**
 * @author costa
 *
 */
public class CSVData {
	private String dataName;
	private String[] header;
	private List<String[]> content;
	
	
	public CSVData() {
		this.content = new ArrayList<>();
	}
	
	public CSVData(String[] header) {
		this.header = header;
	}
	
	public CSVData(String[] header, List<String[]> content) {
		this.header = header;
		this.content = content;
	}
	
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	
	public String getDataName() {
		return this.dataName;
	}
	
	public void setHeader(String[] header) {
		this.header = header;
	}
	
	public String[] getHeader() {
		return this.header;
	}
	
	public void setContent(List<String[]> content) {
		this.content = content;
	}
	
	public List<String[]> getContent() {
		return this.content;
	}
}
