package com.badoo.testapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.badoo.testapp.R;
import com.badoo.testapp.helper.UIInterfaces;
import com.badoo.testapp.ui.fragment.ProductListFragment;


public class MainActivity extends AppCompatActivity implements UIInterfaces.FragmentChanger{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set product fragment by default
        changeFragment(new ProductListFragment());
    }

    @Override
    public void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .replace(R.id.content_view, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        // Pop fragments from stack until there is just one left!
        if(getSupportFragmentManager().getBackStackEntryCount() != 1)
            getSupportFragmentManager().popBackStack();
        else
            finish();
    }
}
