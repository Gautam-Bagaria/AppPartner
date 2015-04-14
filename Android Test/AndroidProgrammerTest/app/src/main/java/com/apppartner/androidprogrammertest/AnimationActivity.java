package com.apppartner.androidprogrammertest;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

//This activity is for the animation screen. We can fade in, fade out and spin an icon.
public class AnimationActivity extends ActionBarActivity
{
    // set all widgets that we will be using
    private Button toolbarBack;
    private TextView toolbarTitle, bonusTextView, animationTextView;
    private ImageView appPartner_icon;
    private LayoutParams layoutParams ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        toolbarBack = (Button)findViewById(R.id.toolbar_back_btn);
        toolbarTitle = (TextView)findViewById(R.id.toolbar_title);
        bonusTextView = (TextView)findViewById(R.id.bonus_textView);
        animationTextView = (TextView)findViewById(R.id.animation_textView);
        appPartner_icon = (ImageView)findViewById(R.id.imageView2);
        //set image for the image view, this can also be done in the xml file.
        appPartner_icon.setImageResource(R.drawable.ic_apppartner);

        //set font for the textviews
        Typeface font = Typeface.createFromAsset(getAssets(), "Jelloween - Machinato ExtraLight.ttf");
        toolbarTitle.setTypeface(font);
        toolbarTitle.setText("Animation");
        font = Typeface.createFromAsset(getAssets(), "Jelloween - Machinato SemiBold Italic.ttf");
        bonusTextView.setTypeface(font);
        font = Typeface.createFromAsset(getAssets(), "Jelloween - Machinato ExtraLight.ttf");
        animationTextView.setTypeface(font);

        // function that allows clicking and dragging of image inside the frame layout.
        appPartner_icon.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FrameLayout.LayoutParams layoutParams = (LayoutParams) appPartner_icon.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int x_cord = (int) event.getRawX();
                        int y_cord = (int) event.getRawY();

                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        layoutParams.gravity = Gravity.LEFT | Gravity.BOTTOM | Gravity.TOP | Gravity.RIGHT;
                        appPartner_icon.setLayoutParams(layoutParams);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    // onClick event for image fade in and fade out
    public  void onFade(View v) {

        ObjectAnimator alpha=ObjectAnimator.ofFloat(appPartner_icon,View.ALPHA,1f,0f);
        alpha.setRepeatMode(ObjectAnimator.REVERSE);
        alpha.setRepeatCount(1);
        alpha.setDuration(2000);
        alpha.start();

    }

    //onClick event for spinning image
    public  void onSpin(View v) {

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        appPartner_icon.startAnimation(rotateAnimation);

    }

    //onClick event for back button on toolbar
    public void onToolbarBackPressed(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //onClick event for back button on device
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
