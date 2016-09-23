package hhh.myapparch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hhh.myapparch.R;
import hhh.myapparch.bean.Student;

/**
 * Created by hhh on 2016/6/16.
 */
public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {
    private Context context;
    private List<Student> list;

    public MyListAdapter(Context context,List list) {
        this.context = context;
        this.list=list;
    }

    @Override
    public MyListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyListAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvAddress.setText(list.get(position).getAddress());
        Date date=list.get(position).getBirthday();
        holder.tvBirthday.setText(String.format("%s-%s-%s",date.getYear(),date.getMonth(),date.getDate()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvAddress;
        private TextView tvBirthday;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.item_name);
            tvAddress=(TextView)itemView.findViewById(R.id.item_address);
            tvBirthday=(TextView)itemView.findViewById(R.id.item_birthday);
        }
    }
}
