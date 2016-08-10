/*
 * 
 */
package category.analyzer.interfaces;

import java.io.IOException;

import category.analyzer.vo.ResponseVO;


/**
 * @author rakesh
 * Response handler for generated response. Needs to be implemented based on output response format/version
 */
public interface ResponseHandler {
    public void handle(ResponseVO responseVO) throws IOException; 
}
