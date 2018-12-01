package com.hackapi.childsafe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hackapi.childsafe.adapters.FlaggedItemsAdapter;
import com.hackapi.childsafe.pojos.FlaggedData;
import com.hackapi.childsafe.utils.CustomNotificationHandler;
import com.hackapi.childsafe.utils.VerticalSpace;
import com.onesignal.OneSignal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FlaggedItemsAdapter flaggedItemsAdapter;
    ArrayList<FlaggedData> allFlaggedData;
    RecyclerView rvFlaggedDataRecycler;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationReceivedHandler(new CustomNotificationHandler())
                .init();
    }

    private void init() {
        allFlaggedData = new ArrayList<>();
        flaggedItemsAdapter = new FlaggedItemsAdapter(MainActivity.this, allFlaggedData);
        rvFlaggedDataRecycler = (RecyclerView) findViewById(R.id.rvFlaggedDataRecycler);
        rvFlaggedDataRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvFlaggedDataRecycler.addItemDecoration(new VerticalSpace(30));
        rvFlaggedDataRecycler.setAdapter(flaggedItemsAdapter);
    }

}
