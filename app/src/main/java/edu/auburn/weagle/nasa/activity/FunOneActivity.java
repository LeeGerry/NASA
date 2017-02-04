package edu.auburn.weagle.nasa.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import edu.auburn.weagle.nasa.R;
import edu.auburn.weagle.nasa.config.AppConfig;
import edu.auburn.weagle.nasa.model.Photo;

/**
 * Author: Gary
 * Time: 17/2/4
 */

public class FunOneActivity extends BaseActivity {
    private ListView lvModel;
    private List<Photo> photoList;
    private PhotosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_one);
        lvModel = (ListView) findViewById(R.id.lvModel1);
        photoList = new ArrayList<>();
        adapter = new PhotosAdapter();
        lvModel.setAdapter(adapter);
        getDataFromServer();
    }
    /** fun1 the adapter for listview */
    private class PhotosAdapter extends BaseAdapter {
        ImageOptions options;
        public PhotosAdapter(){
            options = new ImageOptions.Builder()
                    .setLoadingDrawableId(R.mipmap.ic_launcher)
                    .setFailureDrawableId(R.mipmap.ic_launcher)
                    .build();
//
        }
        @Override
        public int getCount() {
            return photoList.size();
//            return 10;

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
            Photo info = photoList.get(position);


            if (convertView != null){
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }else {
                view = View.inflate(FunOneActivity.this, R.layout.list_item, null);
                holder = new ViewHolder();
                holder.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
                view.setTag(holder);
            }
            x.image().bind(holder.ivIcon, info.getImg_src(), options);
            return view;
        }
    }

    private static class ViewHolder{
        TextView tvName, tvSize;
        ImageView ivIcon;

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

                    for(int i = 0;i<10;i++){
                        Photo photo = new Photo();
                        JSONObject model = (JSONObject) photos.get(i);
                        photo.setImg_src(model.getString("img_src"));
                        photoList.add(photo);
                    }
                    adapter.notifyDataSetChanged();
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
