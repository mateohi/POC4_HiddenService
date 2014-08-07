package com.ic.banking.glass.poc4_hiddenservice;

import android.content.Context;
import android.content.Intent;

public class GlassDialog {

    public static void warning(Context context, String message) {
        Intent warningIntent = new Intent(context, DialogActivity.class);
        warningIntent.putExtra(DialogActivity.TYPE, DialogActivity.WARNING);
        warningIntent.putExtra(DialogActivity.MESSAGE, message);
        warningIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(warningIntent);
    }

    public static void info(Context context, String message) {
        Intent infoIntent = new Intent(context, DialogActivity.class);
        infoIntent.putExtra(DialogActivity.TYPE, DialogActivity.INFO);
        infoIntent.putExtra(DialogActivity.MESSAGE, message);
        infoIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(infoIntent);
    }

    public static void done(Context context, String message) {
        Intent doneIntent = new Intent(context, DialogActivity.class);
        doneIntent.putExtra(DialogActivity.TYPE, DialogActivity.DONE);
        doneIntent.putExtra(DialogActivity.MESSAGE, message);
        doneIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(doneIntent);
    }
}
