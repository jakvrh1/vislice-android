package com.jakvrh1.visliceWajslce;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jakvrh1 on 2/5/17.
 */

public class DialogScoreFragment extends android.support.v4.app.DialogFragment {

    private static final String ST_COUNT = "ST_COUNT_DIALOG_FRAGMENT";
    private static final String ST_HIGH = "ST_HIGH_DIALOG_FRAGMENT";

    private Button mTryAgain;
    private TextView mCurrent, mHigh, mKonec;
    private int st_current, st_high;

    public static DialogScoreFragment newInstance(int count, int high) {
        Bundle args = new Bundle();
        args.putInt(ST_COUNT, count);
        args.putInt(ST_HIGH, high);

        DialogScoreFragment fragment = new DialogScoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        st_current = getArguments().getInt(ST_COUNT);
        st_high = getArguments().getInt(ST_HIGH);
        setCancelable(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_score_fragment, container, false);

        Typeface font = Font.diggi_ziggy(getContext());

        mKonec = (TextView) view.findViewById(R.id.dialog_konec);
        mKonec.setTypeface(font);
        mCurrent = (TextView) view.findViewById(R.id.dialog_current_score);
        mHigh = (TextView) view.findViewById(R.id.dialog_highest_score);
        setTextView();

        mTryAgain = (Button) view.findViewById(R.id.dialog_try_again);
        mTryAgain.setTypeface(font);
        mTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    private void setTextView() {
        if (st_high < st_current) {
            st_high = st_current;
        }
        mCurrent.setText("Trenutni rezultat: " + Integer.toString(st_current));
        mHigh.setText("Najboljši rezultat: " + Integer.toString(st_high));

    }


}
