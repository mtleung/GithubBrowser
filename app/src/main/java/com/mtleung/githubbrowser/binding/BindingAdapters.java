package com.mtleung.githubbrowser.binding;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by marco.t.leung on 7/12/2017.
 */

public class BindingAdapters {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
