package com.sinfotech.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import com.pgyersdk.feedback.PgyFeedback;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;


public class PgyerPlugin extends CordovaPlugin {
  private static String TAG = "PgyerPlugin";


  @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

      String androidAppId = "";
      try {
        ApplicationInfo applicationInfo = this.cordova.getActivity().getPackageManager().getApplicationInfo(this.cordova.getActivity().getPackageName(), PackageManager.GET_META_DATA);
        Bundle bundle = applicationInfo.metaData;
        androidAppId = bundle.getString("PGYER_APP_ID");
      } catch (NameNotFoundException e) {
        Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
      } catch (NullPointerException e) {
        Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
      }

      if(androidAppId.equals("")){
        return false;
      }

      final String appId = androidAppId;

      if (action.equals("popup")) {
        PgyFeedback.getInstance().show(this.cordova.getActivity(), appId);
        callbackContext.success("");
        return true;
      } else {
        return false;
      }
    }
}
