package edu.auburn.weagle.nasa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import edu.auburn.weagle.nasa.R;
import edu.auburn.weagle.nasa.activity.utils.ParserUtils;
import edu.auburn.weagle.nasa.model.Photo;

/**
 * Author: Gary
 * Time: 17/2/4
 */

public class ResultActivity extends BaseActivity {
    private GridView lvModel;
    private List<Photo> photoList;
    private PhotosAdapter adapter;
    private String url ;
    private ImageView ivBack;
    private TextView tvNotFound;
    private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvNotFound = (TextView) findViewById(R.id.tv_not_found);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        pb = (ProgressBar) findViewById(R.id.pb);

        String rover = intent.getStringExtra("rover");
        String camera = intent.getStringExtra("camera");
        String date = intent.getStringExtra("date");
        String sol = intent.getStringExtra("sol");
        pb.setVisibility(View.VISIBLE);
        if(TextUtils.isEmpty(sol)){
//            urlEarthDate  = "https://api.nasa.gov/mars-photos/api/v1/rovers/“   + roverName +”/photos?earth_date=” + dateString +”&camera=” + cameraName +”&api_key=eVQWCl4aiAvDuNwvXzMFzvDQEZ2BakaANp03RVtI”
            url = "https://api.nasa.gov/mars-photos/api/v1/rovers/"+rover+"/photos?earth_date="+date+"&camera="+camera+"&api_key=eVQWCl4aiAvDuNwvXzMFzvDQEZ2BakaANp03RVtI";
        }else {
            url = "https://api.nasa.gov/mars-photos/api/v1/rovers/"+rover+"/photos?sol="+sol+"&camera="+camera+"&api_key=eVQWCl4aiAvDuNwvXzMFzvDQEZ2BakaANp03RVtI";
        }
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
                    .setLoadingDrawableId(R.mipmap.default_loading)
                    .setFailureDrawableId(R.mipmap.default_loading)
                    .build();
//            options = new ImageOptions.Builder().setLoadingDrawableId();
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

        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>(){
            @Override
            public void onSuccess(String result) {
                pb.setVisibility(View.GONE);
                List<Photo> photos = ParserUtils.purser(result);
                if (photos.size() == 0)
                    tvNotFound.setVisibility(View.VISIBLE);
                else {
                    photoList.addAll(photos);
                    Log.i("test",photoList.size()+"url:"+url);
                }


//                if(adapter == null){
//                    adapter = new PhotosAdapter();
//                    lvModel.setAdapter(adapter);
//                }else {
//                    adapter.notifyDataSetChanged();
//                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                tvNotFound.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
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
