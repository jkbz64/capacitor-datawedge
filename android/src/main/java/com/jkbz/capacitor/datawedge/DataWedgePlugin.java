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
    @Override
    public void addListener(PluginCall call) {
        if (!isReceiverRegistered) {
            registerReceiver();
            isReceiverRegistered = true;
        }

        super.addListener(call);
    }

    private void broadcast(Intent intent) {
        Context context = getBridge().getContext();
        context.sendBroadcast(intent);
    }

    private void registerReceiver() { 
        Context context = getBridge().getContext();
        try {
            IntentFilter filter = new IntentFilter(DataWedge.DATAWEDGE_INPUT_FILTER);
            context.registerReceiver(broadcastReceiver, filter);
        } catch(Exception e) {
            Log.d("Capacitor/DataWedge", "Failed to register event receiver");
        }
    }

    private boolean isReceiverRegistered = false;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (!action.equals(DataWedge.DATAWEDGE_INPUT_FILTER)) return;

            try {
                String data = intent.getStringExtra("com.symbol.datawedge.data_string");

                JSObject ret = new JSObject();
                ret.put("data", data);

                notifyListeners("scan", ret);
            } catch(Exception e) {}
        }
    };
}