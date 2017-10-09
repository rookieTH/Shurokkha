package com.cse.mist.shurokkhav5;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

/*

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this,StartActivity.class);
        startActivity(i);
    }
}

*/
public class MainActivity extends AppCompatActivity {
    private static int Splash_time=500;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){ if (firebaseAuth.getCurrentUser() != null){ Intent homeIntent= new Intent(MainActivity.this,HelpActivity.class);
                startActivity(homeIntent);

            }
                else{
                Intent homeIntent= new Intent(MainActivity.this,StartActivity.class);
                startActivity(homeIntent);}
                finish();
            }
        },Splash_time);
    }


}
