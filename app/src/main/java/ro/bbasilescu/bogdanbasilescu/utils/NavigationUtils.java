package ro.bbasilescu.bogdanbasilescu.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class NavigationUtils {

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment,
                                             String tag, int idContainer) {
        fragmentManager.beginTransaction()
                .add(idContainer, fragment, tag)
                .commit();
    }

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment,
                                             String tag, int idContainer, int fragmentTransition) {
        fragmentManager.beginTransaction()
                .add(idContainer, fragment, tag)
                .setTransition(fragmentTransition)
                .commit();
    }

    public static void replaceFragmentWith(FragmentManager fragmentManager, Fragment fragment, String tag, int idContainer) {
        fragmentManager.beginTransaction()
                .replace(idContainer, fragment)
                .addToBackStack(tag)
                .commit();
    }

    public static void replaceFragmentWith(FragmentManager fragmentManager, Fragment fragment,
                                           String tag, int idContainer, int fragmentTransition) {
        fragmentManager.beginTransaction()
                .replace(idContainer, fragment)
                .setTransition(fragmentTransition)
                .addToBackStack(tag)
                .commit();
    }

    public static void popFromStackFragment(FragmentManager fragmentManager, String tag) {
        fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
