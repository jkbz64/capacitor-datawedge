package com.jkbz.capacitor.datawedge;

import android.os.Bundle;
import com.getcapacitor.*;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;

import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.ActivityNotFoundException;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Iterator;


@CapacitorPlugin(name = "DataWedge")
public class DataWedgePlugin extends Plugin {

    private final DataWedge implementation = new DataWedge();

    @PluginMethod
    public void enable(PluginCall call) {
        Intent intent = implementation.enable();

        try {
            broadcast(intent);
        } catch (ActivityNotFoundException e) {
            call.reject("DataWedge is not installed or not running");
        }
    }
    @PluginMethod
    public void disable(PluginCall call) {
        Intent intent = implementation.disable();

        try {
            broadcast(intent);
        } catch (ActivityNotFoundException e) {
            call.reject("DataWedge is not installed or not running");
        }
    }

    @PluginMethod
    public void enableScanner(PluginCall call) {
        Intent intent = implementation.enableScanner();

        try {
            broadcast(intent);
        } catch (ActivityNotFoundException e) {
            call.reject("DataWedge is not installed or not running");
        }
    }

    @PluginMethod
    public void disableScanner(PluginCall call) {
        Intent intent = implementation.disableScanner();

        try {
            broadcast(intent);
        } catch (ActivityNotFoundException e) {
            call.reject("DataWedge is not installed or not running");
        }
    }

    @PluginMethod
    public void startScanning(PluginCall call) {
         Intent intent = implementation.startScanning();

         try {
            broadcast(intent);
         } catch (ActivityNotFoundException e) {
            call.reject("DataWedge is not installed or not running");
         }
    }

    @PluginMethod
    public void stopScanning(PluginCall call) {
        Intent intent = implementation.stopScanning();

        try {
            broadcast(intent);
        } catch (ActivityNotFoundException e) {
            call.reject("DataWedge is not installed or not running");
        }
    }

    @PluginMethod
    public void sendBroadcastWithExtras(PluginCall call) throws JSONException {
        try {
            String action = call.getString("action");
            Intent intent = new Intent();

            if (action != null) {
                intent.setAction(action);
            }

            JSObject extrasObject = call.getObject("extras");

            if (extrasObject != null) {
                Bundle extrasBundle = DataWedge.toBundle(extrasObject);
                intent.putExtras(extrasBundle);
            }

            getContext().sendBroadcast(intent);
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getMessage());
        }
    }

    @PluginMethod
    public void __registerReceiver(PluginCall call) {
        if (isReceiverRegistered) return;

        Context context = getBridge().getContext();
        try {
            IntentFilter filter = new IntentFilter(DataWedge.DATAWEDGE_INPUT_FILTER);
            context.registerReceiver(broadcastReceiver, filter);
            isReceiverRegistered = true;
        } catch(Exception e) {
            Log.d("Capacitor/DataWedge", "Failed to register event receiver");
        }
    }

    private void broadcast(Intent intent) {
        Context context = getBridge().getContext();
        context.sendBroadcast(intent);
    }

    private boolean isReceiverRegistered = false;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (!action.equals(DataWedge.DATAWEDGE_INPUT_FILTER)) return;

            try {
                String data = intent.getStringExtra("com.symbol.datawedge.data_string");
                String type = intent.getStringExtra("com.symbol.datawedge.label_type");

                JSObject ret = new JSObject();
                ret.put("data", data);
                ret.put("type", type);

                notifyListeners("scan", ret);
            } catch(Exception e) {}
        }
    };
    @PluginMethod
    public void registerBroadcastReceiver(PluginCall call) throws JSONException {
        try {
            // Unregister any existing receiver to avoid conflicts
            unregisterReceiver(genericReceiver);

            // Create an IntentFilter to specify which broadcasts to listen for
            IntentFilter filter = new IntentFilter();

            if (call.hasOption("filterActions")) {
                JSArray filterActions = call.getArray("filterActions");
                if (filterActions != null) {
                    for (int i = 0; i < filterActions.length(); i++) {
                        filter.addAction(filterActions.getString(i));
                    }
                }
            }

            if (call.hasOption("filterCategories")) {
                JSArray filterCategories = call.getArray("filterCategories");
                if (filterCategories != null) {
                    for (int i = 0; i < filterCategories.length(); i++) {
                        filter.addCategory(filterCategories.getString(i));
                    }
                }
            }

            // Register the broadcast receiver with the specified filter
            getContext().registerReceiver(genericReceiver, filter);

            // Resolve the plugin call
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getMessage());
        }
    }

    private final BroadcastReceiver genericReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            JSObject ret = new JSObject();
            ret.put("action", action);

            // Check if extras is null before attempting to get keys
            JSObject extras = new JSObject();
            if (intent.getExtras() != null) {
                extras = DataWedge.bundleToJSObject(intent.getExtras());
            }

            ret.put("extras", extras);

            notifyListeners("broadcast", ret);
        }

    };


    private void unregisterReceiver(BroadcastReceiver receiver) {
        try
        {
            getContext().unregisterReceiver(receiver);
        }
        catch (IllegalArgumentException e)
        {
            Log.e("Capacitor/DataWedge", e.getMessage());
        }
    }
}
