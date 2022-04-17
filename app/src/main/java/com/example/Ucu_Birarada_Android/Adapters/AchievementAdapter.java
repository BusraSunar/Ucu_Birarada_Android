package com.example.Ucu_Birarada_Android.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.Ucu_Birarada_Android.Models.AchievementModel;
import com.example.Ucu_Birarada_Android.R;

import java.util.ArrayList;

public class AchievementAdapter extends ArrayAdapter<AchievementModel> {


    private class ViewHolder {
        private TextView toDo;
        private ImageView icon;
    }

    private ArrayList<AchievementModel> array;
    private Context context;

    public AchievementAdapter(Context context, ArrayList<AchievementModel> arrayList) {
        super(context, R.layout.achievements_row, arrayList);
        this.array = arrayList;
        this.context = context;

    }


    public int getCount() {
        return array.size();
    }

    public AchievementModel getItem(int position) {
        return array.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        AchievementAdapter.ViewHolder holder = new AchievementAdapter.ViewHolder();
        view = LayoutInflater.from(context).inflate(R.layout.achievements_row, null);

        if (view != null) {
            holder.toDo = view.findViewById(R.id.AchievementsTextRowID);
            holder.icon = view.findViewById(R.id.AchievementICONID);
        }

        holder.toDo.setText(array.get(position).getDescription());
        if(position == 0)
            if(array.get(position).getGoal().equalsIgnoreCase("true"))
                holder.icon.setBackgroundResource(R.drawable.star);
            else
                holder.icon.setBackgroundResource(R.drawable.starcross);
        else if(position == 1)
            if(array.get(position).getGoal().equalsIgnoreCase("true"))
                holder.icon.setBackgroundResource(R.drawable.callender);
            else
                holder.icon.setBackgroundResource(R.drawable.callendercross);

        else if(position == 2)
            if(array.get(position).getGoal().equalsIgnoreCase("true"))
                holder.icon.setBackgroundResource(R.drawable.cake);
            else
                holder.icon.setBackgroundResource(R.drawable.cakecross);
        else if(position == 3)
            if(array.get(position).getGoal().equalsIgnoreCase("true"))
                holder.icon.setBackgroundResource(R.drawable.chatpng);
            else
                holder.icon.setBackgroundResource(R.drawable.chatcross);

        else if(position == 4)
            if(array.get(position).getGoal().equalsIgnoreCase("true"))
                holder.icon.setBackgroundResource(R.drawable.moonach);
            else
                holder.icon.setBackgroundResource(R.drawable.mooncross);
        else if(position == 5)
            if(array.get(position).getGoal().equalsIgnoreCase("true"))
                holder.icon.setBackgroundResource(R.drawable.tick);
            else
                holder.icon.setBackgroundResource(R.drawable.tickcross);
        else
        {
            holder.toDo.setVisibility(View.INVISIBLE);
            holder.icon.setVisibility(View.INVISIBLE);
        }




        return view;
    }

}

