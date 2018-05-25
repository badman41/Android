package com.example.bharathtata.movielisting;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {
    LinearLayout mLinearLayout;
    Animation slide_down,slide_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mLinearLayout=findViewById(R.id.linearLayout);
        slide_down = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        slide_up = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
    }

    public void Exit(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);

    }

    public void Retry(View view) {
        mLinearLayout.startAnimation(slide_down);
        if (AppStatus.getInstance(getApplicationContext()).isOnline()) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(i);
        }
        mLinearLayout.startAnimation(slide_up);
    }
}
