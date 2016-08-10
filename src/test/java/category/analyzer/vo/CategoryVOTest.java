package category.analyzer.vo;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CategoryVOTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Set<CategoryVO> categoryVOs = new HashSet<CategoryVO>();
		categoryVOs.add(new CategoryVO(null, "sub1"));
		categoryVOs.add(new CategoryVO(null, "sub1"));
		categoryVOs.add(new CategoryVO("cat2", null));
		categoryVOs.add(new CategoryVO("cat2", null));
		categoryVOs.add(new CategoryVO(null, null));
		categoryVOs.add(new CategoryVO(null, null));
		categoryVOs.add(new CategoryVO("cat3", "sub3"));
		categoryVOs.add(new CategoryVO("cat3", "sub3"));
		categoryVOs.add(new CategoryVO("cat 3", "sub 3"));
		categoryVOs.add(new CategoryVO("CAT", "SUB"));
		categoryVOs.add(new CategoryVO("cat", "sub"));
		categoryVOs.add(new CategoryVO("111", "222"));
		categoryVOs.add(new CategoryVO("111", "222"));
		categoryVOs.add(new CategoryVO("", ""));
		categoryVOs.add(new CategoryVO("", ""));
		categoryVOs.add(new CategoryVO(new String("cat"), new String("sub")));
		
		assertEquals(9, categoryVOs.size());
		
	}

}
