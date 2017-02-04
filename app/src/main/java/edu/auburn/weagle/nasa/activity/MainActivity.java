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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

import edu.auburn.weagle.nasa.R;
import edu.auburn.weagle.nasa.config.AppConfig;

public class MainActivity extends BaseActivity {
    private ArrayList<Drawable> list;
    private ArrayList<String> images;

    private static final String[] names = {"Curiosity", "Opportunity", "Spirit",
            "Customize","Setting","Description"};

    private static final int[] icons = {R.drawable.fun_one_selector,
            R.drawable.fun_two_selector,R.drawable.fun_three_selector,
            R.drawable.fun_four_selector,
            R.drawable.fun_setting_selector,
            R.drawable.fun_desc_selector};
    private GVAdapter adapter;
    private GridView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        images = new ArrayList<>();
        adapter = new GVAdapter();
        gv = (GridView) findViewById(R.id.gv);
        gv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent(MainActivity.this, FunOneActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, FunOneActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, FunOneActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, FunFourActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });


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
    /**
     * load data from server
     */
    private void getDataFromServer(){
        RequestParams params = new RequestParams(AppConfig.sample);

        x.http().get(params, new Callback.CommonCallback<String>(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jo = new JSONObject(result);
                    JSONArray photos = jo.getJSONArray("photos");
                    JSONObject model = (JSONObject) photos.get(0);
                    String img_src = model.getString("img_src");
                    ImageOptions options = new ImageOptions.Builder()
                            .setLoadingDrawableId(R.mipmap.ic_launcher)
                            .setFailureDrawableId(R.mipmap.ic_launcher)
                            .build();
//                    x.image().bind(iv, img_src, options);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
