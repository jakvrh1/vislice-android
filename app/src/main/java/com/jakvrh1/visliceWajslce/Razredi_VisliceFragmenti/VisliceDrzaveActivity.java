package com.jakvrh1.visliceWajslce.Razredi_VisliceFragmenti;

import android.support.v4.app.Fragment;

import com.jakvrh1.visliceWajslce.SingleFragmentActivity;

/**
 * Created by jakvrh1 on 2/5/17.
 */

public class VisliceDrzaveActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new VisliceDrzaveFragment();
    }
}
