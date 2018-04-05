package com.iffly.booktalk_app.presentation.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerViewAdapter<T extends AdapterViewModel> extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder<T>> {
    private List<T> list;
    private Context context;
    public OnRecyclerViewItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    protected OnRecyclerViewItemClickListener itemClickListener;

    public RecyclerViewAdapter(List<T> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getItemViewType(int position){
        return list.get(position).getLayoutID();

    }
    @Override
    public RecyclerViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(viewType,parent,false);

        return new RecyclerViewHolder<T>(view,this);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder<T> holder, int position) {
        holder.bind(list.get(position).getResID(),list.get(position));
        holder.getBinding().executePendingBindings();
        if(holder.getItemClickListener()!=this.getItemClickListener())
            holder.setItemClickListener(itemClickListener);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public static class RecyclerViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ViewDataBinding dataBinding;

        public OnRecyclerViewItemClickListener getItemClickListener() {
            return itemClickListener;
        }

        public void setItemClickListener(OnRecyclerViewItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;

        }

        private OnRecyclerViewItemClickListener itemClickListener;
        private RecyclerView.Adapter adapter;
        public RecyclerViewHolder(View itemView,RecyclerView.Adapter adapter) {
            super(itemView);

            itemView.setOnClickListener(this);
            dataBinding= DataBindingUtil.bind(itemView);
            this.adapter=adapter;

        }
        public <E extends T>void bind(int resID,E e){
            dataBinding.setVariable(resID,e);

        }
        public ViewDataBinding getBinding(){
            return this.dataBinding;
        }
        @Override
        public void onClick(View view) {
            if(itemClickListener!=null)
                itemClickListener.onItemClick(view,adapter,getAdapterPosition());
        }
    }
    public  interface OnRecyclerViewItemClickListener{
        public void onItemClick(View view,RecyclerView.Adapter adapter,int position);

    }
}
