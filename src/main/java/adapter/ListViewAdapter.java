package adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigationdemo.R;

import java.util.List;

import utils.JsonBean;

/*
    Author:leia
    Write The Code Change The World    
*/public class ListViewAdapter  extends RecyclerView.Adapter<ListViewAdapter.Holder> {
    private final List<JsonBean> mdata;
    public  Context context;

    public ListViewAdapter(Context context, List<JsonBean>mdata) {
        Log.d("msg","构造付费");
            this.context=context;
            this.mdata=mdata;
    }

    @NonNull
    @Override
    public ListViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.item_list_view,null);
        return new Holder(view);
    }
    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull ListViewAdapter.Holder holder, int position) {
        holder.setData(mdata.get(position));


    }

    @Override
    public int getItemCount() {
        if (mdata!=null){
            return mdata.size();
        }
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView message;
        public TextView count;
        public  TextView player;
        public Holder(@NonNull View itemView) {
            super(itemView);
            message=itemView.findViewById(R.id.message);
            count=itemView.findViewById(R.id.count);
            player=itemView.findViewById(R.id.Player_name);
        }

        public void setData(JsonBean jsonBean) {
            message.setText(jsonBean.getMessage());
            count.setText(jsonBean.getCount());
            player.setText(jsonBean.getPlayer());
        }
    }
}
