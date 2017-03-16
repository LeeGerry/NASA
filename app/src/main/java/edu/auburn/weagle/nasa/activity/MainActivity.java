package edu.auburn.weagle.nasa.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.auburn.weagle.nasa.R;

import static edu.auburn.weagle.nasa.config.AppConfig.icons;
import static edu.auburn.weagle.nasa.config.AppConfig.names;

public class MainActivity extends BaseActivity {
    private ArrayList<Drawable> list;
    private ArrayList<String> images;
    private GVAdapter adapter;
    @BindView(R.id.gv)
    GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        images = new ArrayList<>();
        adapter = new GVAdapter();
        gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new MainGridItemClick());
    }

    class MainGridItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = null;
            switch (position) {
                case 0:
                    intent = new Intent(MainActivity.this, FunOneActivity.class);
                    intent.putExtra("id", 0);
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(MainActivity.this, FunOneActivity.class);
                    intent.putExtra("id", 1);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(MainActivity.this, FunOneActivity.class);
                    intent.putExtra("id", 2);
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(MainActivity.this, FunFourActivity.class);
                    startActivity(intent);
                    break;
                case 4:

                    break;
                case 5:
                    intent = new Intent(MainActivity.this, DescriptionActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    /**
     * 主界面GRIDVIEW适配器
     */
    private class GVAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this, R.layout.gv_item, null);
            ImageView iv = (ImageView) view.findViewById(R.id.ivIcon);
            TextView tv = (TextView) view.findViewById(R.id.tvName);
            iv.setImageResource(icons[position]);
            tv.setText(names[position]);
            return view;
        }
    }
}
