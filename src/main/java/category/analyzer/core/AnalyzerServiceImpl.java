package category.analyzer.core;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import category.analyzer.exception.CategoryAnalyzerException;
import category.analyzer.interfaces.AnalyzerService;
import category.analyzer.vo.CategoryVO;
import category.analyzer.vo.RequestVO;
import category.analyzer.vo.ResponseVO;

/**
 * 
 * @author rakesh Abstract class with concrete implementation of business logic.
 */
public abstract class AnalyzerServiceImpl implements AnalyzerService {

	private static final String ERR_MSG_INPUT_DATA = "Invalid input data";

	public final ResponseVO process(RequestVO requestVO) throws CategoryAnalyzerException {
		if(requestVO==null ||  requestVO.getRequestList()==null || requestVO.getRequestList().size()==0){
			throw new CategoryAnalyzerException(ERR_MSG_INPUT_DATA);
		}
		
		Set<CategoryVO> categoryVOSet = new LinkedHashSet<CategoryVO>(); //To ensure order of input categories is not disturbed, LinkedHashMap is used.
		                                                                 //CategoryVO has equals and hashCode methods implemented.
		Map<String, Integer> categoryCountMap = getPrepopulatedCategoryCountMap(); //Load the categories based on version
		for(CategoryVO categoryVO : requestVO.getRequestList()){
			if(categoryCountMap.containsKey(categoryVO.getCategory()) && !categoryVOSet.contains(categoryVO)){ //Only legal Categories will be considered, rest are omitted.
			   categoryVOSet.add(categoryVO);
		       categoryCountMap.put(categoryVO.getCategory(), categoryCountMap.get(categoryVO.getCategory())+1);
		    }
		}
		ResponseVO responseVO = new ResponseVO(categoryVOSet, categoryCountMap);
		return responseVO;
	}

	/**
	 * Abstract method to get list of categories in a particular order based on
	 * version. Every version may differ in List of categories and their order
	 * Enums do not support inheritance. To avoid common code from having
	 * version specific info, Map is used to wrap categories list.
	 * 
	 * @return Map<String, Integer>
	 */
	protected abstract Map<String, Integer> getPrepopulatedCategoryCountMap();
}
