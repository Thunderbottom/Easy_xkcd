/**
 * *******************************************************************************
 * Copyright 2015 Tom Praschan
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ******************************************************************************
 */
package de.tap.easy_xkcd.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.tap.xkcd_reader.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import de.tap.easy_xkcd.Activities.NestedSettingsActivity;
import de.tap.easy_xkcd.misc.ScrollAwareFABBehavior;

public class PrefHelper {
    private static SharedPreferences sharedPrefs;
    private static SharedPreferences prefs;
    private static List<Integer> randList;
    private static int randIndex = 0;

    private static final String FULL_OFFLINE = "pref_offline";
    private static final String COMIC_TITLES = "comic_titles";
    private static final String COMIC_TRANS = "comic_trans";
    private static final String COMIC_URLS = "comic_urls";
    private static final String SUBTITLE_ENABLED = "pref_subtitle";
    private static final String HIGHEST_COMIC_URL = "highest_comic_url";
    private static final String OFFLINE_TITLE = "title";
    private static final String OFFLINE_ALT = "alt";
    private static final String OFFLINE_HIGHEST = "highest_offline";
    private static final String NEWEST_COMIC = "Newest Comic";
    private static final String LAST_COMIC = "Last Comic";
    private static final String ALT_VIBRATION = "pref_alt";
    private static final String ORIENTATION = "pref_orientation";
    private static final String ALT_TIP = "alt_tip";
    private static final String SHARE_IMAGE = "pref_share";
    private static final String SHARE_MOBILE = "pref_mobile";
    private static final String NOTIFICATIONS_INTERVAL = "pref_notifications";
    private static final String MONDAY_UPDATE = "monday_update";
    private static final String WEDNESDAY_UPDATE = "wednesday_update";
    private static final String FRIDAY_UPDATE = "friday_update";
    private static final String TUESDAY_UPDATE = "tuesday_update";
    private static final String ALT_DEFAULT = "pref_show_alt";
    private static final String LAST_WHATIF = "last_whatif";
    private static final String NIGHT_MODE = "night_mode";
    private static final String WHATIF_READ = "whatif_read";
    private static final String WHATIF_FAV = "whatif_fav";
    private static final String NEWEST_WHATIF = "whatif_newest";
    private static final String HIDE_READ = "hide_read";
    private static final String SWIPE_ENABLED = "whatif_swipe";
    private static final String RATE_SNACKBAR = "rate_snackbar";
    private static final String THEME = "pref_theme";
    private static final String WHATIF_OFFLINE = "pref_offline_whatif";
    private static final String WHATIF_TITLES = "whatif_titles";
    private static final String NOMEDIA_CREATED = "nomedia_created";
    private static final String SHARE_ALT = "pref_share_alt";
    private static final String PREF_ZOOM = "pref_zoom";
    private static final String PREF_DONATE = "pref_hide_donate";
    private static final String DATABSE_LOADED = "database_loaded";
    private static final String ALT_STYLE = "pref_alt_style";
    private static final String ALT_OPTIONS = "pref_alt_options";
    private static final String ALT_ACTIVATION = "pref_alt_activation";
    private static final String SURVEY_SNACKBAR = "survey_snackbar";
    private static final String NEW_FEATURE_SNACKBAR = "new_feature_snackbar";
    private static final String NIGHT_THEME = "pref_night";
    private static final String INVERT_COLORS = "pref_invert";
    private static final String COLORED_NAVBAR = "pref_navbar";
    private static final String MOBILE_ENABLED = "pref_update_mobile";
    private static final String AUTO_NIGHT = "pref_auto_night";
    private static final String AUTO_NIGHT_START_MIN = "pref_auto_night_start_min";
    private static final String AUTO_NIGHT_START_HOUR = "pref_auto_night_start_hour";
    private static final String AUTO_NIGHT_END_MIN = "pref_auto_night_end_min";
    private static final String AUTO_NIGHT_END_HOUR = "pref_auto_night_end_hour";
    private static final String OFFLINE_PATH = "pref_offline_path";
    private static final String ZOOM_SCROLL = "pref_zoom_scroll";
    private static final String OVERVIEW_FAV= "overview_fav";


