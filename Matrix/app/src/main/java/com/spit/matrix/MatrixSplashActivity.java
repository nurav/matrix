package com.spit.matrix;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.spit.matrix.R;


public class MatrixSplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MatrixSplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },500);
    }


}
