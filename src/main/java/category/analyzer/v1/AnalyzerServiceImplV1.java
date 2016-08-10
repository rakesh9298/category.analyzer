package category.analyzer.v1;

import java.util.LinkedHashMap;
import java.util.Map;

import category.analyzer.core.AnalyzerServiceImpl;

/**
 * 
 * @author rakesh
 * Class to configure version 1 specific category list
 */
public class AnalyzerServiceImplV1 extends AnalyzerServiceImpl {
	
	private static final Integer ZERO = 0;

	/**
	 * Loads map with version 1 specific category list. To ensure order, LinkedHashMap is used.
	 */
	@Override
	public Map<String, Integer> getPrepopulatedCategoryCountMap() {
		Map<String, Integer> categoryCountMap = new LinkedHashMap<String, Integer>();
		categoryCountMap.put("PERSON", ZERO);
		categoryCountMap.put("PLACE", ZERO);
		categoryCountMap.put("ANIMAL", ZERO);
		categoryCountMap.put("COMPUTER", ZERO);
		categoryCountMap.put("OTHER", ZERO);	
		return categoryCountMap;
	}

	

}
