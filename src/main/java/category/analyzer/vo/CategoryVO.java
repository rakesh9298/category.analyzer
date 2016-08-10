/**
 * 
 */
package category.analyzer.vo;

import java.util.Objects;

/**
 * @author K24776
 *
 */
public class CategoryVO {
	private String category;
	private String subCategory;	

	public CategoryVO() {
		
	}
	
	public CategoryVO(String category, String subCategory) {
		this.category = category;
		this.subCategory = subCategory;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.category + this.subCategory);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		CategoryVO categoryVO = (CategoryVO) obj;

		if ((category == null && categoryVO.getCategory() == null)
				|| (category != null && category.equals(categoryVO
						.getCategory()))) {
			if ((subCategory == null && categoryVO.getSubCategory() == null)
					|| (subCategory != null && subCategory.equals(categoryVO
							.getSubCategory()))) {
				return true;
			}
		}

		return false;
	}

}
