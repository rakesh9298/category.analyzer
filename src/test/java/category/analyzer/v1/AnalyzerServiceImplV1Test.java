package category.analyzer.v1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import category.analyzer.core.AnalyzerServiceImpl;
import category.analyzer.exception.CategoryAnalyzerException;
import category.analyzer.v1.AnalyzerServiceImplV1;
import category.analyzer.vo.CategoryVO;
import category.analyzer.vo.RequestVO;
import category.analyzer.vo.ResponseVO;

public class AnalyzerServiceImplV1Test {

	private AnalyzerServiceImpl analyzer;
	
	@Before
	public void setUp() throws Exception {
		analyzer = new AnalyzerServiceImplV1();
	}

	@Test(expected= CategoryAnalyzerException.class)
	public void processTestCategoryAnalyzerException() throws CategoryAnalyzerException {
		RequestVO requestVO = null;
		ResponseVO responseVO = null;
		responseVO = analyzer.process(requestVO);	
		assertNull(responseVO);
		requestVO = new RequestVO();
		responseVO = analyzer.process(requestVO);
		assertNull(responseVO);
		requestVO.setRequestList(new ArrayList<CategoryVO>());
		responseVO = analyzer.process(requestVO);
		assertNull(responseVO);
	}
	
	@Test
	public void process() throws CategoryAnalyzerException{
		RequestVO requestVO = new RequestVO();
		ResponseVO responseVO = null;
		List<CategoryVO> categoryList = new ArrayList<CategoryVO>();
		requestVO.setRequestList(categoryList);
		categoryList.add(new CategoryVO("PERSON", "John"));
		categoryList.add(new CategoryVO("ANIMAL", "Dog"));
		categoryList.add(new CategoryVO("COMPUTER", "Windows"));
		categoryList.add(new CategoryVO("PLACE", "Texas"));
		categoryList.add(new CategoryVO("OTHER", "Somethimg"));
		
		responseVO = analyzer.process(requestVO);
		assertNotNull(responseVO);
		assertNotNull(responseVO.getCategoryCountMap());
		assertNotNull(responseVO.getCategoryVOs());
		assertEquals(5, responseVO.getCategoryVOs().size());
		assertEquals(5, responseVO.getCategoryCountMap().size());
		assertEquals(1, responseVO.getCategoryCountMap().get("PERSON").intValue());
		assertEquals(1, responseVO.getCategoryCountMap().get("OTHER").intValue());
		assertEquals(1, responseVO.getCategoryCountMap().get("ANIMAL").intValue());
		assertEquals(1, responseVO.getCategoryCountMap().get("COMPUTER").intValue());
		assertEquals(1, responseVO.getCategoryCountMap().get("PLACE").intValue());	
		
		
		
		categoryList = new ArrayList<CategoryVO>();
		requestVO.setRequestList(categoryList);
		categoryList.add(new CategoryVO("PERSON", "John"));
		categoryList.add(new CategoryVO("PERSON", "Sam"));
		responseVO = analyzer.process(requestVO);

		assertEquals(2, responseVO.getCategoryVOs().size());
		assertEquals(2, responseVO.getCategoryCountMap().get("PERSON").intValue());
		
		categoryList = new ArrayList<CategoryVO>();
		requestVO.setRequestList(categoryList);
		categoryList.add(new CategoryVO("PERSON", "John"));
		categoryList.add(new CategoryVO("PERSON", "John"));
		responseVO = analyzer.process(requestVO);

		assertEquals(1, responseVO.getCategoryVOs().size());
		assertEquals(1, responseVO.getCategoryCountMap().get("PERSON").intValue());
		
		categoryList = new ArrayList<CategoryVO>();
		requestVO.setRequestList(categoryList);
		categoryList.add(new CategoryVO("PERSON", "John"));
		categoryList.add(new CategoryVO("ILLEGAL", "Illegal Chars"));
		responseVO = analyzer.process(requestVO);

		assertEquals(1, responseVO.getCategoryVOs().size());
		assertEquals(1, responseVO.getCategoryCountMap().get("PERSON").intValue());		
	}
	
	@Test
	public void testCategoryCountMap(){
		Map<String, Integer> categoryCountMapTest = new LinkedHashMap<String, Integer>();
		categoryCountMapTest.put("PERSON", 0);
		categoryCountMapTest.put("PLACE", 0);
		categoryCountMapTest.put("ANIMAL", 0);
		categoryCountMapTest.put("COMPUTER", 0);
		categoryCountMapTest.put("OTHER", 0);
		
		AnalyzerServiceImplV1 anaImplV1 = new AnalyzerServiceImplV1();
		Map<String, Integer> categoryCountMap = anaImplV1.getPrepopulatedCategoryCountMap();
		assertNotNull(categoryCountMap);
		assertEquals(categoryCountMapTest.size(), categoryCountMap.size());
		Iterator<Entry<String, Integer>> iter = categoryCountMapTest.entrySet().iterator();
				
		for(Entry<String, Integer> cateCount :categoryCountMap.entrySet()){
			    Entry<String, Integer> cateCountTest = iter.next();
			     assertEquals(cateCountTest.getKey(), cateCount.getKey());
				 assertEquals(cateCountTest.getValue(), cateCount.getValue());			
		}
	}

}
