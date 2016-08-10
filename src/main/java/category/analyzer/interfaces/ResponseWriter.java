/**
 * 
 */
package category.analyzer.interfaces;

import java.io.IOException;

/**
 * 
 * @author rakesh
 * Interface to configure different kinds of output writers and decouple business logic from response writing process
 */
public interface ResponseWriter {
	public void write(String responseString) throws IOException;
}
