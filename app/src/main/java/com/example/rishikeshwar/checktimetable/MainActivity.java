//https://www.viralandroid.com/2016/03/android-expandable-layout-tutorial-with-example.html

package com.example.rishikeshwar.checktimetable;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private ListView listView;
    private ExpandableLayout expandableLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();
        initToolBar();
        setupListView();
    }

    public void expandableButton1(View view) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout1);
        expandableLayout1.toggle(); // toggle expand and collapse
    }


    private void setupUIViews() {
        toolBar = (Toolbar) findViewById(R.id.toolBarMain);
        listView = (ListView) findViewById(R.id.lvMain);
    }

    private void initToolBar() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("TimeTable Management");
    }

    private void setupListView() {

        String[] titleArray = getResources().getStringArray(R.array.titleMain);
        String[] desriptionArray = getResources().getStringArray(R.array.descriptionMain);
        Log.d("coming", titleArray[0]);
        SimpleAdaptor simpleAdaptor = new SimpleAdaptor(this, titleArray, desriptionArray);
        listView.setAdapter(simpleAdaptor);
    }

    public class SimpleAdaptor extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, description;
        private String[] titleArray, descriptionArray;
        private ImageView image;

        public SimpleAdaptor (Context context, String[] title, String[] description) {
            mContext = context;
            titleArray = title;
            descriptionArray = description;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int i) {
            return titleArray[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null) {
                view = layoutInflater.inflate(R.layout.activity_main_single_item, null);
            }

            title = (TextView) view.findViewById(R.id.tvMain);
            description = (TextView) view.findViewById(R.id.tvDescription);
            image = (ImageView) view.findViewById(R.id.ivMain);

            title.setText(titleArray[i]);
            description.setText(descriptionArray[i]);

            if(titleArray[i].equalsIgnoreCase("TimeTable")) {
                image.setImageResource(R.drawable.timetable);
            } else if(titleArray[i].equalsIgnoreCase("Subjects")) {
                image.setImageResource(R.drawable.book);
            } else if(titleArray[i].equalsIgnoreCase("Faculty")) {
                image.setImageResource(R.drawable.play_i);
            } else {
                image.setImageResource(R.drawable.settings);
            }

            return view;
        }
    }
}
