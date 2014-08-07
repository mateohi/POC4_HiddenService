package com.ic.banking.glass.poc4_hiddenservice;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class GraphTask extends AsyncTask<String, Void, String> {

    private static final String TAG = GraphTask.class.getSimpleName();

    private Context context;

    public GraphTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String userId = params[0];
        UserDTO user = FacebookGraphService.instance().getUser(userId);
        String userName = user != null ? user.getName() : null;

        return userName;
    }

    @Override
    protected void onPostExecute(String userName) {
        String message;
        if (userName == null) {
            message = "Something went wrong...";
            GlassDialog.warning(context, message);
        }
        else {
            message = "Username:\n" + userName;
            GlassDialog.done(context, message);
            // context.startActivity(new Intent(this.context, LiveCardMenuActivity.class));
        }
        Log.i(TAG, message);
        //GlassToast.createLong(context, message).show();
    }

}
