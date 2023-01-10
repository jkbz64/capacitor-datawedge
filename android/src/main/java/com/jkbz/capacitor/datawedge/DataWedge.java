package com.jkbz.capacitor.datawedge;

import android.util.Log;
import android.content.Intent;

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

    public Intent enumerateScanners(){
        Intent intent = new Intent();
        intent.setAction("com.symbol.datawedge.api.ACTION");
        intent.putExtra("com.symbol.datawedge.api.ENUMERATE_SCANNERS", "");

        return intent;
    }
}

