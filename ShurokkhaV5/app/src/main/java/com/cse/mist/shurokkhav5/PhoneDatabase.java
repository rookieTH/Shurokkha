
package com.cse.mist.shurokkhav5;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.data;
import static android.R.attr.x;
import static android.widget.Toast.LENGTH_SHORT;
import android.content.Intent;

import com.cse.mist.shurokkhav5.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PhoneDatabase extends AppCompatActivity  {
    ImageButton btn;
   /// EditText t1,t2,t3;
 ////   String n,r,b;
    DatabaseReference databaseContacts;
    DatabaseReference dataref;
    ListView listViewContacts;
    List<Contacts> contactList;
    private ContactsList adapter;
    String id="";
    String no="";
    String name="";

   FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser=auth.getCurrentUser();
    final String uid= firebaseUser.getUid();

    private final int PICK_CONTACT=1;
    /// ContentResolver cr = getContentResolver();
    ///Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
    ///  ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " , null, null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_database);
        getPermissionToReadUserContacts();
        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts");
        btn=(ImageButton) findViewById(R.id.imageButton);
        listViewContacts=(ListView)findViewById(R.id.listViewContacts);
        contactList=new ArrayList<>();
    /*    btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     addTrustedContacts();
            }
        })*/
     ///   ArrayList<String> al = new ArrayList<String>();
        ///TEMPORARY SOLUTION
/*
        listViewContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int arg2, long arg3) {

                // Can't manage to remove an item here
       contactList.remove(arg2);//where arg2 is position of item you click

       adapter.notifyDataSetChanged();


            return false;
            }

        }
        );*/
//listViewContacts.setOnItemLongClickListener(new AdapterView.OnItemClickListener());
    }
    ////////////

    ////////////
    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;

    // Called when the user is performing an action which requires the app to read the
    // user's contacts
    public void getPermissionToReadUserContacts() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_CONTACTS)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACTS_PERMISSIONS_REQUEST);
        }
    }

    // Callback with the request from calling requestPermissions(...)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_CONTACTS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read Contacts permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Read Contacts permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void callContacts(View v){
        Intent intent= new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent,PICK_CONTACT);

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,NavigationActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();


        databaseContacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contactList.clear();
                for(DataSnapshot contactsSnapshot : dataSnapshot.getChildren()){
                    Contacts contacts = contactsSnapshot.getValue(Contacts.class);
                    if(contacts.getEmail().equals(MyApplication.email)){
                    contactList.add(contacts);}
                }

                adapter = new ContactsList(PhoneDatabase.this,contactList);
                listViewContacts.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               final int p=position;
                AlertDialog.Builder a_builder = new AlertDialog.Builder(PhoneDatabase.this);
                a_builder.setMessage("Do you want to delete this contact?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //  finish(); // eta dile close kore na, background ta te jai, mane eta dile mainActivity te jai
                        // android.os.Process.killProcess(android.os.Process.myPid());
                        //finish();
                        // System.exit(1);
                        ////finishAffinity();
                        Contacts contacts= (Contacts) adapter.getItem(p);
                        //    Toast.makeText(this,contacts.getId(),Toast.LENGTH_LONG).show();
                        ///////////////   String uri = "tel:" + contacts.getNumber() ;
                        dataref= FirebaseDatabase.getInstance().getReference("contacts").child(contacts.getId());
                        dataref.removeValue();
                        showToast(contacts.getName());
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert =  a_builder.create();
                alert.setTitle("Delete Contact");
                alert.show();
/*
                Contacts contacts= (Contacts) adapter.getItem(position);
            //    Toast.makeText(this,contacts.getId(),Toast.LENGTH_LONG).show();
            ///////////////   String uri = "tel:" + contacts.getNumber() ;
                dataref= FirebaseDatabase.getInstance().getReference("contacts").child(contacts.getId());
                dataref.removeValue();
                showToast(contacts.getId());*/
                ////////////PHONE
               /*Intent intent = new Intent(Intent.ACTION_DIAL);
               intent.setData(Uri.parse(uri));
                startActivity(intent);
                */

            }
        });

    }

    public void showToast(String c){

        Toast.makeText(this,"Contact "+"'"+c+"'"+" Deleted ",Toast.LENGTH_LONG).show();
    }
public void remove(){


}

        protected void onActivityResult ( int reqCode, int resultCode, Intent data){

            super.onActivityResult(reqCode, resultCode, data);

            if (reqCode == PICK_CONTACT) {

                if (resultCode == AppCompatActivity.RESULT_OK) {


                    Cursor cursor = getContentResolver().query(data.getData(), null, null, null, null);
                    while (cursor.moveToNext()) {
                        id = cursor.getString(cursor.getColumnIndex("_id"));
                        if ("1".equalsIgnoreCase(cursor.getString(cursor.getColumnIndex("has_phone_number")))) {
                            Cursor cursorNo = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = " + id, null, null);
                            while (cursorNo.moveToNext()) {
                                if (cursorNo.getInt(cursorNo.getColumnIndex("data2")) == 2) {
                                    no = no.concat(cursorNo.getString(cursorNo.getColumnIndex("data1")));
                                    break;
                                }
                            }
                            cursorNo.close();
                        }
                    }
                    cursor.close();

                    String whereName = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ?";
                    String[] whereNameParams = new String[]{ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE, id};
                    Cursor nameCur = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
                    while (nameCur.moveToNext()) {
                        name = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME));
                    }
                    nameCur.close();
              ///      Toast.makeText(this, " Name : " + name + " No : " + no, Toast.LENGTH_SHORT).show();
                    String id = databaseContacts.push().getKey();
//if(uid!=null){
                 ///   if(uid!=null) {
                    FirebaseUser user=auth.getCurrentUser();
                    String userId=user.getUid();

                  //  Toast.makeText(this,userId,Toast.LENGTH_LONG).show();
                        Contacts contacts = new Contacts(id, name, no,MyApplication.email);
                    ///databaseContacts.child(userId).child(id).setValue(contacts);
                        databaseContacts.child(id).setValue(contacts);
                        Toast.makeText(this, "Contact Added!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, PhoneDatabase.class));
                   /// }
//}
                }

            }
        }
    }




