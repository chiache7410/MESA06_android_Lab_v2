package tw.org.iii.lab_v2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
        roomkey.add("xxx");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("StickyGobblet");
        myRef.child("Playing").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                roomkey.add(dataSnapshot.getKey());
                myAdapter.notifyDataSetChanged();
                Log.v("testlog","onChildAdded");
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.v("testlog","onChildChanged");
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                roomkey.remove(dataSnapshot.getKey());
                myAdapter.notifyDataSetChanged();
                Log.v("testlog","onChildRemoved");
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.v("testlog","onChildMoved");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("testlog","onCancelled");
            }
        });
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