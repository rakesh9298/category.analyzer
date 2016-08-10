/**
 * 
 */
package category.analyzer.interfaces;

import java.io.IOException;

import category.analyzer.exception.CategoryAnalyzerException;
import category.analyzer.vo.RequestVO;

/**
 * @author rakesh
 * Request Parser for incoming requests. Needs to be implemented based on input file version/format.
 */
public interface RequestParser {
	
	public RequestVO parseRequest(String filePath) throws CategoryAnalyzerException, IOException;

}
