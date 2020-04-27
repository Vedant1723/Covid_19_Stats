package com.vedev.covid_19sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    private List<Country> countryList;
    Context context;

    public CountryAdapter(List<Country> countryList, Context context) {
        this.countryList = countryList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_list, parent, false);
        return new CountryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Country country=countryList.get(position);
        holder.activeCases.setText(country.getActiveCases());
        holder.todayDeaths.setText(country.getTodayDeaths());
        holder.todayCases.setText(country.getTodayCases());
        holder.totalDeaths.setText(country.getTotalDeaths());
        holder.totalCases.setText(country.getTotalCases());
        holder.recovered.setText(country.getRecovered());
        holder.countryName.setText(country.getCountryName());

        Glide.with(context).load(country.getCountryFlag())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.countryFlag);

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void filterList(List<Country> cList) {

        countryList=cList;
         notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView countryFlag;
        TextView countryName,totalCases,totalDeaths,todayCases,todayDeaths,activeCases,recovered;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            countryFlag=(CircleImageView)itemView.findViewById(R.id.flagDp);
            countryName=(TextView)itemView.findViewById(R.id.countryName);
            totalCases=(TextView) itemView.findViewById(R.id.tcas);
            totalDeaths=(TextView) itemView.findViewById(R.id.textView6);
            todayCases=(TextView) itemView.findViewById(R.id.textView9);
            todayDeaths=(TextView) itemView.findViewById(R.id.textView10);
            todayDeaths=(TextView) itemView.findViewById(R.id.textView10);
            activeCases=(TextView)itemView.findViewById(R.id.textView11);
            recovered=(TextView)itemView.findViewById(R.id.textView12);

        }
    }
}
