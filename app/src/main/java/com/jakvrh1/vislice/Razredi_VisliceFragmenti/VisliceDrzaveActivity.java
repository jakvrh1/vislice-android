package com.jakvrh1.vislice.Razredi_VisliceFragmenti;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.jakvrh1.vislice.SingleFragmentActivity;

/**
 * Created by jakvrh1 on 2/5/17.
 */

public class VisliceDrzaveActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new VisliceDrzaveFragment();
    }
}
