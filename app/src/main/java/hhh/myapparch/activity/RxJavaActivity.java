package hhh.myapparch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hhh.myapparch.R;
import hhh.myapparch.log.MyLog;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by hhh on 2016/9/27.
 */
public class RxJavaActivity extends BaseActivity {
    @BindView(R.id.rxbtn1)
    Button rxbtn1;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.rxbtn2)
    Button rxbtn2;

//    private rx.Observable<String> observable;

    //    Subscriber<String> mySubcriber=new Subscriber<String>() {
//        @Override
//        public void onCompleted() {
//            MyLog.LogWithString("completed");
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            MyLog.LogWithString("error");
//        }
//
//        @Override
//        public void onNext(String s) {
//            MyLog.LogWithString(s);
//        }
//    };
    private Action1<String> nextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            MyLog.LogWithString(s);
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
        }
    };

    public static void startAcitivity(Context context) {
        Intent intent = new Intent(context, RxJavaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(nextAction, errorAction, completeAction);
    }

    private int i = 0;

    @OnClick({R.id.rxbtn1,R.id.rxbtn2})
    public void onClick(View v) {
//        rx.Observable.just("message"+i).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                MyLog.LogWithString(s);
//            }
//        });
        switch (v.getId()){
            case R.id.rxbtn1:
                Observable.just("msg" + i).subscribe(nextAction, errorAction, completeAction);
                i++;
                break;
            case R.id.rxbtn2:
                break;
        }
    }

}
