package com.jakvrh1.visliceWajslce;

import android.support.v4.app.Fragment;

/**
 * Created by jakvrh1 on 2/5/17.
 */

public class MenuActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MenuFragment();
    }
}
