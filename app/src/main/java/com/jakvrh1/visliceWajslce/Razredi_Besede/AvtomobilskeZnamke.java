package com.jakvrh1.visliceWajslce.Razredi_Besede;

import android.content.Context;

/**
 * Created by jakvrh1 on 2/24/17.
 */

public class AvtomobilskeZnamke extends Besede {
    public AvtomobilskeZnamke(Context context) {
        super(context);
    }

    @Override
    String asset_file() {
        return "avtomobilske_znamke.txt";
    }
}
