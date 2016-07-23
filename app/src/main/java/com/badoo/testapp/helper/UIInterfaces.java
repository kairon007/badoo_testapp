package com.badoo.testapp.helper;

import android.support.v4.app.Fragment;

/**
 * Common interfaces for Activity and fragment interactions without creating a close coupling
 * between the pair
 */
public class UIInterfaces {

    /**
     * Implemented by activities that can handle fragment change requests from its fragments
     */
    public interface FragmentChanger {
        /**
         * Change the content fragment of the activity
         *
         * @param fragment
         *      New fragment
         */
        public void changeFragment(Fragment fragment);
    }
}
