package com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {
    public CityArrayAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = (convertView == null)
                ? LayoutInflater.from(getContext()).inflate(R.layout.content, parent, false)
                : convertView;

        City city = getItem(position);
        TextView cityText = view.findViewById(R.id.city_text);
        TextView provinceText = view.findViewById(R.id.province_text);
        if (city != null) {
            cityText.setText(city.getName());
            provinceText.setText(city.getProvince());
        }
        return view;
    }
}
