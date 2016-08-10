package category.analyzer.io;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.legacy.PowerMockRunner;

import category.analyzer.exception.CategoryAnalyzerException;
import category.analyzer.v1.AnalyzerServiceImplV1;
import category.analyzer.v1.RequestParserV1;
import category.analyzer.v1.ResponseHandlerV1;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { AnalyzeCategories.class } )
public class AnalyzeCategoriesTest {
	
	private String[] args = {"src/test/resources/inputfile.txt"};
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testMainWithInvalidArgs1() throws Exception {
		String[] args = new String[1];
		CategoryAnalyzerException exception1 = PowerMockito.mock(CategoryAnalyzerException.class);
		PowerMockito.whenNew(CategoryAnalyzerException.class).withAnyArguments().thenReturn(exception1);
		AnalyzeCategories.main(args);
		PowerMockito.verifyNew(CategoryAnalyzerException.class);		
	}
	
	public void testMainWithInvalidArgs2() throws Exception {
		String[] args1 = new String[1];
		args1[0] = "someInvalidPath.txt";
		CategoryAnalyzerException exception2 = PowerMockito.mock(CategoryAnalyzerException.class);
		PowerMockito.whenNew(CategoryAnalyzerException.class).withAnyArguments().thenReturn(exception2);
		AnalyzeCategories.main(args1);
		PowerMockito.verifyNew(CategoryAnalyzerException.class);			
	}
	
	@Test
	public void testMainForV1ReqParser() throws Exception {		
		RequestParserV1 requestParser = PowerMockito.mock(RequestParserV1.class);
		PowerMockito.whenNew(RequestParserV1.class).withNoArguments().thenReturn(requestParser);		
		AnalyzeCategories.main(args);	
		PowerMockito.verifyNew(RequestParserV1.class);
	}
	
	@Test
	public void testMainForV1Analyzer() throws Exception {		
		AnalyzerServiceImplV1 analyzer = PowerMockito.mock(AnalyzerServiceImplV1.class);
		PowerMockito.whenNew(AnalyzerServiceImplV1.class).withNoArguments().thenReturn(analyzer);
		AnalyzeCategories.main(args);	
		PowerMockito.verifyNew(AnalyzerServiceImplV1.class);
	}
	
	@Test
	public void testMainForV1ResponseHandler() throws Exception {
		ResponseHandlerV1 responseHandler = PowerMockito.mock(ResponseHandlerV1.class);
		PowerMockito.whenNew(ResponseHandlerV1.class).withNoArguments().thenReturn(responseHandler);
	    AnalyzeCategories.main(args);	
		PowerMockito.verifyNew(ResponseHandlerV1.class);
	}
	
	@Test
	public void testMain(){		
	    AnalyzeCategories.main(args);		    
	}
	
	@Test
	public void testMainForDiffVersion() throws Exception{		
		CategoryAnalyzerException exception3 = PowerMockito.mock(CategoryAnalyzerException.class);
		PowerMockito.whenNew(CategoryAnalyzerException.class).withAnyArguments().thenReturn(exception3);
		AnalyzeCategories.main(new String[]{"src/test/resources/inputfile_VERSION_2.txt"});
		PowerMockito.verifyNew(CategoryAnalyzerException.class);		
	}

}
