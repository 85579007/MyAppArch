package hhh.myapparch.http.ok;

import android.os.Handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hhh on 2016/10/8.
 */
public abstract class DownloadCallback extends BaseCallback<String> {
    private File file;

    public DownloadCallback(Handler handler, File file) {
        super(handler);
        mType=String.class;
        this.file=file;
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if(response.isSuccessful()){
            InputStream is=null;
            byte[] buf=new byte[2048];
            FileOutputStream fos=null;
            is=response.body().byteStream();
            fos=new FileOutputStream(file);
            int len=0;
            while((len=is.read(buf))!=-1){
                fos.write(buf,0,len);
            }
            fos.flush();
            if(is!=null){
                is.close();
            }
            if(fos!=null){
                fos.close();
            }
            this.sendSuccess("file download success: size("+response.body().contentLength()+")");
        }else{
            this.sendFailed("response unsuccessed");
        }
    }
}
