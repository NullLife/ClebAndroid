package com.cleb.android.tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * 活动管理类。
 *
 * @author yangbin on 2017/12/11 16:18
 * @version 1.0.0
 */

public class ClebActivityManager {
    private static ClebActivityManager sInstance;

    private ArrayList<Activity> mActList;

    private ClebActivityManager() {
        mActList = new ArrayList<>();
    }


    public static ClebActivityManager instance() {
        if (sInstance == null) {
            synchronized (ClebActivityManager.class) {
                sInstance = new ClebActivityManager();
            }
        }
        return sInstance;
    }

    public void add(Activity act) {
        mActList.add(act);
    }

    public void remove(Activity act) {
        mActList.remove(act);
    }

    public void killAll() {
        for (Activity act : mActList) {
            act.finish();
        }
        mActList.clear();
    }

    public void startAndKillSelf(Activity oldAct, Class<?> newAct, Bundle bundle) {
        start(oldAct, newAct, bundle);

        oldAct.finish();
    }

    public void start(Activity oldAct, Class<?> newAct, Bundle bundle) {
        Intent intent = new Intent(oldAct, newAct);

        if (bundle != null)
            intent.putExtras(bundle);

        oldAct.startActivity(intent);
    }

}
