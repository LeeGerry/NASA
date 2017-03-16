package edu.auburn.weagle.nasa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.auburn.weagle.nasa.R;
import edu.auburn.weagle.nasa.activity.utils.ParserUtils;
import edu.auburn.weagle.nasa.config.AppConfig;
import edu.auburn.weagle.nasa.model.Photo;

/**
 * Author: Gary
 * Time: 17/2/4
 */

public class FunOneActivity extends BaseActivity {
    @BindView(R.id.gv_result) GridView lvModel;
    private List<Photo> photoList;
    private PhotosAdapter adapter;
    @BindView(R.id.iv_back) ImageView ivback;
    private String url;
    private Date date;
    @BindView(R.id.pb) ProgressBar pb;
    private String currentDate;
    private int id;
    private SimpleDateFormat dateformat;
    private String date3 = "2010-03-21";
    private String end = "2010-02-21";
    @BindView(R.id.tv_photo_id_title) TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        tvTitle.setText(AppConfig.ROVER_NAMES[id]);
        if (id == 2) {
            currentDate = date3;
            url = "https://api.nasa.gov/mars-photos/api/v1/rovers/spirit/photos?sol=1000&api_key=eVQWCl4aiAvDuNwvXzMFzvDQEZ2BakaANp03RVtI";
        } else {
            date = new Date();
            dateformat = new SimpleDateFormat("yyyy-MM-dd");
            currentDate = dateformat.format(date);
        }
        Log.i(TAG,currentDate);
        url = "https://api.nasa.gov/mars-photos/api/v1/rovers/" + AppConfig.ROVER_NAMES[id] + "/photos?earth_date=" + currentDate + "&api_key=eVQWCl4aiAvDuNwvXzMFzvDQEZ2BakaANp03RVtI";
        photoList = new ArrayList<>();
        adapter = new PhotosAdapter();
        lvModel.setAdapter(adapter);
        lvModel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Photo p = adapter.getItem(position);
                Intent intent = new Intent(FunOneActivity.this, DetailsActivity.class);
                intent.putExtra("p", p);
                startActivity(intent);
            }
        });

        lvModel.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        getDataFromServer();
    }

    private void produceUrl(String date) {
        url = "https://api.nasa.gov/mars-photos/api/v1/rovers/" + AppConfig.ROVER_NAMES[id] + "/photos?earth_date=" + date + "&api_key=eVQWCl4aiAvDuNwvXzMFzvDQEZ2BakaANp03RVtI";
    }

    /**
     * fun1 the adapter for listview
     */
    private class PhotosAdapter extends BaseAdapter {
        ImageOptions options;

        public PhotosAdapter() {
            options = new ImageOptions.Builder()
                    .setLoadingDrawableId(R.mipmap.default_loading)
                    .setFailureDrawableId(R.mipmap.default_loading)
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
            if (convertView != null) {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            } else {
                view = View.inflate(FunOneActivity.this, R.layout.list_item, null);
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

    private static class ViewHolder {
        TextView tvName;
        ImageView ivIcon;
    }

    private static int index = 1;

    /**
     * load data from server
     */
    private void getDataFromServer() {
        if(id == 2)
            url = "https://api.nasa.gov/mars-photos/api/v1/rovers/spirit/photos?sol=800&api_key=eVQWCl4aiAvDuNwvXzMFzvDQEZ2BakaANp03RVtI";
        RequestParams params = new RequestParams(url);
        Log.i(TAG, url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                pb.setVisibility(View.GONE);
                List<Photo> purser = ParserUtils.purser(result);
                Log.i(TAG, purser.size() + "");
                photoList.addAll(purser);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                pb.setVisibility(View.GONE);

                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, -index);
                index++;
                Date d = c.getTime();
                currentDate = dateformat.format(d);
                produceUrl(currentDate);
                getDataFromServer();
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

    @Override
    protected void onDestroy() {
        index = 1;
        super.onDestroy();
    }
}
