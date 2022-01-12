package Test2;

import java.util.HashMap;
import java.util.List;

public class SortData {
	private HashMap<Integer, List<String>> lineAndWord;
	private String fileNames;
	
	public HashMap<Integer, List<String>> getLineAndWord() {
		return lineAndWord;
	}
	public void setLineAndWord(HashMap<Integer, List<String>> lineAndWord) {
		this.lineAndWord = lineAndWord;
	}
	public String getFileNames() {
		return fileNames;
	}
	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}
	public SortData(HashMap<Integer, List<String>> lineAndWord, String fileNames) {
		this.lineAndWord = lineAndWord;
		this.fileNames = fileNames;
	}
	public SortData() {}
	@Override
	public String toString() {
		return "SortData [lineAndWord=" + lineAndWord + ", " + "fileName=" + fileNames + "]";
	}
}
