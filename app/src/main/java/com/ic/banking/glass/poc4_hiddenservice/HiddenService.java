package com.ic.banking.glass.poc4_hiddenservice;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class HiddenService extends Service {

    private static final String LIVE_CARD_TAG = "HiddenService";

    private LiveCard liveCard;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (liveCard == null) {
            liveCard = new LiveCard(this, LIVE_CARD_TAG);

            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.hidden);
            liveCard.setViews(remoteViews);

            // Display the options menu when the live card is tapped.
            Intent menuIntent = new Intent(this, LiveCardMenuActivity.class);
            liveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));
            liveCard.publish(PublishMode.REVEAL);
        } else {
            liveCard.navigate();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (liveCard != null && liveCard.isPublished()) {
            liveCard.unpublish();
            liveCard = null;
        }
        super.onDestroy();
    }
}
