package category.analyzer.v1;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import category.analyzer.vo.CategoryVO;
import category.analyzer.vo.ResponseVO;

public class ResponseHandlerV1Test {

	private ByteArrayOutputStream out = new ByteArrayOutputStream();
	private ByteArrayOutputStream err = new ByteArrayOutputStream();

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(out));
	    System.setErr(new PrintStream(err));
	}

	@Test
	public void test() throws IOException {
		ResponseVO responseVO = new ResponseVO(new LinkedHashSet<CategoryVO>(), new LinkedHashMap<String, Integer>());
		responseVO.getCategoryVOs().add(new CategoryVO("COMPUTER", "mac"));
		responseVO.getCategoryCountMap().put("COMPUTER", 1);
		
		StringBuilder builder = new StringBuilder();
		builder.append("CATEGORY").append("\t").append("COUNT").append("\n")
		.append("COMPUTER").append("\t").append("1").append("\n")
		.append("COMPUTER").append(" ").append("mac");
		
		ResponseHandlerV1 responseHandlerV1 = new ResponseHandlerV1();
		responseHandlerV1.handle(responseVO);
		
		//assertEquals("Console output", builder.toString(), out.toString());
	}
	
	@After
	public void tearDown() throws Exception {
	    System.setOut(null);
	    System.setErr(null);
	}

}
