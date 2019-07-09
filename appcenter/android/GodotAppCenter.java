package org.godotengine.godot;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.util.Log;

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
    
    public boolean isEnabled(){
        return AppCenter.isEnabled().get();
    }

    public void setEnabled(boolean value){
        AppCenter.setEnabled(value);
    }

    public void setUserId(String userId){
        AppCenter.setUserId(userId);
    }

    public void setLogLevel(int level){
        AppCenter.setLogLevel(level);
    }

    public void analyticsTrackSimpleEvent(String name){
        Log.d("godot", "AppCenter.Analytics: SimpleEvent( " + name + ")");
        Analytics.trackEvent(name);
    }

    public void analyticsTrackComplexEvent(String name, String jsonProperties){
        Log.d("godot", "AppCenter.Analytics: ComplexEvent( " + name + " : " + jsonProperties + ")");
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

    public static void triggerRebirth(Context context) {
        Log.d("godot","Triggered rebirth");
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        System.exit(0);
    }

    static public Godot.SingletonBase initialize(Activity activity)
    {
        SharedPreferences prefs = activity.getSharedPreferences("godot_app_center", Context.MODE_PRIVATE);
        if (prefs.getString("api_key",null) == null){
            String api_key = GodotLib.getGlobal("app_center/api_key_android");
            Log.d("godot","Initialized api_key from godot: " + api_key);
            Editor editor = prefs.edit();
            editor.putString("api_key", api_key);
            editor.commit();
            triggerRebirth(activity);
        }
        return new GodotAppCenter(activity);
    }

   public GodotAppCenter(Activity p_activity) {
       registerClass("AppCenter", new String[] {
           "isEnabled","setEnabled",
           "setUserId",
           "setLogLevel",
           "analyticsTrackSimpleEvent","analyticsTrackComplexEvent",
       });
       activity = p_activity;
   }

}