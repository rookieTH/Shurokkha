package com.cse.mist.shurokkhav5;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SendSmsSafe extends AppCompatActivity {
    EditText  msg;
    String num, sms;



    String id="";
    String no="";
    String name="";

    ListView listViewContacts2;
    List<Contacts> contactList2;
    private ContactsList2 adapter;

    DatabaseReference databaseContacts;
   String STRING_I_NEED;
    Button btnSendSms;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms_safe);
        databaseContacts= FirebaseDatabase.getInstance().getReference("contacts");

        //noo=(EditText)findViewById(R.id.numText);
        msg=(EditText)findViewById(R.id.msgText);
        listViewContacts2 = (ListView) findViewById(R.id.listViewContacts2);
        contactList2 = new ArrayList<>();
     //   num=noo.getText().toString();
      //////  btnSendSms = (Button) findViewById(R.id.button3);

       Bundle data = getIntent().getExtras();
        if(data==null){return;}
        else {
             STRING_I_NEED = data.getString("STRING_I_NEED");
        }
        }

    private void showUpdateDialog(int position){
        final int p = position;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.dialoguebox,null);
        dialogBuilder.setView(dialogView);

        final Button message=(Button)dialogView.findViewById(R.id.message);
        final Button phone=(Button)dialogView.findViewById(R.id.phone);

        dialogBuilder.setTitle("Do you want to?");
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contacts contacts= (Contacts) adapter.getItem(p);
                //  String uri = "tel:" +  contacts.getNumber();
                String uri = contacts.getNumber();
                sms = msg.getText().toString();
                if(!sms.isEmpty()){ startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", uri, "llala")).putExtra("sms_body", sms ));}
                else{
                    sms="Reached Safely!";

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", uri, "llala")).putExtra("sms_body", sms ));}


            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Contacts contacts= (Contacts) adapter.getItem(p);
                //    Toast.makeText(this,contacts.getId(),Toast.LENGTH_LONG).show();
                String uri = "tel:" + contacts.getNumber() ;

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);



            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

    }

    @Override
    protected void onStart() {
        super.onStart();


        databaseContacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contactList2.clear();
                for(DataSnapshot contactsSnapshot : dataSnapshot.getChildren()){
                    Contacts contacts = contactsSnapshot.getValue(Contacts.class);
                    if(contacts.getEmail().equals(MyApplication.email)){
                        contactList2.add(contacts);}
                }

                adapter = new ContactsList2(SendSmsSafe.this,contactList2);
                listViewContacts2.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



///////////////////////////

        listViewContacts2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showUpdateDialog(position);

             /*  ContactsList adapter = new ContactsList(PhoneDatabase.this,contactList);
                PhoneDatabase phoneDatabase= (PhoneDatabase) adapter.getNumber(); // ekhane .getNumber()
                Intent i = new Intent(this, MainActivity.class);
                // i.putExtra("Editing", imageUpload);
                startActivity(i);*/
                /*
                Contacts contacts= (Contacts) adapter.getItem(position);
                //  String uri = "tel:" +  contacts.getNumber();
                String uri = contacts.getNumber();
                sms = msg.getText().toString();
                if(!sms.isEmpty()){ startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", uri, "llala")).putExtra("sms_body", sms ));}
                else{
              sms="Reached Safely!";

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", uri, "llala")).putExtra("sms_body", sms ));}*/
             /*   Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);*/

            }
        });

        /*
listViewContacts1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
   /*     Contacts contacts= (Contacts) adapter.getItem(position);
        String uri = "tel:" + contacts.getNumber() ;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", uri, "llala")).putExtra("sms_body", "long clck"));
*/
        /////////////  Contacts contacts= (Contacts) adapter.getItem(position);
        ////////  // String uri = "tel:" + contacts.getNumber() ;
   /*     String num="100";
       String uri = "tel:" + num ;

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
      return true;
    }
});
*/

    }
////////////////////////////////////////////////////////
/*
    public void sendSMS(View v) {

        num = noo.getText().toString();
        sms = msg.getText().toString();
        /*Bundle data = getIntent().getExtras();
        if(data==null){return;}
        else {
            STRING_I_NEED = data.getString("STRING_I_NEED");

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", num, "llala")).putExtra("sms_body", sms+STRING_I_NEED));
        }*/
////////
    /*
        if (!sms.isEmpty()) {
if(STRING_I_NEED!=null) {
    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", num, "llala")).putExtra("sms_body", sms + STRING_I_NEED));
}           else {
    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", num, "llala")).putExtra("sms_body", sms ));
}
}
         else{
       //     double latitude,longitude;
        //    latitude = location.getLatitude();
         //   longitude = location.getLongitude();


         Toast.makeText(this,sms,Toast.LENGTH_LONG).show();
if(STRING_I_NEED!=null)
{          startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", num, "llala")).putExtra("sms_body", sms+STRING_I_NEED));}
else {
    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", num, "llala")).putExtra("sms_body", sms ));
}
    }

    }
    */
    //////////////////////////////////////////////


   /* protected void onStart() {
        super.onStart();



                adapter = new ContactsList(SendSmsActivity.this,contactList);
                listViewContacts.setAdapter(adapter);
            }*/
/*
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             /*   ContactsList adapter = new ContactsList(PhoneDatabase.this,contactList);
                PhoneDatabase phoneDatabase= (PhoneDatabase) adapter.getNumber(); // ekhane .getNumber()
                Intent i = new Intent(this, MainActivity.class);
                // i.putExtra("Editing", imageUpload);
                startActivity(i);*/

          /*      Contacts contacts= (Contacts) adapter.getItem(position);
                String uri = "tel:" + contacts.getNumber() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

    }

      /*  btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /*     String uri="smsto"+num;
                Intent intent= new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(uri));
                startActivity(intent);*/
    //  Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:01716472113"));
    //     smsIntent.putExtra("sms_body","Reached Safely");
    //  startActivity(smsIntent);
    /*        }
        });

        */
}

    /*
    protected void sendSms(){
String number=no.getText().toString();
String message=msg.getText().toString();
        SmsManager manager = new SmsManager.getDefault();

    }*/
//}

