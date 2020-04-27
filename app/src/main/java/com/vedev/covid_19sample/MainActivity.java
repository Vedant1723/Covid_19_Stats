package com.vedev.covid_19sample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    Button button;
    public static TextView countryName, totalCases, activeCases, totalDeath, todayDeath, recovered, todayCases;
    //String cName, tcases, acases, tdeaths, todayDeaths, recoveredCases, todayCas;
    String country;
    EditText editText;
    Button refreshBtn;
    Button india, china, italy, spain, usa;
    String imgURi;
    CircleImageView flagDp;
    TextView wCases, wDeaths, wTodayCases, wTodayDeaths,recoveredWorldTotal;
    String worldCases, worldDeaths, worldTodayCases, worldTodayDeaths,recoveredWorld;
    RecyclerView countryRecyclerView;
    List<Country> countryList;
    CountryAdapter countryAdapter;

    private RequestQueue requestQueue;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        activeCases = (TextView) findViewById(R.id.activeCases);
        totalDeath = (TextView) findViewById(R.id.totalDeath);
        todayDeath = (TextView) findViewById(R.id.todayDeaths);
        recovered = (TextView) findViewById(R.id.recovered);
        todayCases = (TextView) findViewById(R.id.todayCases);
        refreshBtn = (Button) findViewById(R.id.refreshBtn);

        flagDp = (CircleImageView) findViewById(R.id.flagDp);
        wCases = (TextView) findViewById(R.id.worldWideCases);
        wDeaths = (TextView) findViewById(R.id.worldWideDeaths);
        wTodayCases = (TextView) findViewById(R.id.worldTodayCases);
        wTodayDeaths = (TextView) findViewById(R.id.worldTodayDeaths);
        recoveredWorldTotal=(TextView) findViewById(R.id.recoveredWorldTotal);

        countryRecyclerView = (RecyclerView) findViewById(R.id.countryRecyclerView);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //  button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);

        requestQueue = Volley.newRequestQueue(this);

        countryList = new ArrayList<>();

        jsonParseWorldWide();
        jsonParse();


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                jsonParse();
//            }
//        });
//
//
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countryList.clear();

        jsonParseWorldWide();
        jsonParse();
                Toast.makeText(MainActivity.this, "Data Refreshed!", Toast.LENGTH_SHORT).show();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

    }

    private void filter(String toString) {
        List<Country> cList = new ArrayList<>();
        for (Country country:countryList){
            if (country.getCountryName().contains(toString)|| country.getCountryName().toLowerCase().contains(toString.toLowerCase())||
            country.getCountryName().toUpperCase().contains(toString.toUpperCase())){
                cList.add(country);
            }
        }
        countryAdapter.filterList(cList);
    }

    private void jsonParseWorldWide() {

        String url = "https://corona.lmao.ninja/v2/all";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    worldCases = response.getString("cases");
                    worldDeaths = response.getString("deaths");
                    worldTodayCases = response.getString("todayCases");
                    worldTodayDeaths = response.getString("todayDeaths");
                    recoveredWorld=response.getString("recovered");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int number=Integer.parseInt(worldCases);
                worldCases=String.format("%,d",number);
                wCases.setText(worldCases);
                number=Integer.parseInt(worldDeaths);
                worldDeaths=String.format("%,d",number);
                wDeaths.setText(worldDeaths);
                number=Integer.parseInt(worldTodayCases);
                worldTodayCases=String.format("%,d",number);
                wTodayCases.setText(worldTodayCases);
                number=Integer.parseInt(worldTodayDeaths);
                worldTodayDeaths=String.format("%,d",number);
                wTodayDeaths.setText(worldTodayDeaths);
                number=Integer.parseInt(recoveredWorld);
                recoveredWorld=String.format("%,d",number);
                recoveredWorldTotal.setText(recoveredWorld);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);


    }


    private void jsonParse() {
        String url = "https://corona.lmao.ninja/v2/countries?sort=country";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String cNm = jsonObject.getString("country");
                        String tCas = jsonObject.getString("cases");
                        String todayCas = jsonObject.getString("todayCases");
                        String tDeaths = jsonObject.getString("deaths");
                        String todayDeath = jsonObject.getString("todayDeaths");
                        String recovered = jsonObject.getString("recovered");
                        String active = jsonObject.getString("active");
                        JSONObject imgObj = jsonObject.getJSONObject("countryInfo");
                        String img = imgObj.getString("flag");
                        countryList.add(new Country(cNm, img, tCas, tDeaths, todayCas, todayDeath, active, recovered));
                        countryAdapter = new CountryAdapter(countryList, MainActivity.this);
                        // countryAdapter.notifyDataSetChanged();
                        countryRecyclerView.setAdapter(countryAdapter);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(jsonArrayRequest);
    }
}
