package com.example.moviedetail;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    ImageView coverImg1,coverImg2;
    String url;
    String img_url1;
    String img_url2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        coverImg1 = findViewById(R.id.coverImg1);
        coverImg2 = findViewById(R.id.coverImg2);


        Bundle intent = getIntent().getExtras();
        assert intent != null;
        url = intent.getString("URL");

        Detail detail = new Detail();
        detail.execute();
    }

    public class Detail extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Picasso.with(getApplicationContext()).load(img_url1).fit().into(coverImg1);
            Picasso.with(getApplicationContext()).load(img_url2).fit().into(coverImg2);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect(url).get();
                img_url1 = doc.select("div.poster").select("img").attr("src");
                img_url2 = doc.select("div.slate").select("img").attr("src");
                System.out.println(img_url1);
                System.out.println(img_url2);

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
