package com.cse.mist.shurokkhav5;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by TASNEEA on 9/24/2017.
 */

public class ContactsList extends ArrayAdapter<Contacts>{
    private Activity context;
    private List<Contacts> contactsList;

    public ContactsList(Activity context,List<Contacts>contactsList){
        super(context,R.layout.list_layout,contactsList);
        this.context = context;
        this.contactsList=contactsList;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewName= (TextView)listViewItem.findViewById(R.id.textViewName);
        TextView textViewNumber= (TextView)listViewItem.findViewById(R.id.textViewNumber);
        Contacts contacts = contactsList.get(position);
        textViewName.setText(contacts.getName());
        textViewNumber.setText(contacts.getNumber());

        return listViewItem;
    }
}
