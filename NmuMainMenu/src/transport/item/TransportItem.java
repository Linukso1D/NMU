package transport.item;

import java.util.ArrayList;
import java.util.List;

import transport.Transport;
import transport.bus.adapters.TransportFragmentPageAdapter;
import transport.item.fragments.TransportItemList;
import transport.item.fragments.TransportItemMap;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.nmu.mainmenu.R;



public class TransportItem extends FragmentActivity implements OnClickListener {
	static final String TAG = "TransportItem";
	private ViewPager pager;
    private TransportFragmentPageAdapter pagerAdapter;
    private List<Fragment> tabs;
    private Bundle bundle;
    private Button busButton;
	private Button elbusButton;
	private Button tramButton;
	private TextView titleText;
	private int currentTransport;
    TransportItemList trl;
    TransportItemMap trm;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport_item);
		currentTransport = getIntent().getIntExtra("transport",transport.Transport.BUS);
		busButton = (Button)findViewById(R.id.tran_item_button_bus);
		elbusButton = (Button)findViewById(R.id.tran_item_button_elbus);
		tramButton = (Button)findViewById(R.id.tran_item_button_tram);
		titleText = (TextView)findViewById(R.id.transport_title_id);
		busButton.setOnClickListener(this);
		elbusButton.setOnClickListener(this);
		tramButton.setOnClickListener(this);
		tabs  = new ArrayList<Fragment>();
		checkButtonVisible(currentTransport);
		trl = new TransportItemList();
		trm = new TransportItemMap();
		bundle = new Bundle();
	    bundle.putInt("START_ITEM", currentTransport);
	    trl.setArguments(bundle);
	    trm.setArguments(bundle);
		tabs.add(trl);
		tabs.add(trm);
		if(savedInstanceState!=null){
			titleText.setText(savedInstanceState.getString("LAST_TITLE"));
		}
		int orientation = getResources().getConfiguration().orientation;
			pager = (ViewPager) findViewById(R.id.tran_view_pager);
			pagerAdapter = new TransportFragmentPageAdapter(getSupportFragmentManager());
			pagerAdapter.addTabs(tabs);
			pagerAdapter.setOrientationState(orientation);
			pager.setAdapter(pagerAdapter);
	}
	public void onConfigurationChanged(Configuration _newConfig) {
        if (_newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
           Log.wtf("WTF", "ORIENTATION_LANDSCAPE");
           pagerAdapter.setOrientationState(Configuration.ORIENTATION_LANDSCAPE);
           pager.setAdapter(pagerAdapter);
        }

        if (_newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
        	Log.wtf("WTF", "ORIENTATION_PORTRAIT");
        	 pagerAdapter.setOrientationState(Configuration.ORIENTATION_PORTRAIT);
        	 pager.setAdapter(pagerAdapter);
        }

        super.onConfigurationChanged(_newConfig);
    }
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("LAST_TITLE", (String)titleText.getText());
	}
	private void checkButtonVisible(int transport){
		switch(transport){
		case Transport.BUS:
			titleText.setText(R.string.tran_item_title_text_bus);
			busButton.setVisibility(android.view.View.GONE);
			elbusButton.setVisibility(android.view.View.VISIBLE);
			tramButton.setVisibility(android.view.View.VISIBLE);
			break;
		case Transport.ELBUS:
			titleText.setText(R.string.tran_item_title_text_elbus);
			busButton.setVisibility(android.view.View.VISIBLE);
			elbusButton.setVisibility(android.view.View.GONE);
			tramButton.setVisibility(android.view.View.VISIBLE);
			break;
		case Transport.TRAM:
			titleText.setText(R.string.tran_item_title_text_tram);
			busButton.setVisibility(android.view.View.VISIBLE);
			elbusButton.setVisibility(android.view.View.VISIBLE);
			tramButton.setVisibility(android.view.View.GONE);
			break;
		}
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch(id){
		case R.id.tran_item_button_bus:
			 bundle.putInt("START_ITEM", transport.Transport.BUS);
			 checkButtonVisible(transport.Transport.BUS);
			break;
		case R.id.tran_item_button_elbus:
			 bundle.putInt("START_ITEM", transport.Transport.ELBUS);
			 checkButtonVisible(transport.Transport.ELBUS);
			break;
		case R.id.tran_item_button_tram:
			bundle.putInt("START_ITEM", transport.Transport.TRAM);
			checkButtonVisible(transport.Transport.TRAM);
			break;	
		}
		 trl = new TransportItemList();
		 trl.setArguments(bundle);
		 pager.setAdapter(pagerAdapter);
	}
}
