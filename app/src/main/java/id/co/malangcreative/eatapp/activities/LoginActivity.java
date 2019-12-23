package id.co.malangcreative.eatapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import id.co.malangcreative.eatapp.R;

public class LoginActivity extends AppCompatActivity {
    public EditText email, password;
    public Button BtnSignin;
    TextView TVsignup;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        BtnSignin = findViewById(R.id.btn_signin);
        TVsignup = findViewById(R.id.tv_signup);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if(user != null){
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        BtnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailId = email.getText().toString();
                String pwd = password.getText().toString();
                if (emailId.isEmpty()){
                    email.setError("Silahkan Isi Email Anda");
                    email.requestFocus();
                } else if (pwd.isEmpty()){
                    password.setError("silahkan isi Password Anda");
                    password.requestFocus();
                } else if (emailId.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Tolong Isi Email dan Password", Toast.LENGTH_LONG).show();
                } else if (!(emailId.isEmpty() && pwd.isEmpty())){
                    mAuth.signInWithEmailAndPassword(emailId, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }
            }
        });

        TVsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (LoginActivity.this , SignupActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
}
