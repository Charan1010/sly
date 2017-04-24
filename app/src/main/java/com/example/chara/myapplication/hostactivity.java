package com.example.chara.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

public class hostactivity extends AppCompatActivity implements ValueEventListener {
    Spinner spnr;
    String lang,s1,s2;
    EditText et1,et2;

    String[] langs = {
            "English",
            "Hindi",
            "Telugu",
            "Kannada"
    };
    int year_x,month_x,day_x,hour_x,min_x;
    Cursor cr;
    Toolbar tb;
    static final int DIALOG_ID=0;
    static final int DIALOG_ID1=1;
    Context cx=this;
    private FirebaseDatabase firebasedatabase=FirebaseDatabase.getInstance();
    private DatabaseReference mRootReference=firebasedatabase.getReference("users");
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("userlocation");
    GeoFire geoFire = new GeoFire(ref);

    public static final String LOG1="log1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostactivity);
        et1=(EditText)findViewById(R.id.editText3);
        et2=(EditText)findViewById(R.id.esong);

        tb=(Toolbar)findViewById(R.id.tbb);
        setActionBar(tb);
        spnr = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, langs);

        spnr.setAdapter(adapter);
        spnr.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        int position = spnr.getSelectedItemPosition();
                        Toast.makeText(getApplicationContext(), "You have selected " + langs[+position], Toast.LENGTH_LONG).show();
                        lang=langs[+position];
                    }
                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub

                        }

                    }
                    );
                        //
       // showDialogOnButtonClick();
       // showtimepickerdialog();


    }



  /*      public void showtimepickerdialog()
    {
        Button btn=(Button)findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID1);
            }
        });
    }

    public void showDialogOnButtonClick()
    {
        Button btn=(Button)findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        if (id == DIALOG_ID)
        return new DatePickerDialog(this,dpickerListener,year_x,month_x,day_x);
       else if(id==DIALOG_ID1)
            return new TimePickerDialog(this,tpickerListener,hour_x,min_x,true);
        else
            return null;

    }
    private DatePickerDialog.OnDateSetListener dpickerListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x=year;
            month_x=month;
            day_x=dayOfMonth;

        }
    };
    private TimePickerDialog.OnTimeSetListener tpickerListener=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x=hourOfDay;
            min_x=minute;
        }
    };*/

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.next)
        {
            s1=et1.getText().toString();
            s2=et2.getText().toString();
            database db=new database(cx);
            cr=db.getinformation(db);
            cr.moveToFirst();
            Log.d("log1","before push");
            String userId = mRootReference.push().getKey();
            Log.d("log1","after push");
            User user = new User(cr.getString(0),cr.getString(1),cr.getString(2),s1,s2,lang);
            Log.d("log1","after calling user");
            mRootReference.child(userId).setValue(user);
            Log.d("log1","after insertion of data");
            geoFire.setLocation(userId, new GeoLocation(cr.getDouble(3),cr.getDouble(4)));
            Log.d("log1","after geofoire query");
        }


        return true;
    }
}
