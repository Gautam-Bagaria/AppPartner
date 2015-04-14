package com.apppartner.androidprogrammertest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.adapters.ChatsArrayAdapter;
import com.apppartner.androidprogrammertest.models.ChatData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

//This activity will show the chat screen with circular image, person name and the chat text.
public class ChatActivity extends ActionBarActivity
{
    // set widgets and variables that we will be using
    private static final String LOG_TAG = "ActionBarActivity";
    private ArrayList<ChatData> chatDataArrayList;
    private ChatsArrayAdapter chatsArrayAdapter;
    private ListView listView;
    private Button toolbarBack;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toolbarBack = (Button)findViewById(R.id.toolbar_back_btn);
        listView = (ListView) findViewById(R.id.listView);
        chatDataArrayList = new ArrayList<ChatData>();
        toolbarTitle = (TextView)findViewById(R.id.toolbar_title);
        //set font and title
        Typeface font = Typeface.createFromAsset(getAssets(), "Jelloween - Machinato ExtraLight.ttf");
        toolbarTitle.setTypeface(font);
        toolbarTitle.setText("Chat");
        try
        {
            //load the data from the given file
            String chatFileData = loadChatFile();
            JSONObject jsonData = new JSONObject(chatFileData);
            JSONArray jsonArray = jsonData.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ChatData chatData = new ChatData(jsonObject);
                chatDataArrayList.add(chatData);

               }
        }
        catch (Exception e)
        {
            Log.w(LOG_TAG, e);
        }

        //populate the list view

        chatsArrayAdapter = new ChatsArrayAdapter(this, chatDataArrayList);
        listView.setAdapter(chatsArrayAdapter);
    }

    //Onclick event for toolbare back button
    public void onToolbarBackPressed(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //onClick event for device back button
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // function that reads data from given file.
    private String loadChatFile() throws IOException
    {
        InputStream inputStream = getResources().openRawResource(R.raw.chat_data);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String receiveString;
        StringBuilder stringBuilder = new StringBuilder();

        while ((receiveString = bufferedReader.readLine()) != null )
        {
            stringBuilder.append(receiveString);
            stringBuilder.append("\n");
        }

        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();


        return stringBuilder.toString();
    }

}
