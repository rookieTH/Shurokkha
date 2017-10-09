package com.cse.mist.shurokkhav5;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public SettingsFragment() {
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Button btnOpen= (Button)view.findViewById(R.id.addContacts);
   /////////     Button btnText=(Button)view.findViewById(R.id.addText);
        btnOpen.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                Intent in = new Intent(getActivity(),PhoneDatabase.class);
              //  in.putExtra("some","some data");
                startActivity(in);

            }
        });

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
