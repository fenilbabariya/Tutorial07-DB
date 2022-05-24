package com.rku_21soeca21002.tutorial07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText edtUsername, edtPassword;
    TextView txtViewData;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);
        txtViewData = findViewById(R.id.txtView);
        dbHelper = new DBHelper(getApplicationContext());

    }

    public void btnViewClick(View view) {
        String valUsername = edtUsername.getText().toString().trim();
        String valPassword = edtPassword.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", valUsername);
        values.put("password", valPassword);
        db.insert("student", null, values);
        displaData();
    }

    private void displaData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("student", new String[]{"username", "password"}, "id<?", new String[]{"10"}, null, null, "id desc");
        if (cursor.getCount() > 0) {
            String data = "";
            cursor.moveToFirst();
            do {
                data = data + cursor.getString(0) + " - " + cursor.getString(1) + "\n";
            } while (cursor.moveToNext());
            txtViewData.setText(data);
        }
    }
}