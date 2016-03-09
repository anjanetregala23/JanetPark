package com.parku.au.janetpark.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import com.parku.au.janetpark.R;
import com.parku.au.janetpark.view.fragments.AboutFragment;
import com.parku.au.janetpark.view.fragments.SettingsFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    GoogleMap mMap;

    //drawer and navigation
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    //maps component
    private ImageView ivSearch, ivDrawer;
    private EditText etSearch;

    //drawerheader component
    private TextView tvProfileName;

    //maps variable
    private String strLocation;

    //user pfullname string
    private String strFullname;

    //for the image
    private ImageView ivImage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_application);


        //image in drawer
       // ivImage = (ImageView) findViewById(R.id.imageView);
        //textview for the user fullname
        tvProfileName = (TextView) findViewById(R.id.profileTextView);
        //drawer and navigation
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(navigationView);



        //getting the data from login activity
//        Intent intent = getIntent();
//        strFullname = intent.getStringExtra("fullname");
//        tvProfileName.setText(strFullname);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("userBundle");
        String username = bundle.getString("username");
        String email = bundle.getString("email");
        String contact_number = bundle.getString("contactNumber");

        tvProfileName.setText(username);

        //for showing the value in the setting preference
        //showUserSettings();

        //google map
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        setUpMapIfNeeded();

        onMapReady();

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

//        double longitude = location.getLongitude();
//        double latitude = location.getLatitude();
//
//        CameraPosition user = CameraPosition.builder()
//                .target(new LatLng(latitude, longitude))
//                .zoom(15)
//                .bearing(0)
//                .tilt(10)
//                .build();
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(user), 10, null);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            mMap.setMyLocationEnabled(false);
        }

        etSearch = (EditText) findViewById(R.id.search_editText);

        ivSearch = (ImageView) findViewById(R.id.search_imageView);
        ivSearch.setOnClickListener(this);

        ivDrawer = (ImageView) findViewById(R.id.drawer_button);
        ivDrawer.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.search_imageView) {

            strLocation = etSearch.getText().toString();

            List<Address> adressList = null;
            if (strLocation.isEmpty()) {
                Toast.makeText(this, "Enter Location", Toast.LENGTH_LONG).show();

            } else {
                Geocoder geocoder = new Geocoder(this);
                try {
                    adressList = geocoder.getFromLocationName(strLocation, 1);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Address address = adressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }

        } else if (view.getId() == R.id.drawer_button) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {

            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("User"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    private void onMapReady() {
//        LatLng MELBOURNE = new LatLng(15.16250354, 120.55411577);
//        Marker melbourne = mMap.addMarker(new MarkerOptions()
//                .position(MELBOURNE)
//                .title("Melbourne")
//                .snippet("Population: 4,137,400")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_face_black_24dp)));
//
//        LatLng PERTH = new LatLng(15.16190293, 120.55540323);
//        Marker perth = mMap.addMarker(new MarkerOptions()
//                .position(PERTH)
//                .flat(true)
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_face_black_24dp)));
//
//        LatLng CLARK = new LatLng(15.16066028, 120.55319309);
//        Marker clark = mMap.addMarker(new MarkerOptions()
//                .position(CLARK)
//                .title("Clark")
//                .snippet("Population: 4,137,400")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_face_black_24dp)));
//
//        LatLng FRIENDSHIP = new LatLng(15.16113663, 120.55585384);
//        Marker friendship = mMap.addMarker(new MarkerOptions()
//                .position(FRIENDSHIP)
//                .title("Friendship")
//                .snippet("Population: 4,137,400")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_face_black_24dp)));

        LatLng[] location_point = new LatLng[4];
        location_point[0] = new LatLng(15.16250354, 120.55411577);
        location_point[1] = new LatLng(15.16190293, 120.55540323);
        location_point[2] = new LatLng(15.16066028, 120.55319309);
        location_point[3] = new LatLng(15.16113663, 120.55585384);

        for (int i = 0; i < location_point.length; i++) {
            pinMarker(location_point[i]);
        }
    }

    private void pinMarker(LatLng point) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_face_black_24dp));
        mMap.addMarker(markerOptions);
    }


    //for the menu navigation
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
//        switch(menuItem.getItemId()) {
//            case R.id.settingsMenu:
//                Intent intent = new Intent(MainActivity.this, Settings.class);
//                startActivity(intent);

//        }

        Fragment fragment = null;

        Class fragmentClass = null;
        switch (menuItem.getItemId()) {
            case R.id.aboutMenu:
                fragmentClass = AboutFragment.class;
                break;
            case R.id.settingsMenu:
                fragmentClass = SettingsFragment.class;
                break;

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        // Highlight the selected item has been done by NavigationView
        // menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();

    }

}





