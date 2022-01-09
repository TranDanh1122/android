package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.model.anotheruser;
import com.example.myapplication.model.currentuser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference reference;
    TextView name;
    Button nhantin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


            String nguoinhan = getIntent().getStringExtra("userclicked");
             if (nguoinhan != null) {
            name=findViewById(R.id.name);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


            DatabaseReference reference= FirebaseDatabase.getInstance("https://android-dhcn5-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
            reference.child("User").child(nguoinhan).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {

                    currentuser info=task.getResult().getValue(currentuser.class);

                    name.setText(info.getUsername());


                }
            });
            nhantin=findViewById(R.id.nhantin);
            nhantin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent= new Intent(UserProfile.this,ChatActivitive.class);

                    intent.putExtra("nguoinhan",nguoinhan);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}