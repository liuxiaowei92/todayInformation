package com.study.todayinformation.main.shanghai.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.todayinformation.R;
import com.study.todayinformation.main.shanghai.dto.ShanghaiBean;
import com.study.todayinformation.main.shanghai.view.ShanghaiDetailActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @authour lxw
 * @function 复杂条目 仿饿了么
 * @date 2019/9/20
 */
public class ShanghaiElemAdapter extends RecyclerView.Adapter {

    private final ArrayList<ShanghaiBean> mData;
    private final boolean mIsHor;
    private Activity mContext;
    private final RecyclerView.RecycledViewPool mRecycledViewPool;

    public ShanghaiElemAdapter(Activity context, ArrayList<ShanghaiBean> data, boolean isHor) {
        mRecycledViewPool = new RecyclerView.RecycledViewPool();
        mData = data;
        this.mContext = context;
        //高级缓存
        mIsHor = isHor;
    }


    //创建view 进行缓存
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ShanghaiBean.IShanghaiItemType.VERTICAL) {
            if (mIsHor) {
                Log.i("onCreateViewHolder", "VERTICAL");
            }
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment, parent, false);
            ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(inflate);
            return viewHolder;
        } else if (viewType == ShanghaiBean.IShanghaiItemType.HORIZANTAL) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment_rv, null);
            ShanghaiViewHolderRv viewHolder = new ShanghaiViewHolderRv(inflate);
            return viewHolder;
        }
        return null;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShanghaiBean shanghaiBean = mData.get(position);
        if (holder instanceof ShanghaiViewHolder) {
            ((ShanghaiViewHolder) holder).mTv.setText(shanghaiBean.getDec());
            ((ShanghaiViewHolder) holder).mIv.setVisibility(shanghaiBean.isShowImg() ? View.VISIBLE : View.GONE);

//            //不建议这样写 写在viewHolder中
//            ((ShanghaiViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });

            //优化点击事件
            ((ShanghaiViewHolder) holder).itemView.setTag(shanghaiBean);

        } else if (holder instanceof ShanghaiViewHolderRv) {
            //高级优化RecycledViewPool 写在viewHoler
//            ((ShanghaiViewHolderRv) holder).mRecyclerView.
//                    setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
            ((ShanghaiViewHolderRv) holder).mRecyclerView.setAdapter(new ShanghaiElemAdapter(mContext, shanghaiBean.getData(), true));
        }

    }

    //条目数量
    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItemType();
    }

    /**
     * 缓存view 内存友好设计
     */
    public class ShanghaiViewHolder extends RecyclerView.ViewHolder {

        private TextView mTv;
        private ImageView mIv;

        public ShanghaiViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_shanghai_tv);
            mIv = itemView.findViewById(R.id.item_shanghai_iv);
            //点击事件高级优化 最好写在viewholder中
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShanghaiBean shanghaiBean = (ShanghaiBean) v.getTag();
//                    Toast.makeText(mContext, "我被点击了+position ：" + position, Toast.LENGTH_SHORT).show();
                    //转场动画
                    if(shanghaiBean.isShowImg()) {
                        ShanghaiDetailActivity.start_5_0(mContext, mIv);
                    }
                }
            });
        }
    }

    public class ShanghaiViewHolderRv extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;

        public ShanghaiViewHolderRv(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.item_shanghai_rv);
            //高级优化
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            //实现多个recycleView的复用
            linearLayoutManager.setRecycleChildrenOnDetach(true);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setRecycledViewPool(mRecycledViewPool);
        }
    }
}
