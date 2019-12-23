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

public class SignupActivity extends AppCompatActivity {
    public EditText email, password;
    public Button signup;
    TextView signin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        signup = findViewById(R.id.btn_signup);
        signup.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(SignupActivity.this, "Tolong Isi Email dan Password", Toast.LENGTH_LONG).show();
                } else if (!(emailId.isEmpty() && pwd.isEmpty())){
                    mAuth.createUserWithEmailAndPassword(emailId, pwd)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                    } else {
                                        Toast.makeText(SignupActivity.this, "Daftar Gagal",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    signin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}
