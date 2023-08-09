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

class jobCrawler extends AsyncTask<String, Void, Void> {
    // create set that will store links
    private HashSet<String> urlLink;

    // initialize set using constructor
    public jobCrawler() {
        urlLink = new HashSet<String>();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("WrongThread")
    @Override
    protected Void doInBackground(String... URL) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference db = firebaseDatabase.getReference("jobs");
        //LinkedList<Film> film = null;
        Map<String, job> xyz = new HashMap<>();
        if (!urlLink.contains(URL[0])) {
            try {
                if (urlLink.add(URL[0])) {
                }
                String job1 = URL[1];
                String temp = "job";
                int z = 0;
                for(int i=0;i<20;) {
                    String url = URL[0]+job1+"&start="+String.valueOf(i);
                    //System.out.println(url);
                    i = i + 10;
                    Document doc = Jsoup.connect(url).userAgent("Chrome").get();
                    Elements elementsContainer = doc.getElementsByClass("slider_container css-g7s71f eu4oa1w0");
                    for(int j=0;j<elementsContainer.size();j++)
                    {
                        Element ele = elementsContainer.get(j);
                        String name = ele.getElementsByTag("span").first().text();
                        String cName = ele.getElementsByClass("companyName").text();
                        String cLoc = ele.getElementsByClass("companyLocation").text();
                        String date = ele.getElementsByClass("date").text();
                        String xyz1 = ele.getElementsByClass("visually-hidden").text();
                        String link = ele.getElementsByTag("a").attr("abs:href");
                        int x = xyz1.length();
                        date = date.substring(x);
                        job d1 = new job(name,cName,cLoc,date,link);
                        xyz.put(temp+String.valueOf(z), d1);
                        z++;
                        db.setValue(xyz);
                        //System.out.println(name+" "+cName+" "+cLoc+" "+date+" "+link);
                    }
                }
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