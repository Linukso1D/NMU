package transport.item.fragments;

import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.nmu.mainmenu.R;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TransportItemMap extends Fragment implements OnItemClickListener {
	private ListView list;
	private MapView mapView;
	private GoogleMap map;
	private Bundle bundle;
	private String mask;
	public String ID_CLICK_VALUE = "ID_CLICK";
	public String CURRENT_TR = "CURRENT_TRANSPORT";
	private int currentClickedItem;

	@Override
	public void onStart() {
		super.onStart();
		list = (ListView) getActivity().findViewById(R.id.list_routes);
		list.setOnItemClickListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.activity_transport_item_map_fragment,
						container, false);
		// Устанавливаем маску для инициализации маршрутов и флагов
		setMask();
		MapsInitializer.initialize(getActivity());
		if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) == ConnectionResult.SUCCESS) {
			mapView = (MapView) v.findViewById(R.id.map);
			mapView.onCreate(savedInstanceState);
			if (mapView != null) {
				map = mapView.getMap();
				addPolygone(map, "geo_polygon");
				// mapView.getMapAsync(this);
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
						48.45417, 35.061754), 13.0f));
				if (savedInstanceState != null) {
					int savedId = savedInstanceState.getInt(ID_CLICK_VALUE);
					if(savedId < 4 && mask.equals("elbus_") || savedId < 4 && mask.equals("tram_")
							|| mask.equals("bus_")){
						addPolyline(map, "geo_" + mask + (savedId + 1));
						addMarkers(map, "points_" + mask + (savedId + 1));
					}
				}
			}
		}
		return v;
	}

	public void addMarkers(GoogleMap map_arg, String arrayName) {
		List<LatLng> init = initGeoByResName(arrayName);
		for (int count = 0; count < init.size(); count++) {
			MarkerOptions mrk = new MarkerOptions();
			mrk.position(init.get(count))
					.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
					.title("" + (count + 1));
			map_arg.addMarker(mrk);
		}

	}

	public void addPolyline(GoogleMap map_arg, String arrayName) {
		List<LatLng> init = initGeoByResName(arrayName);
		map_arg.addPolyline(new PolylineOptions().addAll(init).color(
				Color.MAGENTA));
	}

	public void addPolygone(GoogleMap map_arg, String arrayName) {
		List<LatLng> init = initGeoByResName(arrayName);
		map_arg.addPolygon(new PolygonOptions()
				.addAll(init)
				.strokeColor(
						getResources().getColor(R.color.tr_plygon_stroke_color))
				.fillColor(
						getResources().getColor(R.color.tr_plygon_fill_color)));
		MarkerOptions mark = new MarkerOptions();
		mark.position(new LatLng(48.454261, 35.06147))
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED))
				.title(getResources()
						.getString(R.string.tran_marker_title_main))
				.snippet(
						getResources()
								.getString(R.string.tran_marker_title_sub));
		map.addMarker(mark).isInfoWindowShown();
	}

	public List<LatLng> initGeoByResName(String resName) {
		int arrayResourceId = this.getResources().getIdentifier(resName,
				"array", getActivity().getPackageName());
		Resources resources = getResources();
		TypedArray geo = resources.obtainTypedArray(arrayResourceId);
		List<LatLng> points = new ArrayList<LatLng>(geo.length() / 2);
		for (int i = 0; i < geo.length(); i += 2) {
			points.add(new LatLng(geo.getFloat(i, 0.0f), geo.getFloat(i + 1,
					0.0f)));
		}
		geo.recycle();
		return points;
	}

	@Override
	public void onResume() {
		super.onResume();
		if(mapView!= null)
		mapView.onResume();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if(mapView!= null)
		mapView.onDestroy();
	}

	public void setMask() {
		bundle = getArguments();
		if (bundle != null) {
			int res = bundle.getInt("START_ITEM");
			Log.e("BL", "" + res);
			switch (res) {
			case 1:
				mask = "bus_";
				break;
			case 2:
				mask = "elbus_";
				break;
			case 3:
				mask = "tram_";
				break;
			default:
				mask = "bus_";
				break;
			}
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		if(mapView!= null)
		mapView.onLowMemory();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(ID_CLICK_VALUE, currentClickedItem);
		outState.putString(CURRENT_TR, mask);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		view.setSelected(true);
		Log.i("BL", "id = " + id);
		currentClickedItem = (int) id;
		if(map != null){
		map.clear();
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.45417,
				35.061754), 13.0f));
		addPolygone(map, "geo_polygon");
		Log.i("BL", "currentClickedItem (onItemClick)- "+currentClickedItem);
		addPolyline(map, "geo_" + mask + (currentClickedItem + 1));
		addMarkers(map, "points_" + mask + (currentClickedItem + 1));
		}
		
	}
}
