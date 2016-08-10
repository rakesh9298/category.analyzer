package category.analyzer.v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import category.analyzer.exception.CategoryAnalyzerException;
import category.analyzer.interfaces.FileReader;
import category.analyzer.interfaces.RequestParser;
import category.analyzer.io.StreamReader;
import category.analyzer.vo.CategoryVO;
import category.analyzer.vo.RequestVO;

/**
 * @author rakesh
 *  This class parses the incoming file as per version 1 format. 
 *         
 */
public class RequestParserV1 implements RequestParser {
    private static final String EER_MSG_NO_CATEGORY_DETAILS = "No catogery details are present in file";
    private static final String EMPTY_STRING = "";
    private static final String SPACE = " ";
	private static final String EXTRA_SPACE = "\\s+";
    
	public RequestVO parseRequest(String filePath)
			throws CategoryAnalyzerException, IOException {
	
		FileReader fileReader = new StreamReader(); //Load file reader. If StreamReader is not efficient, Another reader can be configured here.
		List<String> catregoryStringList = fileReader.read(filePath); //Decouple file reading from business logic.
		                                                              //Get all strings from input file.
		if(catregoryStringList==null || catregoryStringList.size()==0){
			throw new CategoryAnalyzerException(EER_MSG_NO_CATEGORY_DETAILS);			
		}
		
		List<CategoryVO> catDetailsList = new ArrayList<CategoryVO>();
		RequestVO requestVO = new RequestVO();
		requestVO.setRequestList(catDetailsList);
		
		//Identify Category and Sub category in each line based on file format and add to CategoryVO list.
		for(String inputLine : catregoryStringList){
			String trimmedLine = inputLine.trim();
			if (!EMPTY_STRING.equals(trimmedLine)) {
				trimmedLine = trimmedLine.replaceAll(EXTRA_SPACE,SPACE);
				String category = null;
				String subCategory = null;
				int saperaterIndex = trimmedLine.indexOf(SPACE);
				if (saperaterIndex > 0) { //String not following format is omitted. 
					category = trimmedLine.substring(0, saperaterIndex)
							.toUpperCase();
					subCategory = trimmedLine
							.substring(saperaterIndex + 1);
					if (subCategory != null) {
						subCategory = subCategory.trim();
					}
				}
				catDetailsList
						.add(new CategoryVO(category, subCategory));
			}
		}
		return requestVO;		
	}

}
