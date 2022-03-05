package com.jkbz.capacitor.datawedge;

import com.getcapacitor.Plugin;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;

import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.ActivityNotFoundException;

import android.util.Log;
import android.os.Bundle;

import java.util.ArrayList;


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
    public boolean isScannerAvailable(PluginCall call) {
        return isDeviceAvailable;
    }


    @PluginMethod
    public void __enumerateScanners(PluginCall call) {
        Intent intent = implementation.enumerateScanners();

        try {
            broadcast(intent);
        } catch(ActivityNotFoundException e) {
            call.reject("DataWedge is not installed or not running");
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

    private static final String TAG = "MyActivity";
    public boolean isDeviceAvailable = false;
    private boolean isReceiverRegistered = false;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (!action.equals(DataWedge.DATAWEDGE_INPUT_FILTER)) return;

            if(action.equals("com.symbol.datawedge.api.RESULT_ACTION")){
                //
                // enumerate scanners
                //
                if(intent.hasExtra("com.symbol.datawedge.api.RESULT_ENUMERATE_SCANNERS")) {
                    ArrayList<Bundle> scannerList = (ArrayList<Bundle>) intent.getSerializableExtra("com.symbol.datawedge.api.RESULT_ENUMERATE_SCANNERS");
                    if((scannerList != null) && (scannerList.size() > 0)) {
                        for (Bundle bunb : scannerList){
                            String[] entry = new String[4];
                            entry[0] = bunb.getString("SCANNER_NAME");
                            entry[1] = bunb.getBoolean("SCANNER_CONNECTION_STATE")+"";
                            entry[2] = bunb.getInt("SCANNER_INDEX")+"";

                            entry[3] = bunb.getString("SCANNER_IDENTIFIER");

                            Log.d(TAG, "Scanner:" + entry[0]  + " Connection:" + entry[1] + " Index:" + entry[2] + " ID:" + entry[3]);
                            if(entry[1] == "true") {
                                isDeviceAvailable = true;
                            }
                        }
                    }
                }
            }

            try {
                String data = intent.getStringExtra("com.symbol.datawedge.data_string");

                JSObject ret = new JSObject();
                ret.put("data", data);

                notifyListeners("scan", ret);
            } catch(Exception e) {}
        }
    };
}