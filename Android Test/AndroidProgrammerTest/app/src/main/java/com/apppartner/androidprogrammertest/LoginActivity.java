package com.apppartner.androidprogrammertest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
// This activity is made to send a POST request and show the response in a dialogue box with the time taken in making the request and getting the response.
public class LoginActivity extends ActionBarActivity {
    //set the widgets and variables.
    private Button loginButton, toolbarBack;
    private EditText username;
    private EditText password;
    private TextView toolbarTitle;
    private  String url = "http://dev.apppartner.com/AppPartnerProgrammerTest/scripts/login.php";
    private JSONArray user = null;
    private Date startTime, endTime;
    private long diffInMs;
    private  String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.button4);
        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        toolbarBack = (Button)findViewById(R.id.toolbar_back_btn);
        toolbarTitle = (TextView)findViewById(R.id.toolbar_title);
        Typeface font = Typeface.createFromAsset(getAssets(), "Jelloween - Machinato ExtraLight.ttf");
        toolbarTitle.setTypeface(font);
        //set the font and title
        font = Typeface.createFromAsset(getAssets(), "Jelloween - Machinato.ttf");
        loginButton.setTypeface(font);
        toolbarTitle.setText("Login");
    }

    //onClick event for toolbar back button.
    public void onToolbarBackPressed(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //onClick event for device back button.
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //onClick event for login button
    public void onLogin(View v) throws IOException, JSONException {
        // start and async task
        new JSONParse().execute();

            }

    // async task to make a POST request and show the response in a alert dialogue box.
    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONObject doInBackground(String... args) {
            startTime = new Date();
            JSONParser jParser = new JSONParser(username.getText().toString(), password.getText().toString());
            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            endTime = new Date();
            diffInMs= endTime.getTime() - startTime.getTime();

            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
               //Toast.makeText(LoginActivity.this, json.toString(), Toast.LENGTH_SHORT).show();
                code = json.getString("code");
                String message = json.getString("message");
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("time taken " +diffInMs + "\n"+ code + "\n"+ message)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (code.matches("Success")) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}