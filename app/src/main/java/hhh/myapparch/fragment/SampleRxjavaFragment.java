package hhh.myapparch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hhh.myapparch.R;
import hhh.myapparch.activity.AppInfoActivity;
import hhh.myapparch.bean.Result;
import hhh.myapparch.bean.Student;
import hhh.myapparch.http.x.XUtils;
import hhh.myapparch.log.MyLog;
import hhh.myapparch.utils.JsonUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hhh on 2016/10/12.
 */
public class SampleRxjavaFragment extends Fragment {
    @BindView(R.id.rxbtn1)
    Button rxbtn1;
    @BindView(R.id.rxbtn2)
    Button rxbtn2;
    @BindView(R.id.rxbtn3)
    Button rxbtn3;
    @BindView(R.id.rxtxt)
    TextView rxtxt;
    @BindView(R.id.img)
    ImageView img;

    private OkHttpClient client = new OkHttpClient();

    private Action1<String> nextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            MyLog.LogWithString(s);
            rxtxt.append(s);
        }
    };

    private Action1<Throwable> errorAction = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {

        }
    };

    private Action0 completeAction = new Action0() {
        @Override
        public void call() {
            MyLog.LogWithString("completed");
            rxtxt.append("completed");
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rxjava, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    private int i = 0;
    @OnClick({R.id.rxbtn1, R.id.rxbtn2, R.id.rxbtn3})
    public void onClick(View v) {
//        rx.Observable.just("message"+i).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                MyLog.LogWithString(s);
//            }
//        });
        switch (v.getId()) {
            case R.id.rxbtn1:
                Observable.just("msg" + i).subscribe(nextAction, errorAction, completeAction);
                i++;
                break;
            case R.id.rxbtn2:

                break;
            case R.id.rxbtn3:
                String surl = "http://119.29.193.241/student/get";
                XUtils.show(surl);
                Observable.just(surl)
                        .map(new Func1<String, Result<Student>>() {
                            @Override
                            public Result<Student> call(String s) {
                                Request request = new Request.Builder()
                                        .url(s)
                                        .build();
                                try {
                                    Response response = client.newCall(request).execute();
                                    Result<Student> rs=new Result<Student>(){};
                                    return JsonUtils.fromJson(response.body().string(), rs.getClass().getGenericSuperclass());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }
                        })
                        .flatMap(new Func1<Result<Student>, Observable<Student>>() {
                            @Override
                            public Observable<Student> call(Result<Student> studentResult) {
                                return Observable.from(studentResult.getData());
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Student>() {
                            @Override
                            public void onCompleted() {
                                rxtxt.append("student : completed");
                            }

                            @Override
                            public void onError(Throwable e) {
                                rxtxt.append("student : error"+e.getMessage());
                            }

                            @Override
                            public void onNext(Student student) {
                                rxtxt.append(student.toString());
                            }
                        });
                break;
        }
    }
}
