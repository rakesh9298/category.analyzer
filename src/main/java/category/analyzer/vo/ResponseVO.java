package category.analyzer.vo;

import java.util.Map;
import java.util.Set;

/**
 * @author K24776 Response object is created for sending the analyzed response
 *         to client.
 */
public class ResponseVO {
	private Set<CategoryVO> categoryVOs;
	private Map<String, Integer> categoryCountMap;

	public ResponseVO(Set<CategoryVO> categoryVOs,
			Map<String, Integer> categoryCountMap) {
		super();
		this.categoryVOs = categoryVOs;
		this.categoryCountMap = categoryCountMap;
	}

	public Set<CategoryVO> getCategoryVOs() {
		return categoryVOs;
	}

	public void setCategoryVOs(Set<CategoryVO> categoryVOs) {
		this.categoryVOs = categoryVOs;
	}

	public Map<String, Integer> getCategoryCountMap() {
		return categoryCountMap;
	}

	public void setCategoryCountMap(Map<String, Integer> categoryCountMap) {
		this.categoryCountMap = categoryCountMap;
	}
}
