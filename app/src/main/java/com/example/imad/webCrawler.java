package com.example.imad;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class WebCrawlerExample extends AsyncTask<String, Void, Void> {
    // create set that will store links
    private HashSet<String> urlLink;

    // initialize set using constructor
    public WebCrawlerExample() {
        urlLink = new HashSet<String>();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("WrongThread")
    @Override
    protected Void doInBackground(String... URL) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference db = firebaseDatabase.getReference("movies");
        //LinkedList<Film> film = null;
        Map<String, Film> xyz = new HashMap<>();
        if (!urlLink.contains(URL[0])) {
            try {
                if (urlLink.add(URL[0])) {
                }
                Document doc = Jsoup.connect(URL[0]).get();
                Elements elementsContainer = doc.getElementsByClass("ipc-metadata-list-summary-item ipc-metadata-list-summary-item--click sc-b4bc18a3-0 ldrlyp");
                for (int i = 0; i < 10; i++) {
                    String s = elementsContainer.get(i).getElementsByClass("ipc-metadata-list-summary-item__t").attr("abs:href");
                    Document doc1 = Jsoup.connect(s).get();
                    //Elements elementsContainer = doc1.getElementsByClass("sc-80d4314-1 fbQftq");
                    String title = doc1.getElementsByTag("h1").first().text();
                    String year = doc1.getElementsByClass("sc-8c396aa2-2 itZqyK").first().text();
                    String des = doc1.getElementsByClass("sc-16ede01-2 gXUyNh").first().text();
                    if (des == "")
                        des = "N/A";
                    String img = doc1.getElementsByClass("ipc-image").first().attr("src");
                    String release = doc1.getElementsByClass("sc-5766672e-2 bweBzH").first().text();
                    Film film = new Film(title, year, des, img, release);
                    xyz.put(title, film);
                    db.setValue(xyz);
                }
                //db.setValue(xyz);
            }
            // handle exception
            catch (IOException e) {
                // print exception messages
                System.err.println("For '" + URL + "': " + e.getMessage());
            }

        }
        return null;
    }
}