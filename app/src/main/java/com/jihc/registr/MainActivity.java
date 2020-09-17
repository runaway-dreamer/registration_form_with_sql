package com.jihc.registr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.jihc.registr.StoreDatabase.COLUMN_CONFIRM_PASSWORD;
import static com.jihc.registr.StoreDatabase.COLUMN_EMAIL;
import static com.jihc.registr.StoreDatabase.COLUMN_INFO;
import static com.jihc.registr.StoreDatabase.COLUMN_PASSWORD;
import static com.jihc.registr.StoreDatabase.TABLE_USER;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_name, et_email, et_password, et_confirm_pass;
    Button btn, create_account;
    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        btn = findViewById(R.id.create_account);
        initViews();
    }

    public void gotoNext(View view) {
        Intent bebe = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(bebe);
    }

    public void initViews(){
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_confirm_pass = findViewById(R.id.et_confirm_pass);

        create_account = findViewById(R.id.create_account);
        create_account.setOnClickListener(this);

        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.create_account:

                boolean createAccount = true;

                if(et_name.getText().toString().isEmpty()){
                    et_name.setError("Please, try again!");
                    createAccount = false;
                }

                if(et_email.getText().toString().isEmpty()){
                    et_email.setError("Please, try again!");
                    createAccount = false;
                }

                if(et_password.getText().toString().isEmpty()){
                    et_password.setError("Please, try again!");
                    createAccount = false;
                }

                if(et_confirm_pass.getText().toString().isEmpty()){
                    et_confirm_pass.setError("Please, try again!");
                    createAccount = false;
                }

                if (createAccount){

                    // database insert

                    ContentValues userValue = new ContentValues();
                    userValue.put(COLUMN_INFO, et_name.getText().toString());
                    userValue.put(COLUMN_EMAIL, et_email.getText().toString());
                    userValue.put(COLUMN_PASSWORD, et_password.getText().toString());
                    userValue.put(COLUMN_CONFIRM_PASSWORD, et_confirm_pass.getText().toString());

                    sqLiteDatabase.insert(TABLE_USER, null, userValue);

                    Toast.makeText(this, "Account has been successfully created!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Please, fill all info", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }
}