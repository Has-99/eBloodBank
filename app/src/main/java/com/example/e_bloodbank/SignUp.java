package com.example.e_bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {
    EditText inputEmail,inputPassword;
    Button btnReg;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        EditText inputName = findViewById(R.id.name);

        Spinner spinGender = (Spinner) findViewById(R.id.Gender);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gender));
        spinGender.setAdapter(myAdapter);

        Spinner spinBlood = (Spinner) findViewById(R.id.BloodGroup);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(SignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.blood));
        spinBlood.setAdapter(myAdapter2);

        Spinner spinProv = (Spinner) findViewById(R.id.Province);
        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(SignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.provinces));
        spinProv.setAdapter(myAdapter3);

        Spinner spinDis = (Spinner) findViewById(R.id.District);

        spinProv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            ArrayAdapter<String> myAdapter4;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (myAdapter3.getItem(position).equals("Western Province")) {
                    myAdapter4 = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.western));
                } else if (myAdapter3.getItem(position).equals("Central Province")) {
                    myAdapter4 = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.central));
                } else if (myAdapter3.getItem(position).equals("Southern Province")) {
                    myAdapter4 = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.southern));
                } else if (myAdapter3.getItem(position).equals("Northern Province")) {
                    myAdapter4 = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.nothern));
                } else if (myAdapter3.getItem(position).equals("North Western Province")) {
                    myAdapter4 = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.northwestern));
                } else if (myAdapter3.getItem(position).equals("Eastern Province")) {
                    myAdapter4 = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.eastern));
                } else if (myAdapter3.getItem(position).equals("North Central Province")) {
                    myAdapter4 = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.northcentral));
                } else if (myAdapter3.getItem(position).equals("Uva Province")) {
                    myAdapter4 = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.uva));
                } else if (myAdapter3.getItem(position).equals("Sabaragamuwa Province")) {
                    myAdapter4 = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.sabaragamuwa));
                }
                spinDis.setAdapter(myAdapter4);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        EditText inputContact = findViewById(R.id.Contact);
        EditText inputEmail = findViewById(R.id.signUpInputEmail);
        EditText inputPassword = findViewById(R.id.signUpInputPassword);
        RadioGroup radioUserType = findViewById(R.id.UserType);

        btnReg = findViewById(R.id.reg) ;
        progressDialog = new ProgressDialog(this);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                User newUser = new User(
                    inputName.getText().toString(),
                    spinGender.getSelectedItem().toString(),
                    spinBlood.getSelectedItem().toString(),
                    spinProv.getSelectedItem().toString(),
                    spinDis.getSelectedItem().toString(),
                    inputContact.getText().toString(),
                    radioUserType.getCheckedRadioButtonId() == R.id.rec ? UserType.RECIPIENT : UserType.DONOR
                );
                PerformAuth(email, password, newUser);
            }
        });
    }

    private void PerformAuth(String email, String password, User newUser) {
        if (!email.matches(emailPattern)){
            inputEmail.setError("Enter correct Email");
        } else if (password.isEmpty() || password.length() < 6) {
            inputPassword.setError("Enter proper password");
        } else {
            progressDialog.setMessage("Please wait during registration..." );
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       db.collection("users").document(mAuth.getCurrentUser().getEmail()).set(newUser);
                       progressDialog.dismiss();
                       sendUserToNextActivity();
                       Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                   } else{
                       progressDialog.dismiss();
                       Toast.makeText(SignUp.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                   }
                }
            });

        }
    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(SignUp.this,SignIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}

