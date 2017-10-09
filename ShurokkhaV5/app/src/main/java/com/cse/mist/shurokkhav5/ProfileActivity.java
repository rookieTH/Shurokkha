package com.cse.mist.shurokkhav5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;
    public void onClickReached(View view){

        ///map dibo
     //   Intent i = new Intent(this, TrustedContacts.class);
        //startActivity(i);



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();



    }


    @Override
    public void onClick(View view) {
     //   if (view == logoutBtn){
          //  firebaseAuth.signOut();
            startActivity(new Intent(ProfileActivity.this,SendSmsActivity.class));
       // }
    }
public void ClickSafe(View view){

    startActivity(new Intent(this,SendSmsSafe.class));
}
    public void clickFollowed(View view) {
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);

    }



}
