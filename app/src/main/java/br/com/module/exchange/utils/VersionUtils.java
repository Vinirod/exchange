package br.com.module.exchange.utils;

import android.os.Build;
import android.transition.Explode;
import android.view.Window;

public class VersionUtils {

    /**
     * Method for enable the animation of transition, in case the version OS greater than api 21
     */
    public static void checkVersionAndEnableTransition(Window window) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            window.setExitTransition(new Explode());
        }
    }

}
