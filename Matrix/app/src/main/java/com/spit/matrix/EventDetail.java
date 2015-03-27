package com.spit.matrix;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.spit.matrix.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class EventDetail extends ActionBarActivity{

    private ShareActionProvider mShareActionProvider;
    private EventItem eventItem;
    private ImageView eventImage;
    private TextView name;
    private TextView date;
    private TextView time;
    private TextView venue ;
    private TextView desc;
    private ImageView remind;
   // private MenuItem overflow;
    //PopupMenu overflowMenu;
    Button remindMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eventImage = (ImageView)findViewById(R.id.event_detail_img);
        name = (TextView)findViewById(R.id.event_detail_name);
        time = (TextView)findViewById(R.id.event_detail_time);
        venue = (TextView)findViewById(R.id.event_detail_venue);
        desc = (TextView)findViewById(R.id.event_detail_desc);
        remind = (ImageView)findViewById(R.id.img_remind);
        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar srt = Calendar.getInstance();
                srt.set(2014,Calendar.SEPTEMBER,26,8,0,0);

                Calendar end = Calendar.getInstance();
                end.set(2014,Calendar.SEPTEMBER,27,0,0,0);

                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", srt.getTimeInMillis());
                intent.putExtra("allDay", false);
                intent.putExtra("rrule", "FREQ=DAILY;COUNT=2");
                intent.putExtra("endTime", end.getTimeInMillis());
                intent.putExtra("title", name.getText().toString());
                EventDetail.this.startActivity(intent);

            }
        });
       // remindMe = (Button) findViewById(R.id.bRemindMe);
        String ename = getIntent().getStringExtra("name");
        for (EventItem mEvent : EventListFragment.mEvents) {
            //Log.v("eventid","" + mEvent.getId());
            if(mEvent.getName().equals(ename))
            {
                eventItem = mEvent;
                break;
            }
        }
        fillEventActivity();


    }

    private void fillEventActivity()
    {
        // load image
        try {
            // get input stream
            InputStream ims = getAssets().open("event_img/" + eventItem.getId()+ ".jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            eventImage.setImageDrawable(d);
        }
        catch(IOException ex) {
            Log.v("Error", "Error setting image\n path : " + "event_img/" + eventItem.getName() + ".jpg");
        }
        name.setText(eventItem.getName());
        time.setText(eventItem.getTimeDay1());
        venue.setText(eventItem.getVenue());
        desc.setText(eventItem.getDescription());

    }



    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Matrix");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event_detail_menu, menu);
       /* overflow = menu.getItem(0);
        overflowMenu = new PopupMenu(this,overflow.);
        overflowMenu.inflate(R.menu.shr_fav_menu);
        overflowMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return onOptionsItemSelected(menuItem);
            }
        });*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            /*case R.id.overflow:
                overflowMenu.show();
                break;*/
            case R.id.shr:
                String msg = "Check out " + eventItem.getName() + " at Matrix 2014!!";
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, msg);
                try
                {
                    startActivity(Intent.createChooser(shareIntent, "Select an action"));
                    return true;
                }
                catch (Exception ex){}
                break;
            case R.id.fav:
                eventItem.setFavorite(true);
                Toast.makeText(this,"Added to favorites",Toast.LENGTH_SHORT).show();
                EventItem.updateDatabase(this,eventItem);
                break;
        }

        if(id == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
