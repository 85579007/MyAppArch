package hhh.myapparch.activity;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import hhh.myapparch.R;
import hhh.myapparch.adapter.CommonAdapter;
import hhh.myapparch.adapter.ViewHolder;

@ContentView(value = R.layout.activity_m)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.m_list)
    private ListView listView;
    @ViewInject(R.id.m_toolbar)
    private Toolbar toolbar;

    private List<String> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
//        setSupportActionBar(toolbar);
        getData();

        listView.setAdapter(new CommonAdapter<String>(MainActivity.this,data,R.layout.item_single_str) {
            @Override
            public void convert(ViewHolder helper, String item) {
                TextView textView=helper.getView(R.id.single_str_text);
                textView.setText(item);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        MyListActivity.startActivity(MainActivity.this);
                        break;
                    case 1:
                        ServiceActivity.startActivity(MainActivity.this);
                        break;
                    case 2:
                        ButterKnifeActivity.startAcitvity(MainActivity.this);
                    default:
                        break;
                }
            }
        });
    }



    private void getData() {
        data=new ArrayList<String>();
        data.add("RecyclerView List");
        data.add("Service");
        data.add("ButterKnife");
        data.add("okhttp");
        data.add("RxJava");
        data.add("Retrofit");
        data.add("Picasso");
        data.add("XUtil Test");
    }

}
