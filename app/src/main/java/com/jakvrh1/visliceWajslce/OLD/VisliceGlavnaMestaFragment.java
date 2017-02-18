/*package com.jakvrh1.visliceWajslce.OLD;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakvrh1.visliceWajslce.DialogScoreFragment;
import com.jakvrh1.visliceWajslce.R;
import com.jakvrh1.visliceWajslce.Razredi_Besede.GlavnaMesta;

import java.util.Random;

/**
 * Created by jakvrh1 on 2/10/17.
 */
/*
public class VisliceGlavnaMestaFragment extends Fragment {

    static public String SHARED_PREFERENCES_VISLICE = "sharedpreferences_vislice_glavna_mesta";
    static public String ST_HIGH_SCORE = "ST_HIGH_SCORE_GLAVNA_MESTA";
    static public String ST_CURRENT_SCORE = "ST_CURRENT_SCORE_GLAVNA_MESTA";
    static public String ST_PRESKOK = "ST_PRESKOK_GLAVNA_MESTA";
    static public String ST_DVE_CRKI = "ST_DVE_CRKI_GLAVNA_MESTA";
    static public String ST_CRKA = "ST_CRKA_GLAVNA_MESTA";

    static public String ST_CURRENT_WORD = "ST_CURRENT_WORD_GLAVNA_MESTA";
    static public String ST_CURRENT_KNOWN_CHARS = "ST_CURRENT_KNOWN_CHARS_GLAVNA_MESTA";
    static public String ST_CURRENT_ZGRESENIH = "ST_ZGRESENIH_GLAVNA_MESTA";
    static public String ST_NEXT_PERK = "ST_NEXT_PERK_MESTA";

    static private int V_ST_ZGRESENIH = 9, V_ST_PRESKOK = 11, V_ST_DVE_CRKI = 6, V_ST_CRKA = 3;

    private ImageView mVislice;
    private TextView mBeseda, mCurrent;

    private Button a, b, c, cc, d, e, f, g, h, i, j, k, l, m, n, o, p, r, s, ss, t, u, v, z, zz, q , w, y, x;
    private Button mPreskok, mDveCrki, mCrka;

    private String beseda = null, trenutna_beseda = null, trenutno_znano = null;

    private int st_zgresenih = 0, st_count = 0, st_highestScore = 0;
    private int preskok = 0, dve_crki = 0, crka = 0, next = 0;

    private Handler handler = new Handler();
    private GlavnaMesta drzave = null;
    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drzave = new GlavnaMesta(getActivity());

        SharedPreferences prefs = getActivity().getSharedPreferences(SHARED_PREFERENCES_VISLICE, Context.MODE_PRIVATE);
        st_highestScore = prefs.getInt(ST_HIGH_SCORE, -1);
        st_count = prefs.getInt(ST_CURRENT_SCORE, 0);
        preskok = prefs.getInt(ST_PRESKOK, 0);
        dve_crki = prefs.getInt(ST_DVE_CRKI, 0);
        crka = prefs.getInt(ST_CRKA, 0);
        trenutna_beseda = prefs.getString(ST_CURRENT_WORD, null);
        trenutno_znano = prefs.getString(ST_CURRENT_KNOWN_CHARS, null);
        st_zgresenih = prefs.getInt(ST_CURRENT_ZGRESENIH, 0);
        next = prefs.getInt(ST_NEXT_PERK, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vislice_glavna_mesta_fragment, container, false);

        mVislice = (ImageView) view.findViewById(R.id.vislice_image);
        mVislice.setImageResource(R.drawable.vislice1);
        mBeseda = (TextView) view.findViewById(R.id.vislice_beseda);
        if (trenutno_znano != null) {
            mBeseda.setText(trenutno_znano);
            slike_vislice();
        }

        mCurrent = (TextView) view.findViewById(R.id.vislice_mesta_current);
        mCurrent.setText("TR: " + Integer.toString(st_count));

        mPreskok = (Button) view.findViewById(R.id.vislice_skok);
        mDveCrki = (Button) view.findViewById(R.id.vislice_dve_crki);
        mCrka = (Button) view.findViewById(R.id.vislice_crka);

        perk_listeners();
        perks();
        initialize_chars(view);
        if (mBeseda.getText().toString() == "" || trenutna_beseda == null) {
            set_beseda();
        }

        return view;
    }

    private void check_if_in_word(final char c, View v) {
        if (v.getAlpha() == 0.25F || st_zgresenih >= V_ST_ZGRESENIH) {
            return;
        }
        StringBuilder sb = new StringBuilder(mBeseda.getText().toString());
        boolean is_in = false;

        System.out.println(sb.toString());
        System.out.println(trenutna_beseda);

        for (int i = 0; i < trenutna_beseda.length(); ++i) {
            if (trenutna_beseda.charAt(i) == c) {
                sb.setCharAt(i, c);
                is_in = true;
            }
        }

        if (is_in == false) {
            ++st_zgresenih;
            slike_vislice();
        }

        mBeseda.setText(sb.toString());

        if (st_zgresenih >= V_ST_ZGRESENIH) {
            mBeseda.setText(trenutna_beseda);
            updateHighScore();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reset_perks();
                    FragmentManager m = VisliceGlavnaMestaFragment.this.getFragmentManager();
                    DialogScoreFragment.newInstance(st_count, st_highestScore).show(m, "tagy");
                    st_count = 0;
                    refresh();
                    st_zgresenih = 0;

                }
            }, 1500);
        } else {
            if (has_finished()) {
                st_zgresenih = 0;
                ++st_count;
                add_perks();
                delayed_refresh(800);

            }
        }

    }

    private void updateHighScore() {
        if (st_count > st_highestScore) {
            st_highestScore = st_count;
        }
    }

    private void slike_vislice() {
        switch (st_zgresenih) {
            case 1:
                mVislice.setImageResource(R.drawable.vislice2);
                break;
            case 2:
                mVislice.setImageResource(R.drawable.vislice3);
                break;
            case 3:
                mVislice.setImageResource(R.drawable.vislice4);
                break;
            case 4:
                mVislice.setImageResource(R.drawable.vislice5);
                break;
            case 5:
                mVislice.setImageResource(R.drawable.vislice6);
                break;
            case 6:
                mVislice.setImageResource(R.drawable.vislice7);
                break;
            case 7:
                mVislice.setImageResource(R.drawable.vislice8);
                break;
            case 8:
                mVislice.setImageResource(R.drawable.vislice9);
                break;
            case 9:
                mVislice.setImageResource(R.drawable.vislice10);
                break;
        }
    }

    private void reset_perks() {
        preskok = 0;
        crka = 0;
        dve_crki = 0;
        next = 0;
    }

    private void perk_listeners() {
        mPreskok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getAlpha() == 1.0F) {
                    ++st_count;
                    st_zgresenih = 0;
                    --preskok;
                    refresh();
                    perks();
                }
            }
        });

        mDveCrki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getAlpha() == 1.0F) {
                    one_random_char();
                    one_random_char();
                    --dve_crki;
                    perks();

                }
            }
        });

        mCrka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getAlpha() == 1.0F) {
                    one_random_char();
                    --crka;
                    perks();
                }
            }
        });
    }

    private void one_random_char() {
        boolean free = false;
        for (int i = 0; i < trenutna_beseda.length(); ++i) {
            if (mBeseda.getText().toString().charAt(i) == '_') {
                free = true;
            }
        }
        while (free) {
            int r = random.nextInt(trenutna_beseda.length());
            for (int i = r; i < trenutna_beseda.length(); ++i) {
                if (mBeseda.getText().toString().charAt(i) == '_') {
                    perform_clicking(trenutna_beseda.charAt(i));
                    free = false;
                    break;
                }
            }
        }
    }

    private void add_perks() {
        if (st_count % V_ST_PRESKOK == 0 && next == 2) {
            next = 0;
            ++preskok;
        } else if (st_count % V_ST_DVE_CRKI == 0 && next == 1) {
            ++next;
            ++dve_crki;
        } else if (st_count % V_ST_CRKA == 0 && next == 0) {
            ++next;
            ++crka;
        }
    }

    private void delayed_refresh(int delay) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh();
            }
        }, delay);
    }

    private boolean has_finished() {
        for (int i = 0; i < mBeseda.getText().toString().length(); ++i) {
            if (mBeseda.getText().toString().charAt(i) == '_') {
                return false;
            }
        }
        return true;
    }

    private void set_beseda() {

        mVislice.setImageResource(R.drawable.vislice1);

        beseda = drzave.beseda(random.nextInt(drzave.stevilo_besed()));

        StringBuilder sb = new StringBuilder("");
        StringBuilder t_sb = new StringBuilder("");

        String[] arr = beseda.split(" ");
        boolean new_line = false;
        int max_lenght = 0;

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[i].length(); ++j) {
                sb.append(" _");
                t_sb.append(" " + arr[i].charAt(j));

            }
            if (arr.length - 1 == i) {
                break;
            }
            if ((sb.length() + (arr[i + 1].length() * 2)) >= 22) {
                sb.append("\n");
                t_sb.append("\n");
            } else {
                sb.append("    ");
                t_sb.append("    ");
            }
        }

        trenutna_beseda = t_sb.toString();
        trenutna_beseda = trenutna_beseda.toLowerCase();
        mBeseda.setText(sb.toString());
    }

    @Override
    public void onStop() {
        super.onStop();

        if(trenutna_beseda.equalsIgnoreCase(trenutno_znano)) {
            beseda = null;
            trenutna_beseda = null;
            trenutno_znano = null;
            st_zgresenih = 0;
            st_count = 0;
            st_highestScore = 0;
            reset_perks();
        }

        SharedPreferences settings = getActivity().getSharedPreferences(SHARED_PREFERENCES_VISLICE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(ST_HIGH_SCORE, st_highestScore);
        editor.putInt(ST_CURRENT_SCORE, st_count);
        editor.putInt(ST_PRESKOK, preskok);
        editor.putInt(ST_DVE_CRKI, dve_crki);
        editor.putInt(ST_CRKA, crka);
        editor.putString(ST_CURRENT_WORD, trenutna_beseda);
        editor.putString(ST_CURRENT_KNOWN_CHARS, mBeseda.getText().toString());
        editor.putInt(ST_CURRENT_ZGRESENIH, st_zgresenih);
        editor.putInt(ST_NEXT_PERK, next);
        editor.commit();
    }

    private void perks() {
        if (preskok > 0) {
            mPreskok.setAlpha(1.0F);
        } else {
            mPreskok.setAlpha(0.25F);
        }

        if (dve_crki > 0) {
            mDveCrki.setAlpha(1.0F);
        } else {
            mDveCrki.setAlpha(0.25F);
        }

        if (crka > 0) {
            mCrka.setAlpha(1.0F);
        } else {
            mCrka.setAlpha(0.25F);
        }
    }

    private void perform_clicking(char c) {
        if (c == 'a') a.performClick();
        else if (c == 'b') b.performClick();
        else if (c == 'c') this.c.performClick();
        else if (c == 'č') cc.performClick();
        else if (c == 'd') d.performClick();
        else if (c == 'e') e.performClick();
        else if (c == 'f') f.performClick();
        else if (c == 'g') g.performClick();
        else if (c == 'h') h.performClick();
        else if (c == 'i') i.performClick();
        else if (c == 'j') j.performClick();
        else if (c == 'k') k.performClick();
        else if (c == 'l') l.performClick();
        else if (c == 'm') m.performClick();
        else if (c == 'n') n.performClick();
        else if (c == 'o') o.performClick();
        else if (c == 'p') p.performClick();
        else if (c == 'r') r.performClick();
        else if (c == 's') s.performClick();
        else if (c == 'š') ss.performClick();
        else if (c == 't') t.performClick();
        else if (c == 'u') u.performClick();
        else if (c == 'v') v.performClick();
        else if (c == 'z') z.performClick();
        else if (c == 'ž') zz.performClick();
        else if (c == 'q') q.performClick();
        else if (c == 'w') w.performClick();
        else if (c == 'y') y.performClick();
        else if (c == 'x') x.performClick();
    }

    private void refresh() {
        a.setAlpha(1.0F);
        b.setAlpha(1.0F);
        c.setAlpha(1.0F);
        cc.setAlpha(1.0F);
        d.setAlpha(1.0F);
        e.setAlpha(1.0F);
        f.setAlpha(1.0F);
        g.setAlpha(1.0F);
        h.setAlpha(1.0F);
        i.setAlpha(1.0F);
        j.setAlpha(1.0F);
        k.setAlpha(1.0F);
        l.setAlpha(1.0F);
        m.setAlpha(1.0F);
        n.setAlpha(1.0F);
        o.setAlpha(1.0F);
        p.setAlpha(1.0F);
        r.setAlpha(1.0F);
        s.setAlpha(1.0F);
        ss.setAlpha(1.0F);
        t.setAlpha(1.0F);
        u.setAlpha(1.0F);
        v.setAlpha(1.0F);
        z.setAlpha(1.0F);
        zz.setAlpha(1.0F);
        q.setAlpha(1.0F);
        w.setAlpha(1.0F);
        y.setAlpha(1.0F);
        x.setAlpha(1.0F);

        perks();
        mCurrent.setText("TR: " + Integer.toString(st_count));
        set_beseda();
    }

    private void initialize_chars(View view) {

        a = (Button) view.findViewById(R.id.vislice_a);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('a', v);
                v.setAlpha(0.25F);
            }
        });
        b = (Button) view.findViewById(R.id.vislice_b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('b', v);
                v.setAlpha(0.25F);
            }
        });
        c = (Button) view.findViewById(R.id.vislice_c);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('c', v);
                v.setAlpha(0.25F);
            }
        });
        cc = (Button) view.findViewById(R.id.vislice_cc);
        cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('č', v);
                v.setAlpha(0.25F);
            }
        });
        d = (Button) view.findViewById(R.id.vislice_d);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('d', v);
                v.setAlpha(0.25F);
            }
        });
        e = (Button) view.findViewById(R.id.vislice_e);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('e', v);
                v.setAlpha(0.25F);
            }
        });
        f = (Button) view.findViewById(R.id.vislice_f);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('f', v);
                v.setAlpha(0.25F);
            }
        });
        g = (Button) view.findViewById(R.id.vislice_g);
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('g', v);
                v.setAlpha(0.25F);
            }
        });
        h = (Button) view.findViewById(R.id.vislice_h);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('h', v);
                v.setAlpha(0.25F);
            }
        });
        i = (Button) view.findViewById(R.id.vislice_i);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('i', v);
                v.setAlpha(0.25F);
            }
        });
        j = (Button) view.findViewById(R.id.vislice_j);
        j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('j', v);
                v.setAlpha(0.25F);
            }
        });
        k = (Button) view.findViewById(R.id.vislice_k);
        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('k', v);
                v.setAlpha(0.25F);
            }
        });
        l = (Button) view.findViewById(R.id.vislice_l);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('l', v);
                v.setAlpha(0.25F);
            }
        });
        m = (Button) view.findViewById(R.id.vislice_m);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('m', v);
                v.setAlpha(0.25F);
            }
        });
        n = (Button) view.findViewById(R.id.vislice_n);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('n', v);
                v.setAlpha(0.25F);
            }
        });
        o = (Button) view.findViewById(R.id.vislice_o);
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('o', v);
                v.setAlpha(0.25F);
            }
        });
        p = (Button) view.findViewById(R.id.vislice_p);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('p', v);
                v.setAlpha(0.25F);
            }
        });
        r = (Button) view.findViewById(R.id.vislice_r);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('r', v);
                v.setAlpha(0.25F);
            }
        });
        s = (Button) view.findViewById(R.id.vislice_s);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('s', v);
                v.setAlpha(0.25F);
            }
        });
        ss = (Button) view.findViewById(R.id.vislice_ss);
        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('š', v);
                v.setAlpha(0.25F);
            }
        });
        t = (Button) view.findViewById(R.id.vislice_t);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('t', v);
                v.setAlpha(0.25F);
            }
        });
        u = (Button) view.findViewById(R.id.vislice_u);
        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('u', v);
                v.setAlpha(0.25F);
            }
        });
        v = (Button) view.findViewById(R.id.vislice_v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('v', v);
                v.setAlpha(0.25F);
            }
        });
        z = (Button) view.findViewById(R.id.vislice_z);
        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('z', v);
                v.setAlpha(0.25F);
            }
        });
        zz = (Button) view.findViewById(R.id.vislice_zz);
        zz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('ž', v);
                v.setAlpha(0.25F);
            }
        });

        q = (Button) view.findViewById(R.id.vislice_q);
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('q', v);
                v.setAlpha(0.25F);
            }
        });

        w = (Button) view.findViewById(R.id.vislice_w);
        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('w', v);
                v.setAlpha(0.25F);
            }
        });

        y = (Button) view.findViewById(R.id.vislice_y);
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('y', v);
                v.setAlpha(0.25F);
            }
        });

        x = (Button) view.findViewById(R.id.vislice_x);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_if_in_word('x', v);
                v.setAlpha(0.25F);
            }
        });
    }

}
*/