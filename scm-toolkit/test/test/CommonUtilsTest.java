package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.BeforeClass;
import org.junit.Test;

import scm.toolkit.Constants;
import scm.toolkit.util.CommonUtils;

public class CommonUtilsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testSplitAsMap() {
		
		//System.out.println();
		
		//Map<String, String>  aa = CommonUtils.splitAsMap("[フェーズ|[運用:=運用テスト]]", ",", Constants.KEY_VALUE_PIPE_MARK);
		
		//System.out.println(aa);
		
		
//		Map<String, Map<String, String>> valueMap = new HashMap<String, Map<String,String>>();
//		for (Entry<String, String> customValueMappingEntry : CommonUtils.splitAsMap("[フェーズ|[運用:=運用テスト]]", ",", Constants.KEY_VALUE_PIPE_MARK).entrySet()) {
//			
//			
//			valueMap.put(customValueMappingEntry.getKey(), CommonUtils.splitAsMap(customValueMappingEntry.getValue(), ",", Constants.KEY_VALUE_SET_MARK));
//		}
		
		//System.out.println(CommonUtils.splitAsMap("[運用:=運用テスト]", ",", Constants.KEY_VALUE_SET_MARK));
		 
		
		System.out.println(Boolean.valueOf("true"));
	}

}
