package hhh.myapparch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.Arrays;

import hhh.myapparch.R;
import hhh.myapparch.adapter.MyListAdapter;
import hhh.myapparch.bean.Result;
import hhh.myapparch.bean.Student;
import hhh.myapparch.constant.Code;
import hhh.myapparch.http.BaseCallback;
import hhh.myapparch.http.XUtils;

/**
 * Created by hhh on 2016/6/16.
 */
@ContentView(R.layout.activity_mylist)
public class MyListActivity extends BaseActivity {
    @ViewInject(R.id.mylist_recyclerview)
    private RecyclerView recyclerView;

    private Student[] students;
    private MyListAdapter myListAdapter;

    public static void startActivity(Context context){
        Intent intent=new Intent(context,MyListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData();
    }

    private void getData() {
        RequestParams params=new RequestParams(Code.URL+Code.STUDENT);
        XUtils.send(HttpMethod.GET, params, new BaseCallback<Result<Student>>() {
            @Override
            public void success(Result<Student> data) {
                XUtils.show(data.getMessage());
                if(data.getCode()==Result.STATE_SUC){
                    students=data.getData();
                    init();
                }
            }
        },true);
    }

    private void init() {
        myListAdapter=new MyListAdapter(this, Arrays.asList(students));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myListAdapter);
    }
}
