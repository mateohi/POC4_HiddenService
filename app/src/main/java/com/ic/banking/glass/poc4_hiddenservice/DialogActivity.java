package com.ic.banking.glass.poc4_hiddenservice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.glass.app.Card;

public class DialogActivity extends Activity {

    public static final String TYPE = "type";
    public static final String MESSAGE = "message";

    public static final String INFO = "info";
    public static final String WARNING = "warning";
    public static final String DONE = "done";

    private static final String UNDEFINED_TYPE = "Undefined type %s in DialogActivity";
    private static final String NO_MESSAGE = "No message received in intent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getView(getIntent());
        setContentView(view);
    }

    private View getView(Intent intent) {
        String type = intent.getStringExtra(TYPE);
        String message = intent.getStringExtra(MESSAGE);

        if (message == null) {
            throw new RuntimeException(NO_MESSAGE);
        }

        if (INFO.equals(type)) {
            return info(message);
        }
        else if (WARNING.equals(type)) {
            return warning(message);
        }
        else if (DONE.equals(type)) {
            return done(message);
        }
        else {
            throw new IllegalArgumentException(String.format(UNDEFINED_TYPE, type));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dialog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_dismiss) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private View info(String message) {
        return createNotice(message, R.drawable.ic_question);
    }

    private View warning(String message) {
        return createNotice(message, R.drawable.ic_warning);
    }

    private View done(String message) {
        return createNotice(message, R.drawable.ic_done);
    }

    private View createNotice(String message, int imageId) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), imageId);

        Card card = new Card(getApplicationContext());
        card.setText(message);
        card.addImage(image);
        card.setImageLayout(Card.ImageLayout.LEFT);

        return card.getView();
    }
}
