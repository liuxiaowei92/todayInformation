package com.study.todayinformation.main.shanghai.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.todayinformation.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @authour lxw
 * @function 复杂条目 仿饿了么
 * @date 2019/9/20
 */
public class ShanghaiAdapter2 extends RecyclerView.Adapter {


    public ShanghaiAdapter2() {

    }


    //创建view 进行缓存
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment_2, null);
        ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(inflate);
        return viewHolder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    //条目数量
    @Override
    public int getItemCount() {
        return 15;
    }


    /**
     * 缓存view 内存友好设计
     */
    public class ShanghaiViewHolder extends RecyclerView.ViewHolder {

        private TextView mTv;
        private ImageView mIv;

        public ShanghaiViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
