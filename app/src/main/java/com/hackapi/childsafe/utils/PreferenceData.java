package com.hackapi.childsafe.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hackapi.childsafe.pojos.FlaggedData;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by KayO on 21/09/2017.
 */

public class PreferenceData {
    //store JSON for later display
    static final String PREF_FLAGGED_SITES = "flagged_sites";

    public static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setPrefFlaggedSites(Context ctx, ArrayList<FlaggedData> flaggedData) {
        HashSet<String> flagSet = new HashSet<>();
        for(FlaggedData data : flaggedData){
            flagSet.add(data.getImgUrl()+ " "+ data.getInitiatorUrl()+"|"+data.getDateTime());//unique delimiters
        }
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putStringSet(PREF_FLAGGED_SITES, flagSet);
        editor.apply();
    }

    public static ArrayList<FlaggedData> getPrefFlaggedSites(Context ctx) {
        ArrayList<FlaggedData> flaggedDataList = new ArrayList<>();
        for(String item : getSharedPreferences(ctx).getStringSet(PREF_FLAGGED_SITES, new HashSet<String>())){
            flaggedDataList.add(new FlaggedData(item.substring(0,item.indexOf(" ")),
                                                item.substring(item.indexOf(" ")+1,item.indexOf("|")),
                                                item.substring(item.indexOf("|")+1)
                                                ));
        }
        return flaggedDataList;
    }

}