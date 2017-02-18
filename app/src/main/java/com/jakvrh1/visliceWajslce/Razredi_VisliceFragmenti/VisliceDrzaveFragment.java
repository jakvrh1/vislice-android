package com.jakvrh1.visliceWajslce.Razredi_VisliceFragmenti;

import com.jakvrh1.visliceWajslce.R;
import com.jakvrh1.visliceWajslce.Razredi_Besede.Besede;
import com.jakvrh1.visliceWajslce.Razredi_Besede.Drzave;

/**
 * Created by jakvrh1 on 2/18/17.
 */

public class VisliceDrzaveFragment extends Vislice {

    static public String SHARED_PREFERENCES_VISLICE() {
        return "SHARED_PREFERENCES_VISLICE_DRZAVE";
    }

    static public String ST_HIGH_SCORE() {
        return "ST_HIGH_SCORE_DRZAVE";
    }

    static public String ST_CURRENT_SCORE() {
        return "ST_CURRENT_SCORE_DRZAVE";
    }

    @Override
    public String visliceName() {
        return "DRZAVE";
    }

    @Override
    public Besede besede() {
        return new Drzave(getActivity());
    }

    @Override
    public int layout() {
        return R.layout.vislice_drzave_fragment;
    }

    @Override
    public int mCurrentID() {
        return R.id.vislice_drzave_current;
    }

    @Override
    public int keyboard() {
        return Keyboards.SLOVENIAN.ordinal();
    }

    @Override
    public Object getCurrentFragmentManager() {
        return getFragmentManager();
    }
}
