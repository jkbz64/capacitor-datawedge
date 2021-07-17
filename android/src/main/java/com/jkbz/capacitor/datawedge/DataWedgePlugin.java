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

    public DataWedgePlugin() {
        registerReceiver();
    }

    @PluginMethod
    public void enable(PluginCall call) {
        Intent intent = implementation.enable();
        Context context = getBridge().getContext();

        try {
            context.sendBroadcast(intent);
        } catch (ActivityNotFoundException e) {
            call.reject("DataWedge is not installed or not running");
        }
    }
    @PluginMethod
    public void disable(PluginCall call) {
        Intent intent = implementation.disable();
        Context context = getBridge().getContext();

        try {
            context.sendBroadcast(intent);
        } catch (ActivityNotFoundException e) {
            call.reject("DataWedge is not installed or not running");
        }
    }

    @PluginMethod
    public void enableScanner(PluginCall call) {
        Intent intent = implementation.enableScanner();
        Context context = getBridge().getContext();

        try {
            context.sendBroadcast(intent);
        } catch (ActivityNotFoundException e) {
            call.reject("DataWedge is not installed or not running");
        }
    }
    @PluginMethod
    public void disableScanner(PluginCall call) {
        Intent intent = implementation.disableScanner();
        Context context = getBridge().getContext();

        try {
            context.sendBroadcast(intent);
        } catch (ActivityNotFoundException e) {
            call.reject("DataWedge is not installed or not running");
        }
    }

    private void registerReceiver() {
        Context context = getBridge().getContext();

        IntentFilter filter = new IntentFilter(DataWedge.DATAWEDGE_INPUT_FILTER);
        context.registerReceiver(broadcastReceiver, filter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(DataWedge.DATAWEDGE_INPUT_FILTER)) {
                try {
                    String data = intent.getStringExtra("com.symbol.datawedge.data_string");

                    JSObject ret = new JSObject();
                    ret.put("data", data);

                    notifyListeners("scan", ret);
                } catch(Exception e) {}
            }
        }
    };
}