package com.jihc.registr;

import  androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.jihc.registr.StoreDatabase.COLUMN_EMAIL;
import static com.jihc.registr.StoreDatabase.COLUMN_INFO;
import static com.jihc.registr.StoreDatabase.COLUMN_PASSWORD;
import static com.jihc.registr.StoreDatabase.TABLE_USER;

public class Log_in extends AppCompatActivity implements View.OnClickListener {
    ImageView back;
    TextView sign_in;
    EditText et_name, et_email, et_password, et_confirm_pass;
    Button  create_account;
    StoreDatabase storeDatabase;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        initViews();
    }

            // Arrow Icon
    public void go_back() {
        Intent back = new Intent(Log_in.this,Sign_in.class);
        startActivity(back);
    }

    public void initViews(){
        back = findViewById(R.id.iv_back);

        sign_in = findViewById(R.id.tv_sign);
        sign_in.setOnClickListener(this);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_confirm_pass = findViewById(R.id.et_confirm_pass);

        create_account = findViewById(R.id.btn_create_account);
        create_account.setOnClickListener(this);

        storeDatabase = new StoreDatabase(this);
        sqLiteDatabase = storeDatabase.getWritableDatabase();
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_create_account:

                boolean createAccount = true;
                String username = et_name.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                String confirm_pass = et_confirm_pass.getText().toString();

                if(username.isEmpty()){
                    et_name.setError("Please, try again!");
                    createAccount = false;
                }

                if(email.isEmpty()){
                    et_email.setError("Please, try again!");
                    createAccount = false;
                }

                if(password.isEmpty()){
                    et_password.setError("Please, try again!");
                    createAccount = false;
                }

                if(confirm_pass.isEmpty()){
                    et_confirm_pass.setError("Please, try again!");
                    createAccount = false;
                }

                if (createAccount){

                    // database insert

                    ContentValues userValue = new ContentValues();
                    userValue.put(COLUMN_INFO, et_name.getText().toString());
                    userValue.put(COLUMN_EMAIL, et_email.getText().toString());
                    userValue.put(COLUMN_PASSWORD, et_password.getText().toString());

                    sqLiteDatabase.insert(TABLE_USER, null, userValue);

                    Toast.makeText(this, "Account has been successfully created!", Toast.LENGTH_SHORT).show();
                    showDatabaseData();
                }else{
                    Toast.makeText(this, "Please, fill all info", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.tv_sign:

                Intent intent = new Intent(Log_in.this, Sign_in.class);
                startActivity(intent);

                break;
        }
    }

    public void showDatabaseData(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_USER, null);

        if ((cursor != null && cursor.getCount() > 0)) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_INFO));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));

                Log.i("Database", "Name" + name);
                Log.i("Database", "Email" + email);
                Log.i("Database", "Password" + password);
            }

        }
    }
}