package rembo.network.urss;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.nmu.mainmenu.R;
public class RssItemDisplayer extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.rss_item_displayer);

    RssItem selectedRssItem = RSSactivity.selectedRssItem;
    //Bundle extras = getIntent().getExtras();
    TextView titleTv = (TextView)findViewById(R.id.titleTextView);
    TextView contentTv = (TextView)findViewById(R.id.contentTextView);

    String title = "";
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd - hh:mm:ss");
    title = "\n" + selectedRssItem.getTitle() + "  ( "
        + sdf.format(selectedRssItem.getPubDate()) + " )\n\n";

    String content = "";
    content += selectedRssItem.getDescription() + "\n"
        + selectedRssItem.getLink();

    titleTv.setText(title);
    contentTv.setText(content);
  }
}