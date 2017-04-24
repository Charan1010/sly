package com.example.chara.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static com.example.chara.myapplication.R.id.button;

public class choiceactivity extends AppCompatActivity {
    public static final String in1="EXTRA_SESSION_ID";
    PopupMenu popupMenu;
    String str;
    Button btn, btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choiceactivity);
        btn = (Button) findViewById(R.id.button22);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1 = new Intent(getBaseContext(), hostactivity.class);
                startActivity(intent1);
            }
        });

        btn1 = (Button) findViewById(R.id.button33);
        Log.d("log","after btn1");
        popupMenu = new PopupMenu(choiceactivity.this, btn1);
        popupMenu.inflate(R.menu.langselect);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();


            }
        });
      popupMenu.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.eng:
                              str="English";
                                Log.d("log","in english");
                                intent();
                                break;
                            case R.id.hin:
                                  str="Hindi";
                                Log.d("log","in hindi");
                                intent();
                                break;
                            case R.id.tel:
                                   str="Telugu";
                                intent();
                                break;
                        }
                        return true;
                    }
                });


    }
    public void intent()
    {
        Intent intent=new Intent(getBaseContext(),songselect.class);
        intent.putExtra(in1,str);
        startActivity(intent);
    }


}
