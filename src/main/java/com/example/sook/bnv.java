package com.example.sook;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class bnv extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentDrink fragmentDrink = new FragmentDrink();
    private FragmentMeal fragmentMeal = new FragmentMeal();
    private FragmentCommu fragmentCommu = new FragmentCommu();
    private FragmentPref fragmentPref = new FragmentPref();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bnv);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.menu_1:
                    transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.menu_2:
                    transaction.replace(R.id.frameLayout, fragmentDrink).commitAllowingStateLoss();
                    break;
                case R.id.menu_3:
                    transaction.replace(R.id.frameLayout, fragmentMeal).commitAllowingStateLoss();
                    break;
                case R.id.menu_4:
                    transaction.replace(R.id.frameLayout, fragmentCommu).commitAllowingStateLoss();
                    break;
                case R.id.menu_5:
                    transaction.replace(R.id.frameLayout, fragmentPref).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}