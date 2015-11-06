package gxc.rcalc.crawlers.apestan;

import gxc.rcalc.crawlers.CrawlUtils;
import gxc.rcalc.crawlers.CrawlerTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * CrawlerTask implementation for apestan.com site crawling
 * 
 * @author fcisneros
 *
 */
public class ApestanCrawlerTask extends CrawlerTask {
	
	public static final String BASE_URL = "https://www.apestan.com/lista-completa-de-companias.html/period_year";
		

	@Override
	public void doCrawl() {
		try {
			Document doc = Jsoup.connect(BASE_URL)
					.userAgent("Mozilla")
					.get();
			
			// Extract company elements
			Elements companies = doc.select("div#content ul.userList li");
			for(Element i: companies) {
				for(Entry<String,Object> c : crawlCompany(i).entrySet() ) {
					System.out.println(c.getKey() + "=" + c.getValue());
				}
			}
		} catch (IOException e) {
			// TODO: LOG ERROR
			e.printStackTrace();
		}
		
	}
	
	private Map<String,Object> crawlCompany(Element e) throws IOException {
		Element link = e.select("a[href]").first();
		String url = link.attr("abs:href");
		System.out.println(url);
		Document doc = Jsoup.connect(url)
				.userAgent("Mozilla")
				.get();
		
		Element companyElement = doc.select("div#content div.case_details span.count a[href]").first();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyName", companyElement.ownText());
		
		return map;
	}
	
	
	// FIXME: move this test out to unit testing
	public static void main(String[] args) {
		CrawlerTask task = new ApestanCrawlerTask();
		task.run();
	}

}
