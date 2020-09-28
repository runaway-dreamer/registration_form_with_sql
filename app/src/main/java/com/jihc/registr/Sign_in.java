package com.jihc.registr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static com.jihc.registr.StoreDatabase.COLUMN_EMAIL;
import static com.jihc.registr.StoreDatabase.COLUMN_PASSWORD;
import static com.jihc.registr.StoreDatabase.TABLE_USER;

public class Sign_in extends AppCompatActivity implements View.OnClickListener {

    ImageView back;
    EditText email_login, password_login;
    Button sign_in;

    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        initViews();

        back = findViewById(R.id.iv_back);
    }

            // Arrow Icon
    public void go_back(View view) {
        Intent back = new Intent(Sign_in.this,Log_in.class);
        startActivity(back);
    }

    public void initViews(){
        email_login = findViewById(R.id.et_email_login);
        password_login = findViewById(R.id.et_password_login);

        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();

        sign_in = findViewById(R.id.btn_sign_in);
        sign_in.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_sign_in:
                boolean sign_account = true;
                String userEmail = email_login.getText().toString();
                String userPassword = password_login.getText().toString();

                if(userEmail.isEmpty()){
                    email_login.setError("Please, try again!");
                    sign_account = false;
                }

                if(userPassword.isEmpty()){
                    password_login.setError("Please, try again!");
                    sign_account = false;
                }

                if(sign_account){

                    Cursor signCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " +
                            COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=? ", new String[]{userEmail, userPassword});

                    if(signCursor!=null &signCursor.getCount() > 0){
                         signCursor.moveToFirst();

                        Toast.makeText(this, "You are welcome!" +userEmail, Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }

}