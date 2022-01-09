package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.controller.anotheruser_ctrl;
import com.example.myapplication.controller.lastmess_ctrl;
import com.example.myapplication.controller.message_ctrl;
import com.example.myapplication.model.anotheruser;
import com.example.myapplication.model.currentuser;
import com.example.myapplication.model.lastmess;
import com.example.myapplication.model.message;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button toProfile,toSetting;
    ListView lvMess;
    ArrayList<lastmess> arrMess;
    lastmess_ctrl adapter;
    FirebaseAuth auth;
    DatabaseReference reference;
    String sender,receiver;
    int j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toProfile=findViewById(R.id.toprofile);
        toSetting=findViewById(R.id.tosetting);
        lvMess=findViewById(R.id.lastmess);
        toProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MyProfile.class));
                finish();
            }
        });
        toSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,UserProfile.class));
                finish();
            }
        });

        FirebaseUser currentuserid= FirebaseAuth.getInstance().getCurrentUser();
        String myid=currentuserid.getUid();
        DatabaseReference ref= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Message");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrMess = new ArrayList<>();
                Map<String, String> grpmess = new HashMap<String, String>();
                Map<String, String> grpsender= new HashMap<String, String>();
                int i=0;
                if(dataSnapshot==null){
                }else {
                    for (DataSnapshot dt : dataSnapshot.getChildren()) {
                         sender=dt.child("sender").getValue(String.class);
                         receiver=dt.child("receiver").getValue(String.class);
                        String message = dt.child("message").getValue(String.class);
                        if(receiver.equals(myid)){
                            int oldsize=grpmess.size();
                            grpmess.put(sender , message);
                            grpsender.put(String.valueOf(i) , sender);
                            int newsize=grpmess.size();
                            if(oldsize!=newsize){
                                i++;
                            }
                        }
                    }

                    for( j=0;j<=i;j++){
                        if(grpsender.get(String.valueOf(j))==null){

                        }else {
                            DatabaseReference reference = FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
                            String finalJ = grpsender.get(String.valueOf(j));
                            reference.child("User").child(grpsender.get(String.valueOf(j))).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (!task.getResult().exists()) {

                                    } else {

                                        currentuser info = task.getResult().getValue(currentuser.class);

                                        arrMess.add(new lastmess(grpmess.get(finalJ), info.getUsername(), "default",finalJ));
                                    }
                                    adapter = new lastmess_ctrl(MainActivity.this, R.layout.lastmess, arrMess);
                                    lvMess.setAdapter(adapter);

                                }
                            });

                        }
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addValueEventListener(postListener);

        lvMess.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(MainActivity.this,ChatActivitive.class);
                lastmess clickeduser = arrMess.get(position);
                intent.putExtra("nguoinhan",clickeduser.getId());
                startActivity(intent);
                finish();
            }
        });
    }

}