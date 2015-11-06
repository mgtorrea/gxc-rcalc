package gxc.rcalc.crawlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for extracting/cleaning/parsing/formatting html text
 * @author fcisneros
 *
 */
public class CrawlUtils {
	
	public static Pattern INTEGER_PATTERN = Pattern.compile("-?\\d+");
	
	public static Integer extractInteger(String str) {
		Matcher m = INTEGER_PATTERN.matcher(str);
		if(m.find()) {
			return Integer.valueOf(m.group());
		} 
		
		return null;
	}

}
