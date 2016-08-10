/**
 * 
 */
package category.analyzer.vo;

import java.util.List;

/**
 * @author K24776
 * Request object that is passed to business logic component for analyzing.
 */
public class RequestVO {

	private List<CategoryVO> requestList;

	public List<CategoryVO> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<CategoryVO> requestList) {
		this.requestList = requestList;
	}
}
