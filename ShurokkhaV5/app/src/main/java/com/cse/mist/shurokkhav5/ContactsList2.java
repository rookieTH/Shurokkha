package com.cse.mist.shurokkhav5;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by TASNEEA on 10/7/2017.
 */

public class ContactsList2  extends ArrayAdapter<Contacts> {
    private Activity context;
    private List<Contacts> contactsList2;

    public ContactsList2(Activity context,List<Contacts>contactsList2){
        super(context, R.layout.list_layout3,contactsList2);
        this.context = context;
        this.contactsList2=contactsList2;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout3,null,true);

        TextView textviewname2= (TextView)listViewItem.findViewById(R.id.textviewname2);
        TextView textviewnumber2= (TextView)listViewItem.findViewById(R.id.textviewnumber2);
        Contacts contacts = contactsList2.get(position);
        textviewname2.setText(contacts.getName());
        textviewnumber2.setText(contacts.getNumber());

        return listViewItem;
    }
}

