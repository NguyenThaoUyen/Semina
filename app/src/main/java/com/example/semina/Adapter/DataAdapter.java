package com.example.semina.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.semina.Model.Data;
import com.example.semina.Model.Hotel;
import com.example.semina.R;

import java.util.List;

public class DataAdapter extends  RecyclerView.Adapter<DataAdapter.DataViewHolder>{
    private List<Data> dataList;

    private ItemClickListener itemClickListener;


    /**Contructor*/
    public DataAdapter(Activity activity, List<Data> dataList) {

        this.dataList = dataList;
    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    /** Get layout */
    public DataAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cardview,viewGroup,false);
        return new DataAdapter.DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataAdapter.DataViewHolder dataViewHolder, int position) {
        /** Set Value*/
        final Data data = dataList.get(position);



        dataViewHolder.title.setText(data.getTitle());
        dataViewHolder.content.setText(data.getContent());
        /**Sự kiện click vào item*/


        dataViewHolder.itemView
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onItemClick(dataViewHolder.getLayoutPosition());
                    }
                });




    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /** Create ViewHolder*/

    public class DataViewHolder  extends  RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView content;

        public DataViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