    public static void getPrefs(Context context) {
        sharedPrefs = context.getSharedPreferences("MainActivity", Activity.MODE_PRIVATE);
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean fullOfflineEnabled() {
        return prefs.getBoolean(FULL_OFFLINE, false);
    }

    public static void setFullOffline(boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(FULL_OFFLINE, value);
        editor.commit();
    }

    public static String getComicTitles() {
        return sharedPrefs.getString(COMIC_TITLES, "");
    }

    public static String getComicTrans() {
        return sharedPrefs.getString(COMIC_TRANS, "");
    }

    public static String getComicUrls() {
        return sharedPrefs.getString(COMIC_URLS, "");
    }

    public static boolean fabEnabled(String prefTag) {
        return prefs.getStringSet("pref_random", new HashSet<String>()).contains(prefTag);
    }

    public static boolean subtitleEnabled() {
        return prefs.getBoolean(SUBTITLE_ENABLED, true);
    }

    public static boolean classicAltStyle() {
        return Integer.parseInt(prefs.getString(ALT_STYLE, "0")) == 0;
    }

    public static boolean databaseLoaded() {
        return sharedPrefs.getBoolean(DATABSE_LOADED, false);
    }

    public static void setDatabaseLoaded() {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(DATABSE_LOADED, true);
        editor.apply();
    }

    public static void setTitles(String titles) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(COMIC_TITLES, titles);
        editor.commit();
    }

