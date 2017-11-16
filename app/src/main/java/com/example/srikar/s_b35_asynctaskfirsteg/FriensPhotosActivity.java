package com.example.srikar.s_b35_asynctaskfirsteg;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FriensPhotosActivity extends AppCompatActivity {

    FriendsTask myTask;
    MyAdapter myAdapter;
    MainActivity.MyTask task;
    ArrayList<FriendsImages> arrayList = new ArrayList<>();

    TextView imageView;
    RecyclerView recyclerView;

    MainActivity mainActivity =  new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friens_photos);


        //imageView = (TextView) findViewById(R.id.frimage);
        myAdapter = new MyAdapter();
        recyclerView= (RecyclerView) findViewById(R.id.frRecycler);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
     //   recyclerView.addItemDecoration(new MainActivity.GridSpacingItemDecoration(2, mainActivity.dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);


        Intent in  = getIntent();
        Bundle bundle = in.getExtras();
        String name = bundle.getString("getName");
        myTask = new FriendsTask();

        myTask.execute("https://quarkbackend.com/getfile/suneeltamada22/"+name+"photos");

    }

    public class FriendsTask extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... params) {

           String getPhotos = mainActivity.connectionLogic(params);

            return getPhotos;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject j = new JSONObject(s);
                JSONArray contacts = j.getJSONArray("contacts");
                for (int i=0;i<contacts.length();i++){
                    JSONObject k = contacts.getJSONObject(i);
                    String image = k.getString("image").trim();

                    FriendsImages images = new FriendsImages(image);
                    arrayList.add(images);
                    myAdapter.notifyDataSetChanged();



                }
                //myDataBase.close();

                //getDataFRomDb();

                //apply above text on textview
            } catch (JSONException e) {
                e.printStackTrace();
            }


            super.onPostExecute(s);
        }
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = getLayoutInflater().inflate(R.layout.album_card,parent,false);

            MyAdapter.ViewHolder viewHolder = new MyAdapter.ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final MyAdapter.ViewHolder holder, int position) {

            final FriendsImages c = arrayList.get(position);

            //holder.tv1.setText(c.getSno());

            //   holder.tv2.setText(c.getName());

            // holder.tv3.setText(c.getEmail());

            //  holder.tv4.setText(c.getMobile());

            // Glide.with(MainActivity.this).load(c.getImageUrl()).placeholder(R.mipmap.ic_launcher).crossFade().into(holder.image);
            Glide.with(FriensPhotosActivity.this).
                    load(c.getImageUrl()).
                    placeholder(R.mipmap.ic_launcher).
                    crossFade().
                    into(holder.image);


            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });




            /*Picasso.with(MainActivity.this)
                    .load(c.getImageUrl())
                    .placeholder(R.mipmap.ic_launcher)   // optional
                    //.resize(280, 280)                        // optional
                    //.rotate(90)                             // optional
                    .into(holder.image);
*/

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

}