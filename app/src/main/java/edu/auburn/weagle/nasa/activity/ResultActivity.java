package edu.auburn.weagle.nasa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import edu.auburn.weagle.nasa.R;
import edu.auburn.weagle.nasa.activity.utils.ParserUtils;
import edu.auburn.weagle.nasa.config.AppConfig;
import edu.auburn.weagle.nasa.model.Photo;

/**
 * Author: Gary
 * Time: 17/2/4
 */

public class ResultActivity extends BaseActivity {
    private GridView lvModel;
    private List<Photo> photoList;
    private PhotosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        photoList = new ArrayList<>();
        lvModel = (GridView) findViewById(R.id.gv_result);
        adapter = new PhotosAdapter();
        lvModel.setAdapter(adapter);
        lvModel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Photo p = adapter.getItem(position);
                Intent intent = new Intent(ResultActivity.this, DetailsActivity.class);
                intent.putExtra("p",p);
                startActivity(intent);
            }
        });
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
        }
        @Override
        public int getCount() {
            return photoList.size();
        }

        @Override
        public Photo getItem(int position) {
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
                view = View.inflate(ResultActivity.this, R.layout.list_item, null);
                holder = new ViewHolder();
                holder.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
                holder.tvName = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(holder);
            }
            holder.tvName.setText(info.getEarth_date());
            x.image().bind(holder.ivIcon, info.getImg_src(), options);
            return view;
        }
    }

    private static class ViewHolder{
        TextView tvName;
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
                photoList.addAll(ParserUtils.purser(result));
                Log.i("test",photoList.size()+"--");
//                if(adapter == null){
//                    adapter = new PhotosAdapter();
//                    lvModel.setAdapter(adapter);
//                }else {
//                    adapter.notifyDataSetChanged();
//                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                adapter.notifyDataSetChanged();
            }
        });

    }

}
