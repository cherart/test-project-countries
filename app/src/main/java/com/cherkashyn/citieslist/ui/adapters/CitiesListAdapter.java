package com.cherkashyn.citieslist.ui.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cherkashyn.citieslist.R;
import com.cherkashyn.citieslist.utils.OnCityClickListener;

import java.util.List;

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.ViewHolder> {

    private List<String> citiesList;

    private OnCityClickListener onCityClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(citiesList.get(i), onCityClickListener);
    }

    @Override
    public int getItemCount() {
        if (citiesList != null) {
            return citiesList.size();
        }
        return 0;
    }

    public void setCitiesList(List<String> citiesList) {
        this.citiesList = citiesList;
        notifyDataSetChanged();
    }

    public void setOnCityClickListener(OnCityClickListener onCityClickListener) {
        this.onCityClickListener = onCityClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }

        public void bind(final String cityName, final OnCityClickListener onCityClickListener) {
            textView.setText(cityName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCityClickListener.onCityClick(cityName);
                }
            });
        }
    }
}
