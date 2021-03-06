package de.tap.easy_xkcd.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.tap.xkcd_reader.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Calendar;

import de.tap.easy_xkcd.utils.Comic;
import de.tap.easy_xkcd.utils.PrefHelper;

public class ComicNotifier extends WakefulIntentService {

    public ComicNotifier() {
        super("NewComicNotifier");
    }

    @Override
    public void doWakefulWork(Intent intent) {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        PrefHelper.getPrefs(getApplicationContext());
        if (!PrefHelper.checkUpdated(day)) {
            if (day == Calendar.TUESDAY) {
                new updateWhatIfTitles().execute();
                Log.e("Info", "What if task executed");
            } else {
                new updateComicTitles().execute();
                Log.e("Info", "Comic task executed");
            }
        } else {
           Log.e("Info", "notification already sent or wrong day");
        }
    }

    private class updateWhatIfTitles extends AsyncTask<Void, Void, Void> {
        private boolean found = false;
        private String title;
        private int number;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("https://what-if.xkcd.com/archive/")
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.19 Safari/537.36")
                        .get();
                Elements titles = doc.select("h1");
                if (titles.size()>PrefHelper.getNewestWhatIf()) {
                    found = true;
                    title = titles.first().text();
                    number = titles.size();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void dummy) {
            if (found) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.ic_notification)
                                .setContentTitle(getResources().getString(R.string.new_whatif))
                                .setContentText(title)
                                .setAutoCancel(true);

                Intent intent = new Intent("de.tap.easy_xkcd.ACTION_WHAT_IF");
                intent.putExtra("number", number);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, mBuilder.build());
            }
            PrefHelper.setUpdated(Calendar.TUESDAY, found);
        }

    }

    private class updateComicTitles extends AsyncTask<Void, Void, Void> {
        private boolean found = false;
        private Comic comic;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                comic = new Comic(0);
                if (comic.getComicNumber() > PrefHelper.getNewest()) {
                    found = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void dummy) {
            if (found) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.ic_notification)
                                .setContentTitle(getResources().getString(R.string.new_comic))
                                .setContentText(String.valueOf(comic.getComicNumber()) + ": " + comic.getComicData()[0])
                                .setAutoCancel(true);

                Intent intent = new Intent("de.tap.easy_xkcd.ACTION_COMIC");
                intent.putExtra("number", comic.getComicNumber());
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, mBuilder.build());
            }
            PrefHelper.setUpdated(Calendar.getInstance().get(Calendar.DAY_OF_WEEK), found);
        }

    }

}
