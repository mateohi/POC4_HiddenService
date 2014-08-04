package com.ic.banking.glass.poc4_hiddenservice;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.google.android.glass.app.Card;

public class GlassToast {

    public static Toast create(Context context, String message, int duration) {
        View view = new Card(context).setText(message).getView();

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.FILL, 0, 0);
        toast.setView(view);
        toast.setDuration(duration);

        return toast;
    }

    public static Toast createShort(Context context, String message) {
        return create(context, message, Toast.LENGTH_SHORT);
    }

    public static Toast createLong(Context context, String message) {
        return create(context, message, Toast.LENGTH_LONG);
    }
}
