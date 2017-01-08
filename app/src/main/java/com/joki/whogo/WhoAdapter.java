package com.joki.whogo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 嗨呀 on 2017/1/8.
 */

public class WhoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static String TAG="WhoAdapter";

    public ArrayList<String> data = new ArrayList<String>();//装数据


    /**
     * 当创建ViewHoder时就会执行
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //1.加载布局(R.layout.item_who) xml -> View -> ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_who,parent,false);
        return new ViewHolder(view);
    }

    /**
     * 当绑定ViewHolder时调用，这是时候就要为ViewHolder设置内容
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*
            1.找到TextView -> Holder
            2.找到内容 -> data.get(position)
            3. setText()
         */
        //向下转型
        ((ViewHolder) holder).Tvshow.setText(data.get(position));//为Text设置内容
    }

    /**
     * 获取Iterm的个数 -> 由ArrayList<String>的长度决定
     * @return
     */
    @Override
    public int getItemCount() {
        return data.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Tvshow;
        //代表每一个Item 要有一个显示字符串的控件TextView 假设ViewHolder已经把item.xml加载上去了
        public ViewHolder(View itemView) {
            super(itemView);
            //找到TextView
            Tvshow = (TextView) itemView.findViewById(R.id.tv_show);

        }
    }
}
