/**
 * 
 */
package category.analyzer.io;

import java.io.IOException;
import java.util.logging.Logger;

import category.analyzer.exception.CategoryAnalyzerException;
import category.analyzer.interfaces.AnalyzerService;
import category.analyzer.interfaces.RequestParser;
import category.analyzer.interfaces.ResponseHandler;
import category.analyzer.v1.AnalyzerServiceImplV1;
import category.analyzer.v1.RequestParserV1;
import category.analyzer.v1.ResponseHandlerV1;
import category.analyzer.vo.RequestVO;
import category.analyzer.vo.ResponseVO;

/**
 * @author rakesh
 *  Main class that accepts file path as arguments and delegates
 *         to back end process
 */
public class AnalyzeCategories {
	private static final String VERSION = "_version_";
	private static final String FILE_PATH_SAPERATOR_BACKWRD_SLSH = "\\";
	private static final String FILE_PATH_SAPERATOR_FORWRD_SLSH = "/";
	private static final String ERR_MSG_INVALID_FILE_PATH = "File path provided is invalid.";
	private static final String ERR_MSG_NO_INPUT = "No input is provided";
	private static final String SPACE = " ";
	private static final Logger logger = Logger.getLogger(AnalyzeCategories.class.getName());


	private static void handle(String filePath)
			throws CategoryAnalyzerException, IOException {
		if (filePath == null || filePath.trim()==null || filePath.trim().equals(SPACE)) {
			throw new CategoryAnalyzerException(
					ERR_MSG_INVALID_FILE_PATH);
		}

		String fileName = null;
		if(filePath.lastIndexOf(FILE_PATH_SAPERATOR_BACKWRD_SLSH) > -1){
		  fileName = filePath.trim().toLowerCase().substring(filePath
				.lastIndexOf(FILE_PATH_SAPERATOR_BACKWRD_SLSH));
		} else if(filePath.lastIndexOf(FILE_PATH_SAPERATOR_FORWRD_SLSH) > -1){
		  fileName = filePath.trim().toLowerCase().substring(filePath
			    .lastIndexOf(FILE_PATH_SAPERATOR_FORWRD_SLSH));
		}else {
			fileName = filePath;
		}

		/*
		 * Version feature helps to parse different versions of request(file
		 * format may change in future) If "_version_" not present in file name,
		 * request is considered as version 1.
		 */
		RequestVO requestVO = null;
		RequestParser requestParser = null;
		AnalyzerService analyzerService = null;
		ResponseHandler responseHandler = null;
		if (fileName.lastIndexOf(VERSION) < 0) { //If no version string present in file name, file format is considered as version 1
			//Load version1 specific classes
			requestParser = new RequestParserV1(); 
			analyzerService = new AnalyzerServiceImplV1();
			responseHandler = new ResponseHandlerV1();
		} else {
			throw new CategoryAnalyzerException("This format of the file is not supported"); //Add here if next version is implemented
		}
		
		requestVO = requestParser.parseRequest(filePath);  
		ResponseVO responseVO = analyzerService.process(requestVO);
		responseHandler.handle(responseVO);		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			if (args == null || args.length==0) {
				System.out.println(ERR_MSG_NO_INPUT);
			} else {
				handle(args[0]);
			}
		} catch (CategoryAnalyzerException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}  catch (Throwable th) {
			th.printStackTrace();
		} 
	}

}
