package rembo.network.urss;

import java.util.ArrayList;

public class addrss implements Runnable{
private  RSSactivity T;
	public addrss (RSSactivity H)
	{
		T=H;
	}
	@Override
	public void run() {
		
		ArrayList<RssItem> newItems = RssItem.getRssItems("http://www.nmu.org.ua/ua/content/news/?rss=y");
		
		T.AddItems(newItems);
		
	}

}
