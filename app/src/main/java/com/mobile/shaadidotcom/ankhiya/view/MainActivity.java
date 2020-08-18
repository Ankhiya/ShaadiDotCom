package com.mobile.shaadidotcom.ankhiya.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mobile.shaadidotcom.ankhiya.R;

/**
 * Launcher activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, CandidateListFragment.newInstance(),
                CandidateListFragment.TAG);
        transaction.commit();
    }
}
