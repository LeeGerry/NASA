package edu.auburn.weagle.nasa.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.auburn.weagle.nasa.R;
import edu.auburn.weagle.nasa.model.Photo;

/**
 * Author: Gary
 * Time: 17/2/4
 */

public class FunOneActivity extends BaseActivity {
    private ListView lvModel;
    private List<Photo> photoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_one);
        lvModel = (ListView) findViewById(R.id.lvModel1);
        photoList = new ArrayList<>();


    }
    private class PhotosAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return photoList.size();

        }

        @Override
        public Object getItem(int position) {
            return photoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            View view;
            Photo info;


            if (convertView != null && convertView instanceof RelativeLayout){
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }else {
                view = View.inflate(FunOneActivity.this, R.layout.list_item, null);
                holder = new ViewHolder();

                view.setTag(holder);
            }

            return view;
        }
    }

    private static class ViewHolder{
        TextView tvName, tvSize;
        ImageView ivIcon;

    }
}
