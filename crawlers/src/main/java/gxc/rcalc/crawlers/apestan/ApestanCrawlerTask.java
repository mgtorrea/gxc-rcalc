package gxc.rcalc.crawlers.apestan;

import gxc.rcalc.crawlers.CrawlUtils;
import gxc.rcalc.crawlers.CrawlerTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	
	public static final String COMPANIES_URL = 
			"https://www.apestan.com/lista-completa-de-companias.html/period_year";
		

	@Override
	public void doCrawl() {
		try {
			Document doc = Jsoup.connect(COMPANIES_URL)
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
		String companyName = link.attr("title");
		System.out.println(url);
				
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyName", companyName);
		map.put("complainCount", crawlComplains(url).size());
		
		return map;
	}
	
	private List<Integer> crawlComplains(String url) throws IOException {
		Document doc = Jsoup.connect(url)
				.userAgent("Mozilla")
				.get();
		
		List<Integer> ratings = new ArrayList<Integer>();
		for(Element e : doc.select("div.rating span")) {
			Integer rating = CrawlUtils.extractInteger(e.ownText());
			ratings.add((rating != null) ? rating : 0);
		}

		return ratings; // TODO: Add recursion for next links
	}
	
	
	// FIXME: move this test out to unit testing
	public static void main(String[] args) {
		CrawlerTask task = new ApestanCrawlerTask();
		task.run();
	}

}
