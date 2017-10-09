package com.cse.mist.shurokkhav5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static com.cse.mist.shurokkhav5.R.styleable.View;

public class HelpActivity extends AppCompatActivity implements android.view.View.OnClickListener {
ImageButton policeButton,helplineButton;
    Button moreButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        policeButton=(ImageButton)findViewById(R.id.policeButton);
        helplineButton=(ImageButton)findViewById(R.id.helplineButton);
       moreButton=(Button)findViewById(R.id.moreButton);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    public void onClick(View view){
if(view==policeButton){

          String num="100";
       String uri = "tel:" + num ;

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
}
else if (view==helplineButton){

    String num="109";
    String uri = "tel:" + num ;

    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse(uri));
    startActivity(intent);
}
        else if(view==moreButton){


    startActivity(new Intent(this,NavigationActivity.class));
}



    }
}
