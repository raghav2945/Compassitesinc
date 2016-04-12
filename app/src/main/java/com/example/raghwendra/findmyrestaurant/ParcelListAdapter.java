package com.example.raghwendra.findmyrestaurant;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raghawendra.kumar on 12-04-2016.
 */
public class ParcelListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<ParcelModel> DataSource=new ArrayList<>();
    //  public List<ParcelModel> FilteredDataSource=new ArrayList<>();
    private Fragment FragmentRef;
    OnItemClickListener clickListener;
    Context c;
    public ParcelListAdapter(Context context, List<ParcelModel> DataSource, Fragment FragmentRef) {
        this.DataSource=DataSource;
        this.c=context;
        this.FragmentRef=FragmentRef;
    }

    public List<ParcelModel> getDataSource() {
        return DataSource;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parcel_list_item, parent, false);
        viewHolder= new ViewHolderMain(v);

        return viewHolder;
    }
    @Override
    public int getItemViewType(int position) {
        return 1;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolderArg, int position) {
        ViewHolderMain viewHolder=(ViewHolderMain)viewHolderArg;
        ParcelModel item = DataSource.get(position);
        Picasso.with(c).load(item.getLogo_url()).resize(100,100).into(viewHolder.ParcelIcon);
        viewHolder.ParcelName.setText(item.getRestaurant_name());
        viewHolder.ParcelPrice.setText(item.getAddress());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return DataSource.size();
    }

    public ParcelModel getItem(int position)
    {
        return DataSource.get(position);
    }

    public class ViewHolderMain extends RecyclerView.ViewHolder  implements View.OnClickListener {
        CardView CardView;
        ImageView ParcelIcon;
        TextView ParcelName;
        TextView ParcelPrice;
        public ViewHolderMain(View itemView) {
            super(itemView);
            CardView = (CardView)itemView.findViewById(R.id.card_view);
            ParcelIcon = (ImageView)itemView.findViewById(R.id.ivParcelImage);

            ParcelName = (TextView) itemView.findViewById(R.id.TvParcelName);
            ParcelPrice=(TextView) itemView.findViewById(R.id.TvParcelPrice);
            CardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v,getAdapterPosition());

        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
