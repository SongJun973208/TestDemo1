package org.sj.testdemo.zxing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.sj.testdemo.R;

public class TestProgress extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_progress);

        ProgressBarHorizontal pbh_bar = findViewById(R.id.pbh_bar);
        pbh_bar.setMax(1000);
        pbh_bar.setProgress(10);

    }
}
