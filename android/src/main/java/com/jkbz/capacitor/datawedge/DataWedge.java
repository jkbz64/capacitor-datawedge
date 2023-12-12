package com.jkbz.capacitor.datawedge;

import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class DataWedge {
    public static final String DATAWEDGE_PACKAGE = "com.symbol.datawedge.api";
    public static final String DATAWEDGE_INPUT_FILTER = "com.capacitor.datawedge.RESULT_ACTION";

    public Intent enable() {
        Intent intent = new Intent();
        intent.setAction(DATAWEDGE_PACKAGE + ".ACTION");
        intent.putExtra("com.symbol.datawedge.api.ENABLE_DATAWEDGE", true);

        return intent;
    }

    public Intent disable() {
        Intent intent = new Intent();
        intent.setAction(DATAWEDGE_PACKAGE + ".ACTION");
        intent.putExtra("com.symbol.datawedge.api.ENABLE_DATAWEDGE", false);

        return intent;
    }

    public Intent enableScanner() {
        Intent intent = new Intent();
        intent.setAction(DATAWEDGE_PACKAGE + ".ACTION");
        intent.putExtra("com.symbol.datawedge.api.SCANNER_INPUT_PLUGIN", "ENABLE_PLUGIN");

        return intent;
    }

    public Intent disableScanner() {
        Intent intent = new Intent();
        intent.setAction(DATAWEDGE_PACKAGE + ".ACTION");
        intent.putExtra("com.symbol.datawedge.api.SCANNER_INPUT_PLUGIN", "DISABLE_PLUGIN");

        return intent;
    }

    public Intent startScanning() {
        Intent intent = new Intent();
        intent.setAction(DATAWEDGE_PACKAGE + ".ACTION");
        intent.putExtra("com.symbol.datawedge.api.SOFT_SCAN_TRIGGER", "START_SCANNING");

        return intent;
    }

    public Intent stopScanning() {
        Intent intent = new Intent();
        intent.setAction(DATAWEDGE_PACKAGE + ".ACTION");
        intent.putExtra("com.symbol.datawedge.api.SOFT_SCAN_TRIGGER", "STOP_SCANNING");

        return intent;
    }

    public static Bundle toBundle(JSObject json) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator<String> keys = json.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            Object value = json.get(key);

            if (value instanceof Boolean) {
                bundle.putBoolean(key, (Boolean) value);
            } else if (value instanceof Integer) {
                bundle.putInt(key, (Integer) value);
            } else if (value instanceof Long) {
                bundle.putLong(key, (Long) value);
            } else if (value instanceof Double) {
                bundle.putDouble(key, (Double) value);
            } else if (value instanceof String) {
                bundle.putString(key, (String) value);
            } else if (value instanceof JSObject) {
                bundle.putBundle(key, toBundle((JSObject) value));
            } else if (value instanceof JSArray) {
                bundle.putString(key, value.toString());
            }
            // Handle other data types as needed
        }

        return bundle;
    }
    public static JSObject bundleToJSObject(Bundle bundle) {
        JSObject jsObject = new JSObject();

        for (String key : bundle.keySet()) {
            Object value = bundle.get(key);

            if (value instanceof Boolean) {
                jsObject.put(key, (Boolean) value);
            } else if (value instanceof Integer) {
                jsObject.put(key, (Integer) value);
            } else if (value instanceof Long) {
                jsObject.put(key, (Long) value);
            } else if (value instanceof Double) {
                jsObject.put(key, (Double) value);
            } else if (value instanceof String) {
                jsObject.put(key, (String) value);
            } else if (value instanceof Bundle) {
                jsObject.put(key, bundleToJSObject((Bundle) value));
            } else {
                jsObject.put(key, value != null ? value.toString() : JSONObject.NULL);
            }
        }

        return jsObject;
    }
}

