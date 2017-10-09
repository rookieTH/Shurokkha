package com.cse.mist.shurokkhav5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class FeelingUnsafe extends AppCompatActivity  implements View.OnClickListener{

    //private Button logoutBtn;
    // private FirebaseAuth firebaseAuth;

    private FirebaseAuth firebaseAuth;
    @Override
    public void onClick(View view) {
   /*     if (view == logoutBtn){
            firebaseAuth.signOut();
            startActivity(new Intent(FeelingUnsafe.this, SignInActivity.class));
        }*/
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling_unsafe);
        //     firebaseAuth = FirebaseAuth.getInstance();

        //   logoutBtn = (Button) findViewById(R.id.LogOut3Button);

        // logoutBtn.setOnClickListener(this);
    }


    public void ClickOthers(View view) {
        Intent i = new Intent(this, SendSmsActivity.class);
        startActivity(i);

    }

    public void ClickSafehouse(View view) {
        ///map dibo
      Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);

    }

    public void ClickPoliceStations(View view) {
        ///map dibo
   //     Intent i = new Intent(this, Map1.class);
     //   startActivity(i);

    }

    public void Logout(View view) {
        firebaseAuth.signOut();
        finishAffinity();
    }
    ////
    public void onClickMap(View View){
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }
}
