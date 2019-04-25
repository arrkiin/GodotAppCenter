package org.godotengine.godot;

import android.app.Activity;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class GodotAppCenter extends Godot.SingletonBase
{

    private Activity activity = null; // The main activity of the game
    
    public void start(String api_key){
        AppCenter.start(activity.getApplication(), api_key, Analytics.class, Crashes.class);
    }

    public boolean isEnabled(){
        return AppCenter.isEnabled().get();
    }

    public void setEnabled(boolean value){
        AppCenter.setEnabled(value);
    }

    public void setUserId(String userId){
        AppCenter.setUserId(userId);
    }

    public void analyticsTrackEvent(String name, String jsonProperties){
        if (jsonProperties == null){
            Analytics.trackEvent(name);
        } else {
            try {
                Map<String, String> properties = new HashMap<>();
                JSONObject o = new JSONObject(jsonProperties);
                Iterator<String> keys = o.keys();
                while(keys.hasNext()){
                    String key = keys.next();
                    properties.put(key,o.getString(key));
                }
                Analytics.trackEvent(name,properties);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    public void crashesGenerateTestCrash(){
        Crashes.generateTestCrash();
    }

    static public Godot.SingletonBase initialize(Activity activity)
    {
        return new GodotAppCenter(activity);
    }

   public GodotAppCenter(Activity p_activity) {
       registerClass("AdMob", new String[] {
           "start",
           "isEnabled","setEnabled",
           "setUserId",
           "analyticsTrackEvent",
           "crashesGenerateTestCrash"
       });
       activity = p_activity;
   }

}