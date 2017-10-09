package com.cse.mist.shurokkhav5;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    private static Button exitBtn;      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        /// created method ta ekhane call korchi
        onButtonClickListener();
       /* Button btn1 = (Button) findViewById(R.id.exitButton);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });*/
    }
    public void onClickA(View view){
      //  Intent i = new Intent(this,AboutApp.class);
       // startActivity(i);

    }
    public void onClickL(View view){
        Intent i = new Intent(StartActivity.this,SignInActivity.class);
        startActivity(i);

    }
    public void onClickR(View view){
        Intent i = new Intent(StartActivity.this,SignUpActivity.class);
        startActivity(i);

    }

    public void onButtonClickListener(){

        exitBtn = (Button)findViewById(R.id.exitButton);
        exitBtn.setOnClickListener(
                new View.OnClickListener(){

                    public void onClick(View v){
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(StartActivity.this);
                        a_builder.setMessage("Do you want to exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //  finish(); // eta dile close kore na, background ta te jai, mane eta dile mainActivity te jai
                                // android.os.Process.killProcess(android.os.Process.myPid());
                                //finish();
                                // System.exit(1);
                                finishAffinity();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert =  a_builder.create();
                        alert.setTitle("");
                        alert.show();

                    }

                }

        );

    }

}
