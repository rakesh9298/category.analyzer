package category.analyzer.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import category.analyzer.exception.CategoryAnalyzerException;
import category.analyzer.interfaces.FileReader;

/**
 * 
 * @author rakesh
 * This class reads file from specified path using java.io.FileInputStream and java.io.BufferedReader
 */
public class StreamReader implements FileReader {
	private static final String ERR_MSG_FILE_NOT_FOUND = "File not found in specified path.";

	public List<String> read(String filePath) throws IOException, CategoryAnalyzerException {
		BufferedReader bufferedReader = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			bufferedReader = new BufferedReader(new InputStreamReader(
					fileInputStream));
			String inputLine = null;
			List<String> categoryStringList = new ArrayList<String>();
			
			while ((inputLine = bufferedReader.readLine()) != null) {
				categoryStringList.add(inputLine);
			}
			return categoryStringList;
		} catch (FileNotFoundException e) {
			throw new CategoryAnalyzerException(
					ERR_MSG_FILE_NOT_FOUND);
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
	}
}
