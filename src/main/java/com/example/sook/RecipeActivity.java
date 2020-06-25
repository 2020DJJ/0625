package com.example.sook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.net.URLEncoder;

public class RecipeActivity extends AppCompatActivity {

    private Spinner spinner1; private Spinner spinner2;
    private ArrayAdapter arrayAdapter1; private ArrayAdapter arrayAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        StrictMode.enableDefaults();
        TextView status1 = (TextView) findViewById(R.id.result1);
        TextView status2 = (TextView) findViewById(R.id.result2);
        Button viewrecipebutton = (Button) findViewById(R.id.viewrecipebutton);
        viewrecipebutton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }) ;

        boolean incntntsNo = false;
        boolean infdmtNm = false;
        boolean inrtnImgSeCode = false;
        String fdmtNm = null;
        String rtnImgSeCode = null;

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        arrayAdapter1 = ArrayAdapter.createFromResource(this, R.array.recspinner1, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter2 = ArrayAdapter.createFromResource(this, R.array.recspinner2, android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);
        spinner2.setAdapter(arrayAdapter2);
        String selectYear = spinner1.getSelectedItem().toString();
        String selectMonth = spinner2.getSelectedItem().toString();


        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/monthFd/monthFdmtLst"); //URL
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20200609CU4ACGJZDFQB3TQNBAV4Q"); //apiKey
            urlBuilder.append("&" + URLEncoder.encode("thisYear", "UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("thisMonth", "UTF-8") + "=" + URLEncoder.encode("01", "UTF-8"));
            URL url = new URL(urlBuilder.toString());
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("start parsing.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:

                        if (parser.getName().equals("cntntsNo")) {
                            incntntsNo = true;
                        }
                        if (parser.getName().equals("fdmtNm")) {
                            infdmtNm = true;
                        }
                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText() + "에러");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (incntntsNo) {
                            incntntsNo = false;
                        }
                        if (infdmtNm) {
                            fdmtNm = parser.getText();
                            infdmtNm = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            status1.setText(status1.getText() + selectYear + "년" + selectMonth + "월의 제철 식재료는" + fdmtNm + " 입니다.\n");
                            incntntsNo = false;
                        }
                        break;
                }
                parserEvent = parser.next();

            }


        } catch (Exception e) {
            status1.setText("ERROR");
        }


        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/monthFd/monthNewFdLst"); //URL
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20200609CU4ACGJZDFQB3TQNBAV4Q"); //apiKey
            urlBuilder.append("&" + URLEncoder.encode("thisYear", "UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("thisMonth", "UTF-8") + "=" + URLEncoder.encode("01", "UTF-8"));
            URL url2 = new URL(urlBuilder.toString());
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url2.openStream(), null);

            int parserEvent2 = parser.getEventType();
            System.out.println("start parsing.");

            while (parserEvent2 != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent2) {
                    case XmlPullParser.START_TAG:

                        if (parser.getName().equals("cntntsNo")) {
                            incntntsNo = true;
                        }

                        if (parser.getName().equals("rtnImgSeCode")) {
                            inrtnImgSeCode = true;
                        }
                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText() + "에러");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (incntntsNo) {
                            incntntsNo = false;
                        }

                        if (inrtnImgSeCode) {
                            rtnImgSeCode = parser.getText();
                            inrtnImgSeCode = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            status2.setText(status2.getText() + " 파일구분코드: " + rtnImgSeCode + "\n");
                            incntntsNo = false;
                        }
                        break;
                }
                parserEvent2 = parser.next();

            }

        } catch (Exception e) {
            status2.setText("ERROR");
        }

    }
}
