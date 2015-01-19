package transport.bus.adapters;

import java.util.List;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class TransportFragmentPageAdapter extends FragmentStatePagerAdapter {

	
	public static final int PAGE_COUNT = 2;
	private int orientationState;
	private List<Fragment> tabs;

	public void addTabs(List<Fragment> tabs) {
		this.tabs = tabs;
	}
	
	public void setOrientationState(int state){
		orientationState = state;
	}
	public int getOrientationState(){
		return orientationState;
	}
	public TransportFragmentPageAdapter(FragmentManager fm) {
		super(fm);
	}
	
	
	@Override
	public float getPageWidth(int position) {
		if(orientationState == Configuration.ORIENTATION_PORTRAIT){
			return (0.95f);
		}
		if(orientationState == Configuration.ORIENTATION_LANDSCAPE){
			return (0.5f);
		}
		return (0.95f);
	}
	
	@Override
	public Fragment getItem(int arg0) {
		return tabs.get(arg0);
	}
	
	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

}
