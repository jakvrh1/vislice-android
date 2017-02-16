package com.jakvrh1.visliceWajslce.Razredi_VisliceFragmenti;

import android.support.v4.app.Fragment;

import com.jakvrh1.visliceWajslce.SingleFragmentActivity;

/**
 * Created by jakvrh1 on 2/10/17.
 */

public class VisliceGlavnaMestaActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new VisliceGlavnaMestaFragment();
    }
}
