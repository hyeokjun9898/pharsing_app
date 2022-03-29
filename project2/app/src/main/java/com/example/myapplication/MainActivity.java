package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String htmlPageUrl = "https://busan.happydorm.or.kr/60/6060.kmc";  //주소입력시 https://붙여야함.
    private TextView textviewHtmlDocument;
    private String htmlContentInStringFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewHtmlDocument = (TextView) findViewById(R.id.textView);
        textviewHtmlDocument.setMovementMethod(new ScrollingMovementMethod());
        //스크롤 되는 텍스트뷰

        Button htmlTitleButton = (Button) findViewById(R.id.btn1);
        htmlTitleButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                jsoupAsyncTask.execute();
            }
        });
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Document doc = Jsoup.connect(htmlPageUrl).get();
                //Elements links = doc.select("tr[class=under_lc] td");
                Elements links = doc.select(".under_l");

                for (Element link : links) {
                    htmlContentInStringFormat += (link + "\n");
                    //htmlContentInStringFormat += (link.attr("abs:href") + "(" + link.text().trim() + ")\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            textviewHtmlDocument.setText(htmlContentInStringFormat);
        }

    }


}