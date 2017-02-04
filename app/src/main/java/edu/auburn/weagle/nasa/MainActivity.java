package edu.auburn.weagle.nasa;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

import edu.auburn.weagle.nasa.config.AppConfig;

public class MainActivity extends AppCompatActivity {
//    private ListView lvImage;
    private ArrayList<Drawable> list;
    private ImageView iv;
    private ArrayList<String> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        lvImage = (ListView) findViewById(lvImage);
        iv = (ImageView) findViewById(R.id.ivImage);
        list = new ArrayList<>();
        images = new ArrayList<>();
        getDataFromServer();

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
                    x.image().bind(iv, img_src, options);
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
    class LvAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return null;
        }
    }
}
