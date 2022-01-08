package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.controller.anotheruser_ctrl;
import com.example.myapplication.model.anotheruser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TimKiem extends AppCompatActivity {
    ListView lvDanhSach;
    ArrayList<anotheruser> resultlist;
    anotheruser_ctrl adapter;
    EditText search;
    Button btnsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        search=findViewById(R.id.timkiem);
        lvDanhSach=findViewById(R.id.ketqua);
        btnsearch=findViewById(R.id.timbtn);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference reference= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
                reference.child("User").orderByChild("username").startAt(search.getText().toString())
                        .endAt(search.getText().toString()+"\uf8ff").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        resultlist = new ArrayList<>();
                        for (DataSnapshot dt : task.getResult().getChildren()) {
                            anotheruser anotheruser = dt.getValue(com.example.myapplication.model.anotheruser.class);
                            String userId = dt.child("id").getValue(String.class);
                            String name = dt.child("username").getValue(String.class);
                            resultlist.add(new anotheruser(userId, name, "user"));
                            }
                        adapter = new anotheruser_ctrl(TimKiem.this, R.layout.search_result, resultlist);
                        adapter.notifyDataSetChanged();
                        lvDanhSach.setAdapter(adapter);
                        }
                    });
            }
        });
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(TimKiem.this,UserProfile.class);
                anotheruser clickeduser = resultlist.get(position);
                intent.putExtra("userclicked",clickeduser.getId());
                startActivity(intent);
                finish();
            }
        });
    }
}