package com.example.chara.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static  final String LOG="log";
    EditText ett1,ett2;
    String s1,s2;
    Context cx=this;
    Cursor cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login(View view)
    {Log.d(LOG,"at beginning");
        ett1=(EditText)findViewById(R.id.et1);
        ett2=(EditText)findViewById(R.id.et2);
        s1=ett1.getText().toString();
        s2=ett2.getText().toString();
        database db=new database(cx);
        Log.d(LOG," object created");
        cr=db.getinformation(db);

        cr.moveToFirst();
            Log.d(LOG,"in do loop");
            if(s1.equals(cr.getString(0))&& s2.equals(cr.getString(3))) {
               Intent intent=new Intent(this,choiceactivity.class);
                startActivity(intent);
            }
            else
                Log.d(LOG,"wrong");




        cr.close();
    }
    public void createaccount(View view)
    {
        Intent intent=new Intent(this,SignupActivity.class);
        startActivity(intent);

    }

}
