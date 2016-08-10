package category.analyzer.v1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.runners.JUnit44RunnerImpl;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.legacy.PowerMockRunner;

import category.analyzer.io.StreamReader;
import category.analyzer.vo.CategoryVO;
import category.analyzer.vo.RequestVO;


@RunWith(PowerMockRunner.class)
@PrepareForTest( { RequestParserV1.class } )
public class RequestParserV1Test {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void parseRequest() throws Exception {
		List<String> catList = new ArrayList<String>();
		catList.add("PERSON john");
		catList.add("PLACE   TEXAS ");
		catList.add("PERSON JOHN SMITH");
		catList.add("Person JOHN   PETER");
		StreamReader fileReader = PowerMockito.mock(StreamReader.class);
		PowerMockito.whenNew(StreamReader.class).withNoArguments().thenReturn(fileReader);
		Mockito.when(fileReader.read(Mockito.anyString())).thenReturn(catList);
		RequestParserV1 requestParserV1 = new RequestParserV1();
		RequestVO requestVO = requestParserV1.parseRequest("xxx");
		assertNotNull(requestVO);
		assertNotNull(requestVO.getRequestList());
        assertEquals(4, requestVO.getRequestList().size());
        assertEquals("john", requestVO.getRequestList().get(0).getSubCategory());
        assertEquals("PLACE", requestVO.getRequestList().get(1).getCategory());
        assertEquals("TEXAS", requestVO.getRequestList().get(1).getSubCategory());
        assertEquals("JOHN SMITH", requestVO.getRequestList().get(2).getSubCategory());
        assertEquals("PERSON", requestVO.getRequestList().get(3).getCategory());
        assertEquals("JOHN PETER", requestVO.getRequestList().get(3).getSubCategory());
        Mockito.verify(fileReader).read(Mockito.anyString());
	}
}
