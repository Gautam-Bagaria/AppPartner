package com.apppartner.androidprogrammertest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

//This is the start activity after splash window.
public class MainActivity extends ActionBarActivity
{
    //set the widgets.
    private TextView coding_task;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coding_task = (TextView)findViewById(R.id.coding_task_textView);
       //set the font.
        Typeface font = Typeface.createFromAsset(getAssets(), "Jelloween - Machinato Bold.ttf");
        coding_task.setTypeface(font);

    }

    //onClick event for login button.
    public void onLoginButtonClicked(View v)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //onClick event for chat button.
    public void onChatButtonClicked(View v)
    {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
        finish();
    }

    //onClick event for animation button.
    public void onAnimationTestButtonClicked(View v)
    {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);
        finish();
    }
}
