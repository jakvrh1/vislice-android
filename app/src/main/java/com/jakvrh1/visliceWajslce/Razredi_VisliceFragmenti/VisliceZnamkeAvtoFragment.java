package com.jakvrh1.visliceWajslce.Razredi_VisliceFragmenti;

import com.jakvrh1.visliceWajslce.R;
import com.jakvrh1.visliceWajslce.Razredi_Besede.AvtomobilskeZnamke;
import com.jakvrh1.visliceWajslce.Razredi_Besede.Besede;

/**
 * Created by jakvrh1 on 2/24/17.
 */

public class VisliceZnamkeAvtoFragment extends Vislice {

    static public String SHARED_PREFERENCES_VISLICE() {
        return "SHARED_PREFERENCES_VISLICE_ZNAMKE_AVTOMOBIL";
    }

    static public String ST_HIGH_SCORE() {
        return "ST_HIGH_SCORE_ZNAMKE_AVTOMOBIL";
    }

    static public String ST_CURRENT_SCORE() {
        return "ST_CURRENT_SCORE_ZNAMKE_AVTOMOBIL";
    }

    @Override
    public String visliceName() {
        return "ZNAMKE_AVTOMOBIL";
    }

    @Override
    public Besede besede() {
        return new AvtomobilskeZnamke(getActivity());
    }

    @Override
    public int layout() {
        return R.layout.vislice_znamke_avtomobil;
    }

    @Override
    public int mCurrentID() {
        return R.id.vislice_avtomobilske_znamke_current;
    }

    @Override
    public int keyboard() {
        return Keyboards.ENGLISH.ordinal();
    }

    @Override
    public Object getCurrentFragmentManager() {
        return getFragmentManager();
    }
}
