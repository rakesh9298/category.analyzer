/**
 * 
 */
package category.analyzer.interfaces;

import category.analyzer.exception.CategoryAnalyzerException;
import category.analyzer.vo.RequestVO;
import category.analyzer.vo.ResponseVO;

/**
 * @author rakesh
 * Core class that handles business logic.
 */
public interface AnalyzerService {

	public ResponseVO process(RequestVO requestVO) throws CategoryAnalyzerException;
}
