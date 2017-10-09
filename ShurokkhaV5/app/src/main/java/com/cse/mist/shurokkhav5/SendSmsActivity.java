package com.cse.mist.shurokkhav5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SendSmsActivity extends AppCompatActivity {
    EditText editno, msg;
    String num, sms;

    DatabaseReference databaseContacts;

    String id="";
    String no="";
    String name="";
    Button btnSendSms;
    ListView listViewContacts1;
    List<Contacts> contactList1;
    private ContactsList1 adapter;
    String STRING_I_NEED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        databaseContacts= FirebaseDatabase.getInstance().getReference("contacts");

        listViewContacts1 = (ListView) findViewById(R.id.listViewContacts1);
        contactList1 = new ArrayList<>();
        Bundle data = getIntent().getExtras();
        if(data==null){return;}
        else {
            STRING_I_NEED = data.getString("STRING_I_NEED");
        }
    }


    @Override
    protected void onStart() {
        super.onStart();


        databaseContacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contactList1.clear();
                for(DataSnapshot contactsSnapshot : dataSnapshot.getChildren()){
                    Contacts contacts = contactsSnapshot.getValue(Contacts.class);
                    if(contacts.getEmail().equals(MyApplication.email)){
                        contactList1.add(contacts);}
                }

                adapter = new ContactsList1(SendSmsActivity.this,contactList1);
                listViewContacts1.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



///////////////////////////

        listViewContacts1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             /*  ContactsList adapter = new ContactsList(PhoneDatabase.this,contactList);
                PhoneDatabase phoneDatabase= (PhoneDatabase) adapter.getNumber(); // ekhane .getNumber()
                Intent i = new Intent(this, MainActivity.class);
                // i.putExtra("Editing", imageUpload);
                startActivity(i);*/
                Contacts contacts= (Contacts) adapter.getItem(position);
                //  String uri = "tel:" +  contacts.getNumber();
                String uri = contacts.getNumber();
                String  sms="In Danger!";
                if(STRING_I_NEED==null) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", uri, "llala")).putExtra("sms_body", sms));
                }
                else{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", uri, "llala")).putExtra("sms_body", sms+STRING_I_NEED));
                }
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

       /* listViewContacts1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             /*   ContactsList adapter = new ContactsList(PhoneDatabase.this,contactList);
                PhoneDatabase phoneDatabase= (PhoneDatabase) adapter.getNumber(); // ekhane .getNumber()
                Intent i = new Intent(this, MainActivity.class);
                // i.putExtra("Editing", imageUpload);
                startActivity(i);*/

     /*           Contacts contacts= (Contacts) adapter.getItem(position);
                String uri = "tel:" + contacts.getNumber() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

    }
    public void sendSMS(View v) {

       num=editno.getText().toString();
            sms=msg.getText().toString();
            if(!sms.isEmpty()){
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms",num,"llala")).putExtra("sms_body",sms));}
                else  startActivity(new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms",num,"llala")).putExtra("sms_body","In Danger!"));

    }


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

