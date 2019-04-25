package org.godotengine.godot;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class GodotAppCenter extends Godot.SingletonBase
{
    public void start(String api_key){
        AppCenter.start(getApplication(), "api_key", Analytics.class, Crashes.class);
    }

    public boolean isEnabled(){
        return AppCenter.isEnabled();
    }

    public void setEnabled(boolean value){
        AppCenter.setEnabled(value);
    }

    public void setUserId(String userId){
        AppCenter.setUserId(userId);
    }

    public void trackEvent(String name, String jsonProperties){
        if (properties == null){
            Analytics.trackEvent(name);
        } else {
            try {
                Map<String, String> properties = new HashMap<>();
                JSONObject o = new JSONObject(jsonProperties);
                Iterator<String> keys = o.keys();
                while(keys.hasNext()){
                    properties.put(keys.next(),o.getString(key));
                }
                Analytics.trackEvent(name,properties);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

}