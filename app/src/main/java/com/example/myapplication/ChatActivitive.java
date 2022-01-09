package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.controller.anotheruser_ctrl;
import com.example.myapplication.controller.message_ctrl;
import com.example.myapplication.model.anotheruser;
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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firestore.v1.StructuredQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ChatActivitive extends AppCompatActivity {
    ListView lvMess;
    ArrayList<message> arrMess;
    message_ctrl adapter;
    EditText newmess;
    Button send;
    FirebaseAuth auth;
    DatabaseReference reference;
    int maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activitive);
        lvMess=findViewById(R.id.listmess);
        newmess=findViewById(R.id.message);
        send=findViewById(R.id.send);

        String nguoinhan = getIntent().getStringExtra("nguoinhan");
        FirebaseUser currentuserid= FirebaseAuth.getInstance().getCurrentUser();
        String myid=currentuserid.getUid();
        reference= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Message");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                     maxid =(int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("sender",myid);
                hashMap.put("message",newmess.getText().toString());
                hashMap.put("receiver",nguoinhan);
                Date currentTime = Calendar.getInstance().getTime();
                hashMap.put("time",currentTime.toString());


                reference.child(""+((int)maxid+1)).setValue(hashMap);

            }
        });
        DatabaseReference ref= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Message");




        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrMess = new ArrayList<>();

                if(dataSnapshot==null){

                }else {
                    for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    String sender=dt.child("sender").getValue(String.class);
                    String receiver=dt.child("receiver").getValue(String.class);

                    String message = dt.child("message").getValue(String.class);
                    if((sender.equals(myid)&&receiver.equals(nguoinhan))){
                        arrMess.add(new message(message,"right"));
                    }
                    if((sender.equals(nguoinhan)&&receiver.equals(myid))){
                        arrMess.add(new message(message,"left"));
                    }
                }
            }
                adapter = new message_ctrl(ChatActivitive.this, R.layout.list_mess, arrMess);
                adapter.notifyDataSetChanged();
                lvMess.setAdapter(adapter);
                lvMess.setSelection(adapter.getCount() - 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        ref.addValueEventListener(postListener);


    }
}