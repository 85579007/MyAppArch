package hhh.myapparch.fragment;

import android.view.View;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import hhh.myapparch.R;
import hhh.myapparch.activity.MyListActivity;

/**
 * Created by hhh on 2016/6/12.
 */
@ContentView(R.layout.fragment_http)
public class HttpFragment extends BaseFragment {
    @Event(R.id.btn_test1)
    private void onTest1Click(View v){
//        RequestParams params=new RequestParams(Code.URL+Code.STUDENT);
////        params.addBodyParameter("name","value");
//        XUtils.send(HttpMethod.GET, params, new BaseCallback<Result<Student>>() {
//            @Override
//            public void success(Result<Student> data) {
//                XUtils.show(data.getMessage());
//                if(data.getCode()==Result.STATE_SUC){
//                    if(data.getData()!=null){
//
//                    }else{
//                        XUtils.show("未获取到数据");
//                    }
//                }
//            }
//        },true);
        MyListActivity.startActivity(getActivity());
    }
}
