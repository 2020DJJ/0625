package com.example.sook;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Recipe2 extends AppCompatActivity {

    private ArrayAdapter arrayAdapter1; private ArrayAdapter arrayAdapter2;

    EditText edit;
    TextView text;

    XmlPullParser xpp;
    String key = "20200609CU4ACGJZDFQB3TQNBAV4Q";
    String data; String selectYear; String selectMonth;

    Spinner spinner1; Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe2);

        edit = (EditText) findViewById(R.id.edit);
        text = (TextView) findViewById(R.id.result);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter arrayAdapter1 = ArrayAdapter.createFromResource(this, R.array.recspinner1, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter arrayAdapter2 = ArrayAdapter.createFromResource(this, R.array.recspinner2, android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);
        spinner2.setAdapter(arrayAdapter2);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectYear = spinner1.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectMonth = spinner2.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.button:

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        data = getXmlData();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                text.setText(data);
                            }
                        });
                    }
                }).start();
                break;
        }
    }

    String getXmlData() {
        StringBuffer buffer = new StringBuffer();
        String str = edit.getText().toString();
        String queryUrl = "http://api.nongsaro.go.kr/service/monthFd/monthNewFdLst" + "?apiKey=" + key + "&thisYear=" + selectYear + "&thisMonth=" + selectMonth;
        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8"));

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작\n\n");
                        break;
                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();

                        if (tag.equals("item")) ;
                        else if (tag.equals("fdNm")) {
                            buffer.append("레시피: ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if (tag.equals("rtnStreFileNm")) {
                            buffer.append(": ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        break;
                }
                    eventType = xpp.next();
                }

            }catch(Exception e){
                // TODO Auto-generated catch blocke.printStackTrace();
            }
            buffer.append("파싱 끝\n");
            return buffer.toString();

        }

    }
