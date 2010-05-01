package scm.toolkit.util;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
	public static Map<String, String> splitAsMap(String input, String splitRegex, String keyValueSplitRegex) {
		Map<String, String> result = new HashMap<String, String>();
		String[] peaces = input.split(splitRegex);
		for (String peace : peaces) {
			result.put(peace.substring(1, peace.lastIndexOf(keyValueSplitRegex)), peace.substring(peace.lastIndexOf(keyValueSplitRegex) + keyValueSplitRegex.length(), peace.length() - 1));
		}
		return result;
	}
}
