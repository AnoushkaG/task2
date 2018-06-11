package com.example.anoushka.task2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button butn;
    private EditText emailID;
    private EditText pass;
    private TextView txt;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // String emailvalue= (String) getText(R.id.editText1);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            Intent intent=new Intent(MainActivity.this,NextActivity.class);
         //   intent.putExtra("email",getText(R.id.editText1));

            startActivity(new Intent(getApplicationContext(),NextActivity.class));

        }
        progressDialog = new ProgressDialog(this);

        butn = (Button) findViewById(R.id.button1);
        emailID = (EditText) findViewById(R.id.editText1);
        pass = (EditText) findViewById(R.id.editText2);
        txt = (TextView) findViewById(R.id.textCred);
        login = (TextView) findViewById(R.id.TextViewLogin);

        butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }
        });

    }

    private void registerUser() {

        String email = emailID.getText().toString().trim();
        String password = pass.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(MainActivity.this, "Please enter your emailID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering user");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"User is Registered",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                        else {
                            Toast.makeText(MainActivity.this,"Error occured while registering",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void userLogin()
    {
        String email = emailID.getText().toString().trim();
        String password = pass.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(MainActivity.this, "Please enter your emailID", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),NextActivity.class));

                            //list screen
                        }

                    }
                });
    }
}
