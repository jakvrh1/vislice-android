package com.jakvrh1.visliceWajslce.Razredi_Besede;

import android.content.Context;

public class Drzave extends Besede{

    public Drzave(Context context) {
        super(context);
    }

    @Override
    String asset_file() {
        return "seznam_drzav.txt";
    }
}