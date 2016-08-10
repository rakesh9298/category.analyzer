package category.analyzer.io;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import category.analyzer.exception.CategoryAnalyzerException;

public class StreamReaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRead() throws IOException, CategoryAnalyzerException {
		StreamReader streamReader = new StreamReader();
		List<String> catList = streamReader.read("src/test/resources/streamReaderInput.txt");
		assertNotNull(catList);
		assertEquals(16, catList.size());
		assertEquals("Category 13: ", "PLACE Texas", catList.get(13));
	}
	
	@Test(expected = CategoryAnalyzerException.class)
	public void testReadInvalidFilePath() throws IOException, CategoryAnalyzerException {
		StreamReader streamReader = new StreamReader();
		List<String> catList = streamReader.read("src/test/wrongPath/streamReaderInput.txt");		
	}

}
