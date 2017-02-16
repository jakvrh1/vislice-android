package com.jakvrh1.vislice;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by jakvrh1 on 2/5/17.
 */

public class MenuActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MenuFragment();
    }
}
