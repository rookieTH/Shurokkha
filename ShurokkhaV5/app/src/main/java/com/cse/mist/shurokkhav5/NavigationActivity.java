package com.cse.mist.shurokkhav5;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener,SensorEventListener {
    View v;
    private SensorManager sensorManager;
    private Sensor sensor;
    //private Button btn;
   /* @Override
   /* public void onBackPressed() {

     int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
         super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }*/
    float lastx, lasty, lastz;
    long  lastTime=0;
    double SHAKE_THRESHOLD = 1000;
    private FirebaseAuth firebaseAuth;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private  DrawerLayout drawerLayout;
    private ListView navList;
    int save = -1;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    // private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ///pore thik korbo In shaa Allah , user name jate nav act e dekhai
      //  TextView nameView=(TextView)findViewById(R.id.showText);
       // nameView.setText(getIntent().getExtras().getString("username"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        v = new View(this);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //float a = sensor.getPower();
        //txt.setText( "  Power of Accela" + a);


        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener( this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

        firebaseAuth = FirebaseAuth.getInstance();
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        navList =(ListView)findViewById(R.id.navlist);
        ArrayList<String> navArray = new ArrayList<String>();
        navArray.add("Home");

        navArray.add("About App");
        navArray.add("Settings");
        navArray.add("Logout");

       /* navList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                           @Override
                                           public void onItemClick(AdapterView<?> parent, View view,
                                                                   int position, long id) {
                                               // TODO Auto-generated method stub

                                               parent.getChildAt(position).setBackgroundColor(
                                                       Color.parseColor("#A9BCF5"));

                                               if (save != -1 && save != position) {
                                                   parent.getChildAt(save).setBackgroundColor(
                                                           Color.parseColor("#d6e6ff"));
                                               }

                                               save = position;
                                           });*/
        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,navArray); //ekhane change korsi ar xml eo, etar jonno selected item ta dekhabe na
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(this);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.openDrawer,R.string.closeDrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

     loadSelection(0); //home selected thakbe, but ami selected rakhar kaj ta off korsi,karon pink ashe
    }
//////////////////////////////////
   @Override
    public void onBackPressed() {

       //finishAffinity();
       startActivity(new Intent(this,HelpActivity.class));
    }

    private void loadSelection(int i){
        navList.setItemChecked(i,true);

        switch (i) {

            case 0:
//I
                HomeFragment homeFragment = new HomeFragment();

                fragmentTransaction.replace(R.id.fragmentholder, homeFragment);
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, homeFragment);
                fragmentTransaction.addToBackStack(null); ///NOTUN ADD KORSI
                fragmentTransaction.setTransition(fragmentTransaction.TRANSIT_FRAGMENT_FADE);////
                fragmentTransaction.commit();
                break;
            case 1:

                ProfileFragment profileFragment = new ProfileFragment();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, profileFragment);
                fragmentTransaction.setTransition(fragmentTransaction.TRANSIT_FRAGMENT_FADE);////
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 2:
                SettingsFragment settingsFragment=new SettingsFragment();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,settingsFragment);
                fragmentTransaction.setTransition(fragmentTransaction.TRANSIT_FRAGMENT_FADE);////
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case 3:
                //Logout e apatoto diye rakhsi
                firebaseAuth.signOut();
                finishAffinity();
                break;


        }

    }
    ///gurantees that view objs are created, landscape and portrait er kichu ekta
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        actionBarDrawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings){
            return true;
        }else if(id== android.R.id.home){

            if(drawerLayout.isDrawerOpen(navList)){
                drawerLayout.closeDrawer(navList);
            }
            else drawerLayout.openDrawer(navList);
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        loadSelection(position);

        drawerLayout.closeDrawer(navList);
    }

    @Override
    public void onClick(View view) {
     //   Intent i = new Intent(this,DummyActivity.class);
     //   startActivity(i);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER  ) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            long curTime = System.currentTimeMillis();


            if ((curTime - lastTime) > 100) {
                long diffTime = (curTime - lastTime);

                lastTime = curTime;
                double speed = Math.abs(x - lastx + y - lasty + z - lastx) / diffTime * 5000;

                if (speed > SHAKE_THRESHOLD) {
                    Intent in = new Intent(this,ProfileActivity.class);
                    startActivity(in);
                }

                lastx = x;
                lasty = y;
                lastz = z;

            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
   /* public void onListItemClick(ListView listView, View view, int position,
                                long id) {
        v.setBackgroundResource(0);
        view.setBackgroundResource(R.color.afterSelect);
        v = view;
    }
*/

    @Override
    protected void onRestart() {
        super.onRestart();
        sensorManager.registerListener( this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener( this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


}
