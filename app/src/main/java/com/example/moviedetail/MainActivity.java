package com.example.moviedetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ArrayList<MovieData> mData;
    ArrayList<String> title, rating, img_url, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData = new ArrayList<>();
        title = new ArrayList<>();
        url = new ArrayList<>();
        img_url = new ArrayList<>();
        rating = new ArrayList<>();


        recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerAdapter(this, mData);
        recyclerView.setAdapter(adapter);
        Content content = new Content();
        content.execute();
        getData();
    }

    private void getData() {

    }


    public class Content extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (int i = 0; i < title.size(); i++) {
                mData.add(new MovieData(title.get(i), img_url.get(i), rating.get(i), url.get(i)));
                System.out.println(title.get(i));
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document document = Jsoup.connect("https://www.imdb.com/chart/top/?ref_=nv_mv_250").get();
                Elements elements = document.select("td.titleColumn");
                Elements elements1 = document.select("td.posterColumn");
                Elements elements2 = document.select("td.ratingColumn.imdbRating");

                for (int i = 0; i < elements.size(); i++) {
                    title.add(elements.select("a").eq(i).text() + elements.select("span").eq(i).text());
                    url.add("https://www.imdb.com/" + elements.select("a").eq(i).attr("href"));
                }

                for (int i = 0; i < elements1.size(); i++) {
                    img_url.add(elements1.select("img").eq(i).attr("src"));
                }

                for (int i = 0; i < elements2.size(); i++) {
                    rating.add(elements2.select("strong").eq(i).text());
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("FINISHED");
            return null;
        }

    }
}
