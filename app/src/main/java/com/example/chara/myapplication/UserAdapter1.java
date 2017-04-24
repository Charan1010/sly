package com.example.chara.myapplication;

/**
 * Created by chara on 07-03-2017.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This class is an example of how to use FirebaseListAdapter. It uses the ExampleObject class to encapsulate the
 * data for each individual chat message
 */
public class UserAdapter1 extends FirebaseListAdapter<User> {
    @Bind(R.id.name)
    TextView nView;
    @Bind(R.id.age)
    TextView aView;
   @Bind(R.id.about)
           TextView abView;
    Context context;

    public UserAdapter1(Query ref, Activity activity, int layout) {
        super(ref, User.class, layout, activity);
        this.context = activity.getApplicationContext();
    }

    /**
     * Bind an instance of the ExampleObject class to our view. This method is called by <code>FirebaseListAdapter</code>
     * when there is a data change, and we are given an instance of a View that corresponds to the layout that we passed
     * to the constructor, as well as a single ExampleObject instance that represents the current data to bind.
     *
     * @param view    A view instance corresponding to the layout we passed to the constructor.
     *   An instance representing the current state of a message
     */
    @Override
    protected void populateView(View view, User user) {
        ButterKnife.bind(this, view);
            Log.d("log","inside populate");
        // populate the list element
        nView.setText(user.getname());
        aView.setText(user.getage());
        abView.setText(user.getAbout());
       // dView.setText(user.getdate());
       // tView.setText(user.gettime());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Toast.makeText(context,""+()+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected List<User> filters(List<User> models, CharSequence filter) {
        List<User> filterList = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            /* implement your own filtering logic
             * and then call  filterList.add(models.get(i));
             */
        }
        return filterList;
    }

    @Override
    protected Map<String, User> filterKeys(List<User> mModels) {
        return null;
    }
}