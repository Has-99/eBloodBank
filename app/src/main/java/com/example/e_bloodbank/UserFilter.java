package com.example.e_bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;

public class UserFilter extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_filter);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db.collection("users").whereEqualTo("bloodType", getIntent().getStringExtra("bloodType")).get()
            .addOnSuccessListener(querySnapshot -> {
                recyclerView.setAdapter(new UserAdapter(querySnapshot.getDocuments()));
            });
    }
}