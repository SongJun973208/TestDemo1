package org.sj.testdemo.zxing;

import com.journeyapps.barcodescanner.CaptureActivity;

/**
 * This Activity is exactly the same as CaptureActivity, but has a different orientation
 * setting in AndroidManifest.xml.
 * 很纠结
 */
public class AnyOrientationCaptureActivity extends CaptureActivity {
    private int i = 0, j= 3;
}
