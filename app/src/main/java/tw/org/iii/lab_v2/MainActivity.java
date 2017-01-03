package tw.org.iii.lab_v2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private View drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.gridView);
        drawer.setX(-200f);
    }
    public void bt1(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(drawer, "x", -200, 0);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator);
        set.setDuration(1000);
        Log.v("testlog","bt1 start");
        set.start();
    }
    public void bt2(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(drawer, "x", 0, -200);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator);
        set.setDuration(1000);
        Log.v("testlog","bt2 start");
        set.start();
    }
}
