package edu.auburn.weagle.nasa.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import edu.auburn.weagle.nasa.R;
import edu.auburn.weagle.nasa.model.Photo;

/**
 * Author: Gary
 * Time: 17/2/4
 */

public class DetailsActivity extends BaseActivity {
    private ImageView ivDetails;
    private TextView tvDetails;
    private ImageOptions options;
    private ImageView ivBack,ivShare;
    private TextView tvPhotoId, tvSol, tvFrom, tvCamera, tvEarthDate, tvTitle;
    private Photo photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ShareSDK.initSDK(this, "1b04865fc9058");
        ivDetails = (ImageView) findViewById(R.id.iv_details);
        ivShare = (ImageView) findViewById(R.id.ivShare);
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_photo_id_title);
        tvPhotoId = (TextView) findViewById(R.id.tv_photo_id);
        tvCamera = (TextView) findViewById(R.id.tv_camera);
        tvFrom = (TextView) findViewById(R.id.tv_from);
        tvSol = (TextView) findViewById(R.id.tv_sol);
        tvEarthDate = (TextView) findViewById(R.id.tv_earth_date);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        photo = (Photo) getIntent().getSerializableExtra("p");
        options = new ImageOptions.Builder()
                .setLoadingDrawableId(R.mipmap.default_loading)
                .setFailureDrawableId(R.mipmap.default_loading)
                .build();
        x.image().bind(ivDetails, photo.getImg_src(), options);
//        StringBuilder desc = new StringBuilder();
//
//        desc.append("Photo ID: "+photo.getId()+"\n")
//                .append("sol: "+photo.getSol()+"\n")
//                .append("From: "+photo.getRover().getName()+"\n")
//                .append("Camera: "+photo.getCamera().getFull_name()+"("+photo.getCamera().getName()+")"+"\n")
//                .append("Earth date:"+photo.getEarth_date());
        tvPhotoId.setText(String.valueOf(photo.getId()));
        tvEarthDate.setText(photo.getEarth_date());
        tvCamera.setText(photo.getCamera().getFull_name() + " (" + photo.getCamera().getName() + ")");
        tvSol.setText(photo.getSol() + "");
        tvFrom.setText(photo.getRover().getName());
        tvTitle.setText("PhotoID " + photo.getId());
//        tvDetails.setText(desc.toString());
    }

    private void showShare() {
        if (photo == null)return;
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();

        oks.setTitle("Mar");
//        oks.setTitleUrl("http://google.com");
        oks.setText("From MarsTracker:");
        oks.setImageUrl(photo.getImg_src());

        //oks.setUrl("http://sharesdk.cn");
        oks.setComment("comment");
        oks.setSite(getString(R.string.app_name));
        //oks.setSiteUrl("http://sharesdk.cn");

        oks.show(this);
    }
}
