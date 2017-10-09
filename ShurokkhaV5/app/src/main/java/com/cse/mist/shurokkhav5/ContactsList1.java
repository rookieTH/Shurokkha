package com.cse.mist.shurokkhav5;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cse.mist.shurokkhav5.Contacts;
import com.cse.mist.shurokkhav5.R;

import java.util.List;

public class ContactsList1 extends ArrayAdapter<Contacts> {
    private Activity context;
    private List<Contacts> contactsList1;

    public ContactsList1(Activity context,List<Contacts>contactsList1){
        super(context, R.layout.list_layout2,contactsList1);
        this.context = context;
        this.contactsList1=contactsList1;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout2,null,true);

        TextView textviewname1= (TextView)listViewItem.findViewById(R.id.textviewname1);
        TextView textviewnumber1= (TextView)listViewItem.findViewById(R.id.textviewnumber1);
        Contacts contacts = contactsList1.get(position);
        textviewname1.setText(contacts.getName());
        textviewnumber1.setText(contacts.getNumber());

        return listViewItem;
    }
}
