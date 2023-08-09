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

class stockCrawler extends AsyncTask<String, Void, Void> {
    // create set that will store links
    private HashSet<String> urlLink;

    // initialize set using constructor
    public stockCrawler() {
        urlLink = new HashSet<String>();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("WrongThread")
    @Override
    protected Void doInBackground(String... URL) {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference db = firebaseDatabase.getReference("stocks");
        //LinkedList<Film> film = null;
        Map<String, stock> xyz = new HashMap<>();
        if (!urlLink.contains(URL[0])) {
            try {
                if (urlLink.add(URL[0])) {
                }
                Document doc = Jsoup.connect(URL[0]).get();
                Elements elementsContainer = doc.getElementsByTag("tr");
                for (int i = 0; i < 20; i++) {
                    Element element = elementsContainer.get(i+1);
                    String name = element.getElementsByClass("clrText fw500 st76SymbolName").text();
                    String mktPrice = element.getElementsByClass("fw500 st76CurrVal").text().substring(1);
                    String clsPrice = element.getElementsByClass("fs14 clrText st76Pad16").first().text().substring(1);
                    String mktCap = element.getElementsByClass("fs14 clrText st76Pad16").get(1).text().substring(1);
                    String chng = element.getElementsByClass("st76PercentChange").text();
                    stock stk = new stock(name,mktPrice,clsPrice,mktCap,chng);
                    xyz.put(name, stk);
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