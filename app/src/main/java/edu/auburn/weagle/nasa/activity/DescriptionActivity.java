package edu.auburn.weagle.nasa.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.auburn.weagle.nasa.R;

/**
 * Author: Gary
 * Time: 17/2/5
 */

public class DescriptionActivity extends BaseActivity {
    private TextView tvCenter;
    private ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvCenter = (TextView) findViewById(R.id.tvCenter);
        tvCenter.setText("MarsTracker gets mars photos from open API of NASA.\n" +
                        "Author: Guorui Li (gzl0023@auburn.edu)\nYang Cao (yzc0020@auburn.edu)\nSicheng Li (szl0072@auburn.edu)\nYufei Yan (yzy0050@auburn.edu)\n" +
                        "Sponsored by Equifax, 2017 Hackathon, Auburn University");

    }

}
