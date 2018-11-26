package com.nordwest.university_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FloorPlanAdaptor extends RecyclerView.Adapter<FloorPlanAdaptor.myVireHolder> {


    Context mContext;
    List<ItemViewConstructor> mData;
    int i = 0;

    public FloorPlanAdaptor(Context mContext, List<ItemViewConstructor> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }



    @Override
    public myVireHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.card_item, parent, false);
        return new myVireHolder(v);
    }

    @Override
    public void onBindViewHolder(myVireHolder holder, int position) {
        holder.background_img.setImageResource(mData.get(position).getBackground());
        holder.profile_photo.setImageResource(mData.get(position).getProfilePhoto());
        holder.tv_title.setText(mData.get(position).getProfileName());
        holder.tv_nbDescription.setText(mData.get(position).getFloorDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myVireHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView profile_photo, background_img;
        TextView tv_title, tv_nbDescription;


        public  myVireHolder(View itemView){
            super(itemView);
            itemView.setTag(i++);
            itemView.setOnClickListener(this);
            profile_photo = itemView.findViewById(R.id.profile_img);
            background_img = itemView.findViewById(R.id.cardBackground);
            tv_title = itemView.findViewById(R.id.card_description);
            tv_nbDescription = itemView.findViewById(R.id.card_title);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            mContext.startActivity(new Intent(mContext, Singele_flor_planActivity.class).putExtra(Singele_flor_planActivity.FLOOR_ID, mData.get(position).getFloorDescription()));
            Toast.makeText(mContext, mData.get(position).getFloorDescription(), Toast.LENGTH_SHORT).show();
        }
    }
}
