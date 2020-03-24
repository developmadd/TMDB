package com.madd.madd.tmdb.utilities;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.inputmethod.InputMethodManager;


import java.text.Normalizer;
import java.util.Objects;

public class Utilities {


    // Recursos para animacion de traslacion en Y

    public interface GetAnimationStatus{
        void animationStatus(boolean animationStatus);
    }

    public static void animateTranslationY(View v, int distance, GetAnimationStatus getAnimationStatus){
        ViewPropertyAnimator viewPropertyAnimator = v.animate().translationY(getDP(v.getContext(),distance));
        if( getAnimationStatus != null ){
            viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    getAnimationStatus.animationStatus(true);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    getAnimationStatus.animationStatus(false);
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    // Not required
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                    // Not required
                }
            });
        }
        viewPropertyAnimator.start();
    }
    private static int getDP(Context context, int dps){
        final float scale = Objects.requireNonNull(context).getResources().getDisplayMetrics().density;
        return  (int) (dps * scale + 0.5f);
    }







    // Limpia strings para su comparación

    public static String cleanString(String string){
        String normalize = Normalizer.normalize(string.toLowerCase(), java.text.Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return normalize.trim();
    }






    // Administra el hide del keyboard para un control o view en especifico

    public static void hideKeyboardFrom( final View view) {
        view.requestFocus();
        view.postDelayed( () -> {
            try {
                InputMethodManager keyboard = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            catch(Exception ignored){
                // Ignored code block
            }
        },200);
    }









    // Administra el hide del keyboard al cambiar de item en tabs

    public static void hideKeyboardFromTab(TabLayout tabLayout){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Utilities.hideKeyboardFrom(tabLayout);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }













}
