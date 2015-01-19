package transport.item.fragments;

import com.nmu.mainmenu.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TransportItemList extends Fragment {

	private ListView list;
	private String[] routes;
	private View rootView;
	private ArrayAdapter<String> adapter;

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// outState.putString(KEY_TEXT_VALUE, mTextView.getText());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("BL", "TransportItemList onCreateView");
		rootView = inflater.inflate(
				R.layout.activity_transport_item_list_fragment, container,
				false);

		Bundle bundle = getArguments();
		if (bundle != null) {
			int res = bundle.getInt("START_ITEM");
			Log.e("BL", "" + res);
			switch (res) {
			case 1:
				routes = getResources()
						.getStringArray(R.array.bus_routes_array);
				break;
			case 2:
				routes = getResources().getStringArray(
						R.array.elbus_routes_array);
				break;
			case 3:
				routes = getResources().getStringArray(
						R.array.trams_routes_array);
				break;
			}
		}
		list = (ListView) rootView.findViewById(R.id.list_routes);
		adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.routes_list_item, routes);
		list.setAdapter(adapter);
		return rootView;
	}
}
