package com.ic.banking.glass.poc4_hiddenservice;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;

public class LiveCardMenuActivity extends Activity {

    private static final int MAP_INTENT = 0;
    private static final int CONTACT_INTENT = 1;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // Open the options menu right away.
        openOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hidden, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_stop:
                // Stop the service which will unpublish the live card.
                stopService(new Intent(this, HiddenService.class));
                return true;
            case R.id.action_contact:
                Intent navIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(navIntent, CONTACT_INTENT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        // Nothing else to do, finish the Activity.
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == CONTACT_INTENT) {
            if (resultCode == RESULT_OK) {
                intent.getData();
            }
        }
    }
}
