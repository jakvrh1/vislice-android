package com.jakvrh1.vislice.Razredi_Besede;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by jakvrh1 on 2/10/17.
 */

abstract public class Besede {

    abstract String asset_file();

    private BufferedReader br;
    private LinkedList<String> seznam;

    public Besede(Context context) {
        seznam = new LinkedList<String>();
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().open(asset_file())));

            String drzava;
            while ((drzava = br.readLine()) != null) {
                seznam.add(drzava);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int stevilo_besed() {
        return seznam.size();
    }

    public String beseda(final int index) {
        return seznam.get(index);
    }
}
