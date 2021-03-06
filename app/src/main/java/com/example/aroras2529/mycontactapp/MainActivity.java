package com.example.aroras2529.mycontactapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.aroras2529.mycontactapp.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editNumber;
    EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.editText_name);
        editNumber = findViewById(R.id.editText_number);
        editEmail = findViewById(R.id.editText_email);
        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp", "Databasehelper: instantiated Databasehelper");
    }
    public void addData(View view){
        Log.d("MyContactApp", "MainActivity: Add contact button pressed");
        boolean isInserted = myDb.insertData(editName.getText().toString(), editNumber.getText().toString(), editEmail.getText().toString());
        if(isInserted == true){
            Toast.makeText(MainActivity.this, "Success - contact inserted",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Failure - contact not inserted",Toast.LENGTH_LONG).show();
        }
    }
    public void viewData (View view){
        Cursor res = myDb.getAllData();
        Log.d("MyContactApp", "MainActivity: viewData: received cursor " + res.getCount());
        if (res.getCount() == 0){
            showMessage("Error", "No data found in database");
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            //Append res column 0123
            for(int i = 0; i < 4; i++){
                buffer.append(res.getColumnName(i) + ": " + res.getString(i) + "\n" );
            }
            buffer.append("\n");
        }
        Log.d("MyContactApp", "MainActivity: viewData: assembled stringbuffer");
        showMessage("Data", buffer.toString());
    }

    public void showMessage(
            String title, String message) {
        Log.d("MyContactApp", "MainActivity: showMessage: building alert dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public static final String Extra_Message = "com.example.aroras2529.mycontactapp.MESSAGE";
    public void SearchRecord (View view) {
        Log.d("MyContactApp", "MainActivity: Launching SearchActivity");
        Intent intent = new Intent(this, SearchActivity.class);
        Cursor res = myDb.getAllData();
        StringBuffer buff = new StringBuffer();
        String name = editName.getText().toString();
        while (res.moveToNext()) {
            String ID = res.getString(0);
            String Names = res.getString(1);
            String Numbers = res.getString(2);
            String Emails = res.getString(3);
            if (name.equals(Names)) {
                buff.append(ID + "\n" + Names + "\n" + Numbers + "\n" + Emails + "\n" + "\n");

            }

        }
        if (buff.length() == 0){
            buff.append("Sorry, no results found");
        }
        String retur = buff.toString();
        intent.putExtra(Extra_Message, retur);
        startActivity(intent);
    }
}

