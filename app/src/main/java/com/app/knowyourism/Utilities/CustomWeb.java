package com.app.knowyourism.Utilities;

import android.content.Context;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import com.app.knowyourism.R;

public class CustomWeb {
    private final Context context;
    public CustomWeb(Context context){
        this.context=context;
    }
    public void openTab(String url){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorOnPrimary));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }
}
