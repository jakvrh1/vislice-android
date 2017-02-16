package com.jakvrh1.vislice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.jakvrh1.vislice.Razredi_VisliceFragmenti.VisliceDrzaveActivity;
import com.jakvrh1.vislice.Razredi_VisliceFragmenti.VisliceGlavnaMestaActivity;

public class MenuFragment extends Fragment {

    private Button mDrzave;
    private Button mGlavnaMesta;
    private Button mRezultati;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);

        mDrzave = (Button) view.findViewById(R.id.menu_drzave);
        mDrzave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VisliceDrzaveActivity.class);
                startActivity(intent);
            }
        });

        mGlavnaMesta = (Button) view.findViewById(R.id.menu_glavna_mesta);
        mGlavnaMesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VisliceGlavnaMestaActivity.class);
                startActivity(intent);
            }
        });

        mRezultati = (Button) view.findViewById(R.id.menu_rezultati);
        mRezultati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }else{
                    Intent intent = new Intent(getContext(), RezultatiActivity.class);
                    startActivity(intent);
                }
            }
        });

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-5083694784305282/6735757459");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                requestNewInterstitial();
                Intent intent = new Intent(getContext(), RezultatiActivity.class);
                startActivity(intent);
            }
        });

        requestNewInterstitial();

        mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
