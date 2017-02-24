package com.jakvrh1.visliceWajslce;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jakvrh1.visliceWajslce.Razredi_VisliceFragmenti.VisliceDrzaveFragment;
import com.jakvrh1.visliceWajslce.Razredi_VisliceFragmenti.VisliceGlavnaMestaFragment;
import com.jakvrh1.visliceWajslce.Razredi_VisliceFragmenti.VisliceZnamkeAvtoFragment;

import org.w3c.dom.Text;

/**
 * Created by jakvrh1 on 2/11/17.
 */

public class RezultatiFragment extends Fragment {

    private TextView mDrzave;
    private TextView mMesta;
    private TextView mAvtoZnamke;
    private TextView mRezultati;
    private int s_drzave, s_mesta, c_drzave, c_mesta, s_avto, c_avto;
    private AdView mAdView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getActivity().getSharedPreferences(VisliceDrzaveFragment.SHARED_PREFERENCES_VISLICE(), Context.MODE_PRIVATE);
        s_drzave = prefs.getInt(VisliceDrzaveFragment.ST_HIGH_SCORE(), 0);
        c_drzave = prefs.getInt(VisliceDrzaveFragment.ST_CURRENT_SCORE(), 0);

        prefs = getActivity().getSharedPreferences(VisliceGlavnaMestaFragment.SHARED_PREFERENCES_VISLICE(), Context.MODE_PRIVATE);
        s_mesta = prefs.getInt(VisliceGlavnaMestaFragment.ST_HIGH_SCORE(), 0);
        c_mesta = prefs.getInt(VisliceGlavnaMestaFragment.ST_CURRENT_SCORE(), 0);

        prefs = getActivity().getSharedPreferences(VisliceGlavnaMestaFragment.SHARED_PREFERENCES_VISLICE(), Context.MODE_PRIVATE);
        s_mesta = prefs.getInt(VisliceGlavnaMestaFragment.ST_HIGH_SCORE(), 0);
        c_mesta = prefs.getInt(VisliceGlavnaMestaFragment.ST_CURRENT_SCORE(), 0);

        prefs = getActivity().getSharedPreferences(VisliceZnamkeAvtoFragment.SHARED_PREFERENCES_VISLICE(), Context.MODE_PRIVATE);
        s_avto = prefs.getInt(VisliceZnamkeAvtoFragment.ST_HIGH_SCORE(), 0);
        c_avto = prefs.getInt(VisliceZnamkeAvtoFragment.ST_CURRENT_SCORE(), 0);


        exam_scores();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rezultati_fragment, container, false);

        mDrzave = (TextView) v.findViewById(R.id.rezultati_drzav);
        mDrzave.setText(" -> " + Integer.toString(s_drzave));

        mMesta = (TextView) v.findViewById(R.id.rezultati_mesta);
        mMesta.setText(" -> " + Integer.toString(s_mesta));

        mAvtoZnamke = (TextView) v.findViewById(R.id.rezultati_avtomobilskih_znamk);
        mAvtoZnamke.setText(" -> " + Integer.toString(s_avto));

        mRezultati = (TextView) v.findViewById(R.id.rezultati_rezultati);
        mRezultati.setTypeface(Font.diggi_ziggy(getContext()));

        mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return v;
    }

    private void exam_scores() {
        if(c_drzave > s_drzave) {
            s_drzave = c_drzave;
        }
        if(c_mesta > s_mesta) {
            s_mesta = c_mesta;
        }

        if(c_avto > s_avto) {
            s_avto = c_avto;
        }
    }
}
