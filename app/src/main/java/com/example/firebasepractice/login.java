package com.example.firebasepractice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class login extends AppCompatActivity {
          EditText email,pass;
          TextView textView;
          Button button,button1;
          FirebaseAuth firebaseAuth1;

          GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.loginemail);
        pass=findViewById(R.id.loginpass);
        button1=findViewById(R.id.google);
        button=findViewById(R.id.btnlogin);
        textView=findViewById(R.id.txt1);
        firebaseAuth1=FirebaseAuth.getInstance();
        createreques();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this, rege.class);
                startActivity(intent);
            }
        });

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               firebaseAuth1.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       FirebaseUser user=firebaseAuth1.getCurrentUser();
                       if (user!=null){
                           Intent intent=new Intent(login.this, MainActivity.class);
                           startActivity(intent);
                       }else {
                           Toast.makeText(login.this, "User not Found", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
           }
       });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }
      void createreques() {
          GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                  .requestIdToken("784436597102-imn8jo4lh7kbb1uvml1kkugtd0plfgvr.apps.googleusercontent.com")
                  .requestEmail()
                  .build();
          googleSignInClient= GoogleSignIn.getClient(this,googleSignInOptions);
      }
         void signIn(){
        Intent signintent=googleSignInClient.getSignInIntent();
        startActivityForResult(signintent,120);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==120){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account=task.getResult(ApiException.class);
                firbaseAuthwithgoogle(account);
            }catch (ApiException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    void firbaseAuthwithgoogle(GoogleSignInAccount googleSignInAccount){
        AuthCredential credential= GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(),null);
        firebaseAuth1.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent=new Intent(login.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(login.this, "Authentication fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}