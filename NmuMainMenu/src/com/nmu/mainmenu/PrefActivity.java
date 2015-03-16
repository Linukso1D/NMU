package com.nmu.mainmenu;


import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class PrefActivity extends PreferenceActivity {


@Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.pref);
    
    Preference button = (Preference)findPreference("theme");
    button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) { 
                   	Intent intent_themes = new Intent(getApplicationContext(), themes.class);
    	        		startActivity(intent_themes);
                        return true;
                    }
                });
            	            	
       
    }
  }
