package rembo.network.urss;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nmu.mainmenu.R;

public class RSSactivity extends Activity {

	public static RssItem selectedRssItem = null;
	String feedUrl = "http://www.nmu.org.ua/ua/content/news/?rss=y";
	ListView rssListView = null;
	ArrayList<RssItem> rssItems = new ArrayList<RssItem>();
	ArrayAdapter<RssItem> aa = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rss_s);
		Log.i("Информация", "RSSactivity коннект");
		// get textview from our layout.xml

		// get button from layout.xml

		// define the action that will be executed when the button is clicked.

		// get the listview from layout.xml
		rssListView = (ListView) findViewById(R.id.rssListView);
		// here we specify what to execute when individual list items clicked
		rssListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					// @Override
					public void onItemClick(AdapterView<?> av, View view,
							int index, long arg3) {
						selectedRssItem = rssItems.get(index);

						// we call the other activity that shows a single rss
						// item in
						// one page
						Intent intent = new Intent(
								"rembo.network.urss.displayRssItem");
						startActivity(intent);
					}
				});

		// adapters are used to populate list. they take a collection,
		// a view (in our example R.layout.list_item
		aa = new ArrayAdapter<RssItem>(this, R.layout.list_item, rssItems);
		// here we bind array adapter to the list
		rssListView.setAdapter(aa);

		refressRssList();
	}

	public void AddItems(final ArrayList<RssItem> newItems) {
		runOnUiThread(new Runnable() {
			public void run() {
			
				rssItems.clear();
				rssItems.addAll(newItems);
				
				aa.notifyDataSetChanged();

			}
		});
	}

	private void refressRssList() {

		

		new Thread (new addrss(this)).start(); 
	}

}