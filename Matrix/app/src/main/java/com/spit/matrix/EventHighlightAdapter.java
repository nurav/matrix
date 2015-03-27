package com.spit.matrix;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spit.matrix.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Mustafa on 14/09/2014.
 */
public class EventHighlightAdapter extends ArrayAdapter<EventItem> {

    public EventHighlightAdapter(Context context, int resource, List<EventItem> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.event_highlight, parent, false);
        ImageView img = (ImageView)convertView.findViewById(R.id.highlight_img);
        try {
            // get input stream
            InputStream ims = getContext().getAssets().open("event_img/" + getItem(position).getId()+ ".jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            img.setImageDrawable(d);
        }
        catch (IOException ex)
        {}
        TextView name =  (TextView)convertView.findViewById(R.id.highlight_text);
        name.setText(getItem(position).getName());

        return convertView;

    }

}
