package com.example.imad;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class newsCrawler extends AsyncTask<String, Void, Void> {
    // create set that will store links
    private HashSet<String> urlLink;

    // initialize set using constructor
    public newsCrawler() {
        urlLink = new HashSet<String>();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("WrongThread")
    @Override
    protected Void doInBackground(String... URL) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference db = firebaseDatabase.getReference("news");
        Map<String, String> xyz1 = new HashMap<>();
        if (!urlLink.contains(URL[0])) {
            try {
                if (urlLink.add(URL[0])) {
                }
                String q = URL[1];
                String url = URL[0] + q + "&hl=en-IN&gl=IN&ceid=IN%3Aen";
                Document page = Jsoup.connect(url).get();
                Elements availableLinksOnPage = page.getElementsByClass("VDXfz");
                for (int i = 0; i < 5; i++) {
                    String link = availableLinksOnPage.get(i).attr("abs:href");
                    Document pg = Jsoup.connect(link).get();
                    Elements next_links = pg.select("a[href]");
                    Element tx = null;
                    for (Element ele : next_links) {
                        if (ele.text().contains(q)) {
                            tx = ele;
                            break;
                        }
                    }
                    pg = Jsoup.connect(tx.text()).get();
                    next_links = pg.getElementsByTag("p");
                    String xyz = "";
                    int w = 0;
                    for (Element ele : next_links) {
                        xyz = xyz + ele.text();
                    }
                    xyz1.put(q+String.valueOf(i),xyz);
                    db.setValue(xyz1);
                }
            }
                // handle exception
            catch(IOException e){
                    // print exception messages
                    System.err.println("For '" + URL + "': " + e.getMessage());
                }

            }
            return null;
        }
    }
