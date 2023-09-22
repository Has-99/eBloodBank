package com.example.e_bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Donor extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        TextView txtName = findViewById(R.id.name);
        TextView txtGender = findViewById(R.id.Gender);
        TextView txtBloodGroup = findViewById(R.id.BloodGroup);
        TextView txtProvince = findViewById(R.id.Province);
        TextView txtDistrict = findViewById(R.id.District);
        TextView txtContact = findViewById(R.id.Contact);
        TextView txtEmail = findViewById(R.id.signInInputEmail);
        RadioGroup radioUserType = findViewById(R.id.UserType);
        RadioButton radioUserTypeDonor = findViewById(R.id.don);
        RadioButton radioUserTypeRecipient = findViewById(R.id.rec);
        Button btnReg = findViewById(R.id.reg);

        db.collection("users").document(mAuth.getCurrentUser().getEmail()).get()
            .addOnSuccessListener(documentSnapshot -> {
                User user = documentSnapshot.toObject(User.class);

                txtName.setText(user.getName());
                txtGender.setText(user.getGender());
                txtBloodGroup.setText(user.getBloodType());
                txtProvince.setText(user.getProvince());
                txtDistrict.setText(user.getDistrict());
                txtContact.setText(user.getContactNumber());
                txtEmail.setText(mAuth.getCurrentUser().getEmail());
                if (user.getType() == UserType.DONOR) {
                    radioUserTypeDonor.toggle();
                } else {
                    radioUserTypeRecipient.toggle();
                }
            });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("users").document(mAuth.getCurrentUser().getEmail()).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        User user = documentSnapshot.toObject(User.class);
                        user.setProvince(txtProvince.getText().toString());
                        user.setDistrict(txtDistrict.getText().toString());
                        user.setContactNumber(txtContact.getText().toString());
                        user.setType(radioUserType.getCheckedRadioButtonId() == R.id.don ? UserType.DONOR : UserType.RECIPIENT);

                        db.collection("users").document(mAuth.getCurrentUser().getEmail()).set(user);
                        Toast.makeText(Donor.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                    });
            }
        });
    }
}