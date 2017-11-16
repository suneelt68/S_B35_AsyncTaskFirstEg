package com.example.srikar.s_b35_asynctaskfirsteg;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.srikar.s_b35_asynctaskfirsteg.MainActivity.arrayList;

/**
 * Created by sunilkumar on 17-07-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{


    public Context context;
    public List<Contacts> al = new ArrayList<>();

    public MyAdapter(Context context , List arrayList){
        this.context = context;
        this.al = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card,null);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Images c = arrayList.get(position);



        //holder.tv1.setText(c.getSno());

        //   holder.tv2.setText(c.getName());

        // holder.tv3.setText(c.getEmail());

        //  holder.tv4.setText(c.getMobile());

        // Glide.with(MainActivity.this).load(c.getImageUrl()).placeholder(R.mipmap.ic_launcher).crossFade().into(holder.image);
           /* Glide.with(MainActivity.this).
                    load(c.getImageUrl()).
                    placeholder(R.mipmap.ic_launcher).
                    crossFade().
                    into(holder.image);*/

        Picasso.with(context)
                .load(c.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)   // optional
                .resize(280, 280)                        // optional
                //.rotate(90)                             // optional
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv1,tv2,tv3,tv4;
        public FloatingActionButton floatingActionButton;
        public ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.id);
            tv2 = (TextView) itemView.findViewById(R.id.name);
            tv3 = (TextView) itemView.findViewById(R.id.email);
            tv4 = (TextView) itemView.findViewById(R.id.mobile);
            image = (ImageView) itemView.findViewById(R.id.image);
            //  floatingActionButton = (FloatingActionButton) itemView.findViewById(R.id.fab);
//                floatingActionButton.setImageResource(R.drawable.download);

        }
    }
}