    public static void setUrls(String urls, int highest) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(COMIC_URLS, urls);
        editor.putInt(HIGHEST_COMIC_URL, highest);
        editor.commit();
    }

    public static void setTrans(String trans) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(COMIC_TRANS, trans);
        editor.commit();
    }

    public static int getHighestUrls() {
        return sharedPrefs.getInt(HIGHEST_COMIC_URL, 0);
    }

    public static void addTitle(String title, int i) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(OFFLINE_TITLE + String.valueOf(i), title);
        editor.commit();
    }

    public static String getTitle(int number) {
        return sharedPrefs.getString(OFFLINE_TITLE + String.valueOf(number), "");
    }

    public static void addAlt(String alt, int i) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(OFFLINE_ALT + String.valueOf(i), alt);
        editor.commit();
    }

    public static String getAlt(int number) {
        return sharedPrefs.getString(OFFLINE_ALT + String.valueOf(number), "");
    }

    public static void setHighestOffline(int number) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(OFFLINE_HIGHEST, number);
        editor.commit();
    }

    public static int getHighestOffline() {
        return sharedPrefs.getInt(OFFLINE_HIGHEST, 0);
    }

    public static int getNewest() {
        return sharedPrefs.getInt(NEWEST_COMIC, 0);
    }

    public static void deleteTitleAndAlt(int newest, Activity activity) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        for (int i = 1; i <= newest; i++) {
            if (!Favorites.checkFavorite(activity, i)) {
                editor.putString(OFFLINE_TITLE, "");
                editor.putString(OFFLINE_ALT, "");
            }
        }
        editor.apply();
    }

    public static void setLastComic(int number) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(LAST_COMIC, number);
        editor.commit();
    }

    public static int getLastComic() {
        return sharedPrefs.getInt(LAST_COMIC, 0);
    }

    public static void setNewestComic(int number) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(NEWEST_COMIC, number);
        editor.commit();
    }

    public static boolean altVibration() {
        return prefs.getStringSet(ALT_OPTIONS, new HashSet<String>()).contains(ALT_VIBRATION);
    }

    public static String getOrientation() {
        return prefs.getString(ORIENTATION, "1");
    }

    public static boolean showAltTip() {
        return sharedPrefs.getBoolean(ALT_TIP, true);
    }

    public static void setAltTip(boolean value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(ALT_TIP, value);
        editor.apply();
    }

    public static boolean shareImage() {
        return prefs.getBoolean(SHARE_IMAGE, false);
    }

    public static boolean shareMobile() {
        return prefs.getBoolean(SHARE_MOBILE, false);
    }

    public static int getNotificationInterval() {
        String hours = prefs.getString(NOTIFICATIONS_INTERVAL, "0");
        return Integer.parseInt(hours) * 60 * 60 * 1000;
    }

    public static boolean checkUpdated(int day) {
        switch (day) {
            case Calendar.MONDAY:
                return sharedPrefs.getBoolean(MONDAY_UPDATE, false);
            case Calendar.WEDNESDAY:
                return sharedPrefs.getBoolean(WEDNESDAY_UPDATE, false);
            case Calendar.FRIDAY:
                return sharedPrefs.getBoolean(FRIDAY_UPDATE, false);

            case Calendar.TUESDAY:
                sharedPrefs.getBoolean(TUESDAY_UPDATE, false);
        }
        return true;
    }

    public static void setUpdated(int day, boolean found) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        switch (day) {
            case Calendar.MONDAY:
                editor.putBoolean(MONDAY_UPDATE, found);
                editor.putBoolean(WEDNESDAY_UPDATE, false);
                editor.putBoolean(FRIDAY_UPDATE, false);
                break;
            case Calendar.WEDNESDAY:
                editor.putBoolean(WEDNESDAY_UPDATE, found);
                editor.putBoolean(FRIDAY_UPDATE, false);
                editor.putBoolean(MONDAY_UPDATE, false);
                editor.putBoolean(TUESDAY_UPDATE, false);
                break;
            case Calendar.FRIDAY:
                editor.putBoolean(FRIDAY_UPDATE, found);
                editor.putBoolean(MONDAY_UPDATE, false);
                editor.putBoolean(WEDNESDAY_UPDATE, false);
                editor.putBoolean(TUESDAY_UPDATE, false);
                break;
            case Calendar.TUESDAY:
                editor.putBoolean(TUESDAY_UPDATE, found);
        }
        editor.apply();
        Log.d("Update Status:", String.valueOf(sharedPrefs.getBoolean(MONDAY_UPDATE, false))
                + String.valueOf(sharedPrefs.getBoolean(TUESDAY_UPDATE, false))
                + String.valueOf(sharedPrefs.getBoolean(WEDNESDAY_UPDATE, false))
                + String.valueOf(sharedPrefs.getBoolean(FRIDAY_UPDATE, false)));
    }

    public static boolean altByDefault() {
        return prefs.getStringSet(ALT_OPTIONS, new HashSet<String>()).contains(ALT_DEFAULT);
    }

    public static int getLastWhatIf() {
        return sharedPrefs.getInt(LAST_WHATIF, 0);
    }

    public static void setLastWhatIf(int number) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(LAST_WHATIF, number);
        editor.apply();
    }

    public static boolean nightModeEnabled() {
        return sharedPrefs.getBoolean(NIGHT_MODE, false);
    }

    public static void setNightMode(boolean value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(NIGHT_MODE, value);
        editor.commit();
    }

    public static void setWhatifRead(String added) {
        String read = sharedPrefs.getString(WHATIF_READ, "");
        if (!read.equals("")) {
            read = read + "," + added;
        } else {
            read = added;
        }
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(WHATIF_READ, read);
        editor.commit();
    }

    public static boolean checkRead(int number) {
        String read = sharedPrefs.getString(WHATIF_READ, "");
        if (read.equals("")) {
            return false;
        }
        String[] readList = Favorites.sortArray(read.split(","));
        int[] readInt = new int[readList.length];
        for (int i = 0; i < readInt.length; i++) {
            readInt[i] = Integer.parseInt(readList[i]);
        }
        int a = Arrays.binarySearch(readInt, number);
        return (a >= 0);
    }

    public static void setWhatIfFavorite (String added) {
        String fav = sharedPrefs.getString(WHATIF_FAV, "");
        if (!fav.equals("")) {
            fav = fav + "," + added;
        } else {
            fav = added;
        }
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(WHATIF_FAV, fav);
        editor.commit();
    }

    public static boolean checkWhatIfFav(int number) {
        String fav = sharedPrefs.getString(WHATIF_FAV, "");
        if (fav.equals("")) {
            return false;
        }
        String[] favList = Favorites.sortArray(fav.split(","));
        int[] favInt = new int[favList.length];
        for (int i = 0; i < favInt.length; i++) {
            favInt[i] = Integer.parseInt(favList[i]);
        }
        int a = Arrays.binarySearch(favInt, number);
        return (a >= 0);
    }

    public static void removeWhatifFav(int number) {
        String[] old = sharedPrefs.getString(WHATIF_FAV, "").split(",");
        old = Favorites.sortArray(old);
        int[] oldInt = new int[old.length];
        for (int i = 0; i < old.length; i++) {
            oldInt[i] = Integer.parseInt(old[i]);
        }
        int a = Arrays.binarySearch(oldInt, number);
        String[] out = new String[old.length - 1];
        Log.d("favorites", sharedPrefs.getString(WHATIF_FAV, ""));
        Log.d("a", String.valueOf(a));
        if (out.length != 0 && a >= 0) {
            System.arraycopy(old, 0, out, 0, a);
            System.arraycopy(old, a + 1, out, a, out.length - a);
            StringBuilder sb = new StringBuilder();
            sb.append(out[0]);
            for (int i = 1; i < out.length; i++) {
                sb.append(",");
                sb.append(out[i]);
            }
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(WHATIF_FAV, sb.toString());
            editor.commit();
        } else {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(WHATIF_FAV, "");
            editor.commit();
        }

    }

    public static void setAllUnread() {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(WHATIF_READ, "");
        editor.commit();
    }

    public static boolean hideRead() {
        return sharedPrefs.getBoolean(HIDE_READ, false);
    }

    public static void setHideRead(boolean value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(HIDE_READ, value);
        editor.apply();
    }

    public static boolean swipeEnabled() {
        return sharedPrefs.getBoolean(SWIPE_ENABLED, false);
    }

    public static void setSwipeEnabled(boolean value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(SWIPE_ENABLED, value);
        editor.apply();
    }

    public static boolean showRateDialog() {
        int n = sharedPrefs.getInt(RATE_SNACKBAR, 0);
        if (n < 31) {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putInt(RATE_SNACKBAR, n + 1);
            editor.apply();
        }
        return (n == 12 | n == 30);
    }

    public static void showRateSnackbar(final String packageName, final Context context, FloatingActionButton mFab) {
        // Thanks to /u/underhound for this great idea! https://www.reddit.com/r/Android/comments/3i6uw0/dev_i_think_ive_mastered_the_art_of_asking_for/
        if (PrefHelper.showRateDialog()) {
            View.OnClickListener oc = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("market://details?id=" + packageName);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    SharedPreferences.Editor editor = sharedPrefs.edit();
                    editor.putInt(RATE_SNACKBAR, 32);
                    editor.apply();
                    try {
                        context.startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
                    }
                }
            };
            Snackbar.make(mFab, R.string.snackbar_rate, Snackbar.LENGTH_LONG)
                    .setAction(R.string.snackbar_rate_action, oc)
                    .show();
        }
    }

    public static int getTheme() {
        if (nightThemeEnabled())
            return R.style.NightTheme;

        int n = Integer.parseInt(prefs.getString(THEME, "1"));
        switch (n) {
            case 1:
                return R.style.DefaultTheme;
            case 2:
                return R.style.RedTheme;
            case 3:
                return R.style.BlueTheme;
            case 4:
                return R.style.BlackTheme;
            case 5:
                return R.style.PurpleTheme;
            case 6:
                return R.style.LimeTheme;
            case 7:
                return R.style.GreenTheme;
            default:
                return R.style.DefaultTheme;
        }
    }

    public static int getDialogTheme() {
        if (nightThemeEnabled())
            return R.style.AlertDialogNight;
        int n = Integer.parseInt(prefs.getString(THEME, "1"));
        switch (n) {
            case 1:
                return R.style.AlertDialog;
            case 2:
                return R.style.AlertDialogRed;
            case 3:
                return R.style.AlertDialogBlue;
            case 4:
                return R.style.AlertDialogBlack;
            case 5:
                return R.style.AlertDialogPurple;
            case 6:
                return R.style.AlertDialogLime;
            case 7:
                return R.style.AlertDialogGreen;
            default:
                return R.style.AlertDialog;
        }
    }

    public static int getNewestWhatIf() {
        return sharedPrefs.getInt(NEWEST_WHATIF, 1);
    }

    public static void setNewestWhatif(int number) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(NEWEST_WHATIF, number);
        editor.apply();
    }

    public static void setFullOfflineWhatIf(boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(WHATIF_OFFLINE, value);
        editor.commit();
    }

    public static boolean fullOfflineWhatIf() {
        return prefs.getBoolean(WHATIF_OFFLINE, false);
    }

    public static void setWhatIfTitles(String titles) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(WHATIF_TITLES, titles);
        editor.commit();
    }

    public static ArrayList<String> getWhatIfTitles() {
        String titles = sharedPrefs.getString(WHATIF_TITLES, "");
        return new ArrayList<>(Arrays.asList(titles.split("&&")));
    }

    public static boolean nomediaCreated() {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        boolean created = sharedPrefs.getBoolean(NOMEDIA_CREATED, false);
        editor.putBoolean(NOMEDIA_CREATED, true);
        editor.apply();
        return created;
    }

    public static boolean shareAlt() {
        return prefs.getStringSet(ALT_OPTIONS, new HashSet<String>()).contains(SHARE_ALT);
    }

    public static int getZoom(int webDefault) {
        int i;
        try {
            i = Integer.parseInt(prefs.getString(PREF_ZOOM, String.valueOf(webDefault)));
        } catch (Exception e) {
            i = webDefault;
        }
        return i;
    }

    public static boolean hideDonate() {
        return prefs.getBoolean(PREF_DONATE, false);
    }

    public static void setHideDonate(boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PREF_DONATE, value);
        editor.apply();
    }

    public static boolean altLongTap() {
        return Integer.parseInt(prefs.getString(ALT_ACTIVATION, "1")) == 1;
    }

    public static void showSurveySnackbar(final Context context, FloatingActionButton fab) {
        int n = sharedPrefs.getInt("survey count", 0);
        if (!sharedPrefs.getBoolean(SURVEY_SNACKBAR, false) && n == 4) {
            View.OnClickListener oc = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("https://docs.google.com/forms/d/1c9hb80NU67CImMKAlOH4p9-F63_duC7qAL30FhJv9Xg/viewform?usp=send_form");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                }
            };
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putBoolean(SURVEY_SNACKBAR, true);
            editor.apply();
            Snackbar.make(fab, R.string.snackbar_survey, Snackbar.LENGTH_LONG)
                    .setAction(R.string.snackbar_survey_oc, oc)
                    .show();
        } else if (n < 4) {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putInt("survey count", n + 1);
            editor.apply();
        }
    }

    public static void showFeatureSnackbar(final Context context, FloatingActionButton fab) {
        if (!sharedPrefs.getBoolean(NEW_FEATURE_SNACKBAR, false)) {
            View.OnClickListener oc = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NestedSettingsActivity.class);
                    intent.putExtra("key", "night");
                    context.startActivity(intent);
                }
            };
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putBoolean(NEW_FEATURE_SNACKBAR, true);
            editor.apply();
            Snackbar.make(fab, R.string.snackbar_feature, Snackbar.LENGTH_LONG)
                    .setAction(R.string.snackbar_feature_oc, oc)
                    .show();
        }
    }

    public static boolean invertColors() {
        return prefs.getBoolean(INVERT_COLORS, true) && nightThemeEnabled();
    }

    public static int getRandomNumber(int current) {
        if (randList == null) {
            randList = new ArrayList<>();
            for (int i = 1; i < getNewest(); i++) {
                if (i != current)
                    randList.add(i);
            }
            Collections.shuffle(randList);
            randList.add(0, current);
        }
        int result;
        if (randIndex == 0) {
            result = randList.get(randIndex + 1);
            randIndex++;
        } else {
            randIndex++;
            result = randList.get(randIndex);
        }
        return result;
    }

    public static int getPreviousRandom(int i) {
        if (randList != null && randIndex > 0) {
            int result;
            if (randIndex == 1) {
                randIndex--;
                result = randList.get(randIndex);
            } else {
                result = randList.get(randIndex - 1);
                randIndex--;
            }
            return result;
        }
        return i;
    }

    public static boolean colorNavbar() {
        return prefs.getBoolean(COLORED_NAVBAR, true);
    }

    public static boolean mobileEnabled() {
        return prefs.getBoolean(MOBILE_ENABLED, true);
    }

    public static boolean autoNightEnabled() {
        return prefs.getBoolean(AUTO_NIGHT, false);
    }

    public static boolean nightThemeEnabled() {
        if (prefs.getBoolean(NIGHT_THEME, false) && autoNightEnabled()) {
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            int minute = Calendar.getInstance().get(Calendar.MINUTE);
            int[] start = getAutoNightStart();
            int[] end = getAutoNightEnd();
            if (hour == start[0]) {
                return minute >= start[1];
            }
            if (hour == end[0]) {
                return minute < end[1];
            }
            if (hour > start[0] && hour > end[0] && end[0] >= start[0]) {
                return false;
            }
            if (hour > start[0]) {
                /*if (end[0] > start[0]) {
                    return hour < end[0];
                } else {
                    return true;
                } */
                return end[0] <= start[0] || hour < end[0];
            } else {
                return hour < end[0];
            }
        } else {
            return prefs.getBoolean(NIGHT_THEME, false);
        }
    }

    public static int[] getAutoNightStart() {
        return new int[]{
                sharedPrefs.getInt(AUTO_NIGHT_START_HOUR, 21),
                sharedPrefs.getInt(AUTO_NIGHT_START_MIN, 0)
        };
    }

    public static int[] getAutoNightEnd() {
        return new int[]{
                sharedPrefs.getInt(AUTO_NIGHT_END_HOUR, 8),
                sharedPrefs.getInt(AUTO_NIGHT_END_MIN, 0)
        };
    }

    public static void setAutoNightStart(int[] time) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(AUTO_NIGHT_START_HOUR, time[0]);
        editor.putInt(AUTO_NIGHT_START_MIN, time[1]);
        editor.apply();
    }

    public static void setAutoNightEnd(int[] time) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(AUTO_NIGHT_END_HOUR, time[0]);
        editor.putInt(AUTO_NIGHT_END_MIN, time[1]);
        editor.apply();
    }

    public static String getStartSummary() {
        int[] start = getAutoNightStart();
        String suffix = "";
        String minute = String.valueOf(start[1]);
        if (start[1]<10) {
            minute = "0"+minute;
        }
        return start[0] + ":" + minute + suffix;
    }

    public static String getEndSummary() {
        int[] end = getAutoNightEnd();
        String suffix = "";
        String minute = String.valueOf(end[1]);
        if (end[1]<10) {
            minute = "0"+minute;
        }
        return end[0] + ":" + minute + suffix;
    }

    public static File getOfflinePath() {
        String path = prefs.getString(OFFLINE_PATH, "default");
        if (path.equals("default"))
            return Environment.getExternalStorageDirectory();
        return new File(path);
    }

    public static void setOfflinePath(String path) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(OFFLINE_PATH, path);
        editor.apply();
    }

    public static boolean isWifi(Context context) {
        if (context == null)
            return true;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static boolean scrollDisabledWhileZoom() {
        return prefs.getBoolean(ZOOM_SCROLL, true);
    }

    public static boolean overviewFav() {
        return sharedPrefs.getBoolean(OVERVIEW_FAV, false);
    }

    public static void setOverviewFav(boolean value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(OVERVIEW_FAV, value);
        editor.apply();
    }

}



























