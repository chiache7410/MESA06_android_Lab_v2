package tw.org.iii.lab_v2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridView drawer;
    private MyAdapter myAdapter;
    private ArrayList<String> roomkey;
    public int[] getkey = {0,1,2,3,4,5,6};
    public int[] getimg = {R.drawable.ballgreat,R.drawable.ballmaster,
                            R.drawable.ballpoke,R.drawable.ballcherish,
                            R.drawable.ballpremier,R.drawable.ballsafari,
                            R.drawable.ballultra};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roomkey = new ArrayList<>();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
        //
        roomkey.add("xxx");
        roomkey.add("yyy");
        roomkey.add("zzz");
        drawer = (GridView)findViewById(R.id.gridView);
        drawer.setX(-200f);
        initGridView();
    }
    private void initGridView() {
        myAdapter = new MyAdapter(this);
        drawer.setAdapter(myAdapter);

    }
    private class MyAdapter extends BaseAdapter {
        private Context context;
        MyAdapter(Context context) {
            this.context = context;
        }
        @Override
        public int getCount() {
            int size = roomkey.size();
            Log.v("testlog","Adapter Count : "+size);
            return size;
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.layout_item,null);
//                img = new ImageView(context);
//                img.setLayoutParams(new GridView.LayoutParams(128,128));
//                img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
            ImageView img = (ImageView)view.findViewById(R.id.item_img);
            TextView title = (TextView)view.findViewById(R.id.item_title);
            img.setImageResource(getimg[getkey[i]]);
            title.setText(roomkey.get(i));
            return view;
        }
    }
    public void bt1(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(drawer, "x", -200, 0);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator);
        set.setDuration(1000);
        Log.v("testlog", "bt1 start");
        set.start();
    }
    public void bt2(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(drawer, "x", 0, -200);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator);
        set.setDuration(1000);
        Log.v("testlog", "bt2 start");
        set.start();
    }
}