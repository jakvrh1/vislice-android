package com.jakvrh1.vislice.Razredi_Besede;

import android.content.Context;

/**
 * Created by jakvrh1 on 2/10/17.
 */

public class GlavnaMesta extends Besede {
    public GlavnaMesta(Context context) {
        super(context);
    }

    @Override
    String asset_file() {
        return "seznam_glavnih_mest.txt";
    }
}
