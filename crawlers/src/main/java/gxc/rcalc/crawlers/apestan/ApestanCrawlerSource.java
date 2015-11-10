package gxc.rcalc.crawlers.apestan;

import gxc.rcalc.crawlers.CrawlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.FlumeException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;

/**
 * Flume source implementation for apestan.com site crawling
 * 
 * @author fcisneros
 *
 */
public class ApestanCrawlerSource extends AbstractSource implements Configurable, PollableSource {
	
	public static final String COMPANIES_URL = 
			"https://www.apestan.com/lista-completa-de-companias.html/period_year";
	
	private Iterator<Element> companies;
	
	private Gson gson = new Gson();
	
	@Override
	public void configure(Context ctx) {
		
	}
	
	@Override
	public void start() {
		Document doc;
		try {
			doc = Jsoup.connect(COMPANIES_URL).userAgent("Mozilla").get();
			// Extract company elements
			this.companies = doc.select("div#content ul.userList li").iterator();
			
		} catch (IOException e) {
			throw new FlumeException("Could not retrieve Companies list",e);
		}
	}
	
	@Override
	public void stop() {
		
	}

	@Override
	public Status process() throws EventDeliveryException {
		if(!companies.hasNext()) {
			stop(); // is manual stop valid ?
			return Status.BACKOFF;
		}
		
		Status status = null;
		try {
			// Poll data
			
			Map<String,Object> map = crawlCompany(companies.next());
			String body = gson.toJson(map);
			Event e = EventBuilder.withBody(body.getBytes());

			// Store the Event into this Source's associated Channel(s)
			getChannelProcessor().processEvent(e);

			status = Status.READY;
		} catch (Throwable t) {
			status = Status.BACKOFF;
			
			// re-throw all Errors
			if (t instanceof Error) {
				throw (Error) t;
			}
		} finally {
			
		}
		
		return status;	
	}
	
	private Map<String,Object> crawlCompany(Element e) throws IOException {
		Element link = e.select("a[href]").first();
		String companyName = link.attr("title");		
		String url = link.attr("abs:href");
		List<Integer> ratings = crawlComplains(url);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("companyName", companyName);
		map.put("complainCount", ratings.size());
		map.put("ratings", ratings);
		
		return map;
	}
	
	private List<Integer> crawlComplains(String url) throws IOException {
		Document doc = Jsoup.connect(url)
				.userAgent("Mozilla")
				.get();
		
		List<Integer> ratings = new ArrayList<Integer>();
		for(Element e : doc.select("div.rating span")) {
			Integer rating = CrawlUtils.extractInteger(e.className());
			ratings.add((rating != null) ? rating : 0);
		}

		return ratings; // TODO: Add recursion for next links
	}

}
