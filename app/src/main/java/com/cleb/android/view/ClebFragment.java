package com.cleb.android.view;

import android.app.Fragment;

/**
 * Created by FengMap on 17/12/22.
 */

public class ClebFragment extends Fragment {

    protected boolean mShow;

    public void show() {
        mShow = true;
        getActivity().getFragmentManager().beginTransaction().show(this).commit();
    }

    public void hide() {
        mShow = false;
        getActivity().getFragmentManager().beginTransaction().hide(this).commit();
    }

    public boolean isShowing() {
        return mShow;
    }

}
