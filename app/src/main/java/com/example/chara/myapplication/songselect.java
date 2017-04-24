package com.example.chara.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.chara.myapplication.choiceactivity.in1;

public class songselect extends AppCompatActivity {
    UserAdapter1 adapter1;
    ListView listview1;
    String lang;
    private FirebaseDatabase firebasedatabase=FirebaseDatabase.getInstance();
    private DatabaseReference ref=firebasedatabase.getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songselect);
         lang= getIntent().getStringExtra(in1);
        listview1=(ListView)findViewById(R.id.list_view1);
        adapter1=new UserAdapter1(ref.orderByChild("lang").equalTo(lang),this,R.layout.row_layout);
        listview1.setAdapter(adapter1);
      

    }
}
