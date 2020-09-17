package com.jihc.registr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        img = findViewById(R.id.GoBackIcon);
    }

    public void goBack(View view) {
        Intent baba = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(baba);
    }
}