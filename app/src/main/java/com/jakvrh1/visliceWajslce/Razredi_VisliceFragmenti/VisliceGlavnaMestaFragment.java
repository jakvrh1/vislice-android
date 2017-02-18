package com.jakvrh1.visliceWajslce.Razredi_VisliceFragmenti;

import com.jakvrh1.visliceWajslce.R;
import com.jakvrh1.visliceWajslce.Razredi_Besede.Besede;
import com.jakvrh1.visliceWajslce.Razredi_Besede.Drzave;
import com.jakvrh1.visliceWajslce.Razredi_Besede.GlavnaMesta;

/**
 * Created by jakvrh1 on 2/18/17.
 */

public class VisliceGlavnaMestaFragment extends Vislice {

    static public String SHARED_PREFERENCES_VISLICE() {
        return "SHARED_PREFERENCES_VISLICE_GLAVNA_MESTA";
    }

    static public String ST_HIGH_SCORE() {
        return "ST_HIGH_SCORE_GLAVNA_MESTA";
    }

    static public String ST_CURRENT_SCORE() {
        return "ST_CURRENT_SCORE_GLAVNA_MESTA";
    }

    @Override
    public String visliceName() {
        return "GLAVNA_MESTA";
    }

    @Override
    public Besede besede() {
        return new GlavnaMesta(getActivity());
    }

    @Override
    public int layout() {
        return R.layout.vislice_glavna_mesta_fragment;
    }

    @Override
    public int mCurrentID() {
        return R.id.vislice_mesta_current;
    }

    @Override
    public int keyboard() {
        return Vislice.Keyboards.ENGLISH.ordinal();
    }

    @Override
    public Object getCurrentFragmentManager() {
        return getFragmentManager();
    }
}
