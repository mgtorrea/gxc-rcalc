package gxc.rcalc.rest.util;

import org.springframework.util.StringUtils;

public class TextUtils {
	
	/**
	 * Normalizes the given string
	 * @param name
	 * @return
	 */
	public static String normalize(String name) {
		if(StringUtils.hasText(name)) {
			// TODO: add encoding check and non utf characters removal
			return name.toLowerCase().trim();
		}
		
		return "";
	}
	
	/**
	 * Calculates the Levenshtein distance of the given strings
	 * @param a
	 * @param b
	 * @return
	 */
	public static int levenshtein(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
	
	public static String coalesce(String... str) {
		for(String s : str ) {
			if(s != null) {
				return s;
			}
		}
		
		return null;
	}

}
