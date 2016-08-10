package category.analyzer.interfaces;

import java.io.IOException;
import java.util.List;

import category.analyzer.exception.CategoryAnalyzerException;

/**
 * 
 * @author rakesh
 * Interface to configure different kinds of file readers and decouple file reading process from business logic
 */
public interface FileReader {
      public List<String> read(String filePath) throws IOException, CategoryAnalyzerException;
}
