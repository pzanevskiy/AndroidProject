package com.example.rentalcar.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.rentalcar.R;
import com.example.rentalcar.db.entity.User;
import com.example.rentalcar.viewmodel.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavBarActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    UserViewModel userViewModel;
    Bundle args;
    String userId;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        args = getIntent().getExtras();
        if (args != null) {
            userId = String.valueOf(args.getInt("user"));
        }
        user=userViewModel.getUserById(Integer.parseInt(userId));

//        Bundle bundle = new Bundle();
//        bundle.putString("userId", userId);
//        HomeFragment homeFragment = new HomeFragment();
//        homeFragment.setArguments(bundle);

        openFragment(HomeFragment.newInstance(userId, user.getName(),user.getEmail(),String.valueOf(user.getMoney())));


    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container1, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_home: {
                    openFragment(HomeFragment.newInstance(userId, user.getName(),user.getEmail(),String.valueOf(user.getMoney())));
                    return true;
                }
                case R.id.menu_cars: {
                    openFragment(CarFragment.newInstance(userId));
                    return true;
                }
                case R.id.menu_current: {
                    openFragment(OrderFragment.newInstance(userId));
                    return true;
                }
                case R.id.menu_completed: {
                    openFragment(CompletedOrderFragment.newInstance(userId));
                    return true;
                }
//                case R.id.menu_penalty:{
//                    openFragment(Fragment.newInstance("",""));
//                    return true;
//                }
            }
            return false;
        }
    };

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}