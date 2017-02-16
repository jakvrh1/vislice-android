package com.jakvrh1.visliceWajslce;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by jakvrh1 on 2/9/17.
 */

public class Font {
    static public Typeface diggi_ziggy(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "digi_ziggy.ttf");
    }
}
