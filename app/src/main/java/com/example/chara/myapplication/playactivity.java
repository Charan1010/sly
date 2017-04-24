package com.example.chara.myapplication;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;

public class playactivity extends AppCompatActivity {
   // UserAdapter adapter;
    UserAdapter1 adapter1;
    ListView listview;
    String[] names;
    String[] ages;
    String[] dates;
    String[] times;
    Context cx = this;
    Cursor cr;
    double localat;
    double localong;
    DatabaseReference ref1=FirebaseDatabase.getInstance().getReference("users");
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("userlocation");
    GeoFire geoFire = new GeoFire(ref);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playactivity);
        listview=(ListView)findViewById(R.id.list_view);
       // adapter=new UserAdapter(getApplicationContext(),R.layout.row_layout);
        adapter1=new UserAdapter1(ref1,this,R.layout.row_layout);
        listview.setAdapter(adapter1);
       // listview.setAdapter(adapter);
        database db = new database(cx);
        cr = db.getinformation(db);
        cr.moveToFirst();
        localat = cr.getDouble(3);
        localong = cr.getDouble(4);
        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(localat, localong), 0.6);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                Toast.makeText(getBaseContext(), "key entered" + key, Toast.LENGTH_SHORT).show();
                   ref1.child(key).addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {

                          // User user=dataSnapshot.getValue(User.class);
                          // addvalue(user);
                            //val=dataSnapshot.getValue(User.class);
                           String key = dataSnapshot.getKey();
                           if (!adapter1.exists(key)) {
                               Log.d("TAG", "item added " + key);
                               adapter1.addSingle(dataSnapshot);
                               adapter1.notifyDataSetChanged();
                           } else {
                               //...otherwise I will update the record
                               Log.d("TAG", "item updated: " + key);
                               adapter1.update(dataSnapshot, key);
                               adapter1.notifyDataSetChanged();
                           }
                       }
                       @Override
                       public void onCancelled(DatabaseError databaseError) {
                       }
                   });
            }
            @Override
            public void onKeyExited(String key) {
                System.out.println(String.format("Key %s is no longer in the search area", key));
            }
            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                System.out.println(String.format("Key %s moved within the search area to [%f,%f]", key, location.latitude, location.longitude));
            }
            @Override
            public void onGeoQueryReady() {
                System.out.println("All initial data has been loaded and events have been fired!");
            }
            @Override
            public void onGeoQueryError(DatabaseError error) {
                System.err.println("There was an error with this query: " + error);
            }
        });
        Log.d("log1","before madapter");
    }
    public  void addvalue(User user)
    {                  Log.d("log","in addvalue");
          //   User user1=new User(user.getname(),user.getage(),user.getdate(),user.gettime());
        //adapter.add(user1);


    }
}

