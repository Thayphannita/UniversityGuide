package com.example.bona.universityguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Major_Adapter adapter;
    private DatabaseReference reference;
    private EditText etxt;
    private Button btnSearch;
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Major_Adapter();
        recyclerView.setAdapter(adapter);

        etxt=(EditText) findViewById(R.id.edt_search);


        btnSearch = (Button) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "on click listener");
        String query = etxt.getText().toString();
        reference= FirebaseDatabase.getInstance().getReference("/Majors");
        reference.orderByChild("basicCourse").startAt(query).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Major[] majors=new Major[(int) dataSnapshot.getChildrenCount()];
                int index=0;
                Log.d("ckcc", "Data snipshort is  :" + dataSnapshot.getChildrenCount());
                Log.d("ckcc","Loaded1");
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Log.d("ckcc","Loaded2");
                    Major major=snapshot.getValue(Major.class);
                    majors[index]=major;
                    index++;
                    Log.d("ckcc","Loaded"+major.majorName);
                }
                adapter.setMajors(majors);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
