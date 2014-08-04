package com.ic.banking.glass.poc4_hiddenservice;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class FacebookGraphService {

    private static final String TAG = FacebookGraphService.class.getSimpleName();
    private static final String SERVICE_URL = "http://graph.facebook.com/";

    private static FacebookGraphService instance;
    private HttpClient httpClient;
    private Gson gson;

    private FacebookGraphService() {
        this.httpClient = new DefaultHttpClient();
        this.gson = new Gson();
    }

    public static FacebookGraphService instance() {
        if (instance == null) {
            instance = new FacebookGraphService();
        }
        return instance;
    }

    public UserDTO getUser(String user) {
        HttpGet httpGet = new HttpGet(SERVICE_URL + user);
        try {
            HttpResponse response = this.httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();

            if (status == HttpStatus.SC_OK) {
                String data = EntityUtils.toString(response.getEntity());
                UserDTO userDTO = this.gson.fromJson(data, UserDTO.class);
                return userDTO;
            }
        }
        catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

}
