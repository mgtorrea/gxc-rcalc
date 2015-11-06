package gxc.rcalc.crawlers;

/**
 * Base Class for Crawling Tasks
 * 
 * @author fcisneros
 *
 */
public abstract class CrawlerTask implements Runnable {
	
	@Override
	public void run() {
		// Entry point for all Crawlers.
		// TODO: define if input parameters and results should be handled here or delegate to each impl
		doCrawl();
	}
	
	public abstract void doCrawl();

}
