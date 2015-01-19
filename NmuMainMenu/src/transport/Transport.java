package transport;

import transport.item.TransportItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.nmu.mainmenu.R;



public class Transport extends Activity implements OnClickListener {

	private Button busButton;
	private Button elbusButton;
	private Button tramButton;
	private ImageView imgBusButton;
	private ImageView imgElbusButton;
	private ImageView imgTramButton;
	public final static int BUS = 1;
	public final static int ELBUS = 2;
	public final static int TRAM = 3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transport);
		busButton = (Button)findViewById(R.id.transport_bus_button_id);
		elbusButton = (Button)findViewById(R.id.transport_elbus_button_id);
		tramButton = (Button)findViewById(R.id.transport_tram_button_id);
		imgBusButton = (ImageView)findViewById(R.id.transport_bus_image_id);
		imgElbusButton = (ImageView)findViewById(R.id.transport_elbus_image_id);
		imgTramButton = (ImageView)findViewById(R.id.transport_tram_image_id);
		busButton.setOnClickListener(this);
		elbusButton.setOnClickListener(this);
		tramButton.setOnClickListener(this);
		imgBusButton.setOnClickListener(this);
		imgElbusButton.setOnClickListener(this);
		imgTramButton.setOnClickListener(this);
		float scale = getResources().getDisplayMetrics().density;
		Log.wtf("WTF", "Scale = "+scale);
	}
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = null;
		intent = new Intent(this, TransportItem.class);
		if(id == R.id.transport_bus_button_id || id == R.id.transport_bus_image_id){
			intent.putExtra("transport", BUS);
		}
		if(id == R.id.transport_elbus_button_id || id == R.id.transport_elbus_image_id){
			intent.putExtra("transport", ELBUS);
		}
		if(id == R.id.transport_tram_button_id || id == R.id.transport_tram_image_id){
			intent.putExtra("transport", TRAM);
		}
		if(intent != null)
		startActivity(intent);
	}
}
