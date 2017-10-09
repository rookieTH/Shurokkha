package com.cse.mist.shurokkhav5;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override //called when fragment is called
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override //
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override //layout inflate kore :|
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
View view = inflater.inflate(R.layout.fragment_profile, container, false);
      /*  ListView listViewContacts;
        List<Contacts> contactList;
         ContactsList adapter;
        listViewContacts=(ListView)view.findViewById(R.id.listViewContacts);
        contactList=new ArrayList<>();
        // Inflate the layout for this fragment
        adapter = new ContactsList(getActivity(),contactList);
        listViewContacts.setAdapter(adapter);*/
        return view;

    }

    @Override //instance dibe of activities, eta on create of activity, orientation change holeo lage
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override //fragment destroy korte
    public void onDestroy() {
        super.onDestroy();
    }

    @Override //completely fragment destroy kore apparently
    public void onDetach() {
        super.onDetach();
    }
}
