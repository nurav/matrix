package com.spit.matrix;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

/**
 * Created by Mustafa on 14/09/2014.
 */
public class CategoryAdapter extends ArrayAdapter<String> {


    private final int[] COLOR_RES = { R.color.material_red_400, R.color.material_deep_purple_400,
            R.color.material_indigo_400,R.color.material_teal_400,R.color.material_green_400,
            R.color.material_orange_400,R.color.material_brown_400};

    public CategoryAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.category_view, parent, false);
        ImageView img = (ImageView)convertView.findViewById(R.id.category_img);
        img.setImageResource(COLOR_RES[position]);
        TextView name = (TextView)convertView.findViewById(R.id.category_text);
        name.setText(getItem(position));


        return convertView;
    }
}
