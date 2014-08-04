package com.ic.banking.glass.poc4_hiddenservice;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.google.android.glass.app.Card;

public class GraphTask extends AsyncTask<String, Void, String> {

    private static final String TAG = GraphTask.class.getSimpleName();

    private Context context;

    public GraphTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String user = params[0];
        String userName = FacebookGraphService.instance().getUser(user).getName();

        return userName;
    }

    @Override
    protected void onPostExecute(String userName) {
        String message = null;
        if (userName == null) {
            message = "Something went wrong...";
        }
        else {
            message = "Username:\n" + userName;
            // context.startActivity(new Intent(this.context, LiveCardMenuActivity.class));
        }
        Log.i(TAG, message);
        GlassToast.createShort(context, message).show();
    }

}
