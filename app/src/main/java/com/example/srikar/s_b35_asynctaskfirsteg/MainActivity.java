package com.example.srikar.s_b35_asynctaskfirsteg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.srikar.s_b35_asynctaskfirsteg.R.id.imageView;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolbar;
    ProgressDialog pDialog;
    Button button;
  public static MyTask mytask;
    //DECLARE RELEVANT VARIABLES
    static ArrayList<Images> arrayList;//FOR STORING CONTACT OBJECTS COMING FROM SERVER
    RecyclerView recyclerView;//FOR DISPLAYING CONTACT INFORMATIOM COMING FROM SERVER JSON
    MyAdapter myAdapter;
    MyDataBase myDataBase;


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = getLayoutInflater().inflate(R.layout.album_card,parent,false);

            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            final Images c = arrayList.get(position);

            //holder.tv1.setText(c.getSno());

         //   holder.tv2.setText(c.getName());

           // holder.tv3.setText(c.getEmail());

          //  holder.tv4.setText(c.getMobile());

           // Glide.with(MainActivity.this).load(c.getImageUrl()).placeholder(R.mipmap.ic_launcher).crossFade().into(holder.image);
            Glide.with(MainActivity.this).
                    load(c.getImageUrl()).
                    placeholder(R.mipmap.ic_launcher).
                    crossFade().centerCrop().
                    into(holder.image);


            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent in = new Intent(MainActivity.this, FriensPhotosActivity.class);
                    in.putExtra("getName", c.getName());
                    startActivity(in);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/MavenPro-Regular" + ".ttf");
        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(typeface);


        myDataBase = new MyDataBase(this);
        myDataBase.open();

       // button = (Button) findViewById(R.id.hit);
        mytask = new MyTask();
   //------------------------------------------------------------------------------------------------------
        arrayList= new ArrayList<Images>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);

        myAdapter = new MyAdapter();


        RecyclerView recyclerView1=(RecyclerView)findViewById(R.id.list);
        //  recyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
       /* LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);*/
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        RecyclerAdapter adapter=new RecyclerAdapter(MainActivity.this);
        recyclerView1.setAdapter(adapter);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);

        // recyclerView.setLayoutManager(linearLayoutManager);

        if (checkForInternet()==false){
            Toast.makeText(MainActivity.this, "no internet", Toast.LENGTH_SHORT).show();
            return;
        }else {
           // mytask.execute("https://api.myjson.com/bins/crnez");
           // mytask.execute("http://www.json-generator.com/api/json/get/clklvqxjuG?indent=2");
           // mytask.execute("https://quarkbackend.com/getfile/suneeltamada22/suneel");
            mytask.execute("https://quarkbackend.com/getfile/suneeltamada22/4-2bavanapadu");

        }



  //-------------------------------------------------------------------------------------------------------------
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(false);

              //  mytask.execute("http://www.json-generator.com/api/json/get/cezmOJkMJe?indent=2");
               // mytask.execute("http://www.json-generator.com/api/json/get/cfxImsQHyq?indent=2");
               // mytask.execute("http://www.json-generator.com/api/json/get/ceEsYckBvm?indent=2");
            }
        });*/


    }
    public boolean checkForInternet(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (connectivityManager!=null){
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();

            if(info!=null && info.isConnected()==true){
                return true;
            }
        }
        return false;
    }

    public class MyTask extends AsyncTask<String,String,String>{
//------------------------------------------------------------------------------------------------------------
       /* URL url;                          //THIS CLASS IS USED TO STORE SERVER URL ,WHICH WE WANT TO CONNECT
        HttpURLConnection connection;     //THIS CLASS IS USED TO CONNECT TO SERVER
        InputStream inputStream;          //THIS CLASS IS USED TO READ DATA FROM SERVER  IN 1001(BITS/BYTES)
        InputStreamReader inputStreamReader;//THIS CLASS IS USED TO CONVERT BITS BYTES TO CHARACETERS
        BufferedReader bufferedReader;    //THIS CLASS IS USED TO READ DATA LINE BY LINE
        String line;                      //THIS IS USED TO READ EACH LINE FROM BUFFERED READER
        StringBuilder stringBuilder;      //THIS IS USED TO PILE UP LINES COMING FROM SERVER*/

//---------------------------------------------------------------------------------------------------------------

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
          //  pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {



            String s =  connectionLogic(params);
                return s;
        }



        @Override
        protected void onPostExecute(String s) {
            //textView.setText(""+s);
            //PARSE THE JSON FROM ANDROID.HIVE.COM

           // StringBuilder sb = new StringBuilder();
     //---------------------------------------------------------------------------------
            // myDataBase.deleteAll();
            parseJSon(s);




//-----------------------------------------------------------------------------------------
            super.onPostExecute(s);

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }


    }

/*
    public void getDataFRomDb(){

        Cursor c = myDataBase.getData();
        if (c!=null&&c.getCount()>0){
          //  arrayList.clear();
            c.moveToFirst();
            do {
                String id =c.getString(c.getColumnIndex("id"));
                String name = c.getString(c.getColumnIndex("name"));
                String email = c.getString(c.getColumnIndex("email"));
                String mobile = c.getString(c.getColumnIndex("mobile"));
                String image = c.getString(c.getColumnIndex("image")).trim();

                Contacts c1 = new Contacts(id,name,email,mobile,image);
                arrayList.add(c1);
                myAdapter.notifyDataSetChanged();

            }
            while (c.moveToNext());
            c.close();
            myDataBase.close();
        }

    }
*/
    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    public String connectionLogic(String[] baseUrl){

//-----------------------------------------------------------------------------------------------------------------
        try {
            //9.a Store Url in params[0]
            URL  url = new URL(baseUrl[0]);
            //9.b open connection
            HttpURLConnection  connection = (HttpURLConnection) url.openConnection();
            //9.c open input straem
            InputStream inputStream = connection.getInputStream();
            //d.now start reading --bits and bytes fron server--into reader--convert to characters
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            //e.collect the characters in line by  line fashion
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //f. using while loop,read each line from buffer Reader and append on String Biulder
            String line = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();

            while (line!=null){
                stringBuilder.append(line);
                line =bufferedReader.readLine();
            }
            return stringBuilder.toString();


//-------------------------------------------------------------------------------------------------------------------
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void parseJSon(String result){
        final String name = "suneel";

        try {
            JSONObject j = new JSONObject(result);
            JSONArray contacts = j.getJSONArray("AllImages");
            for (int i=0;i<contacts.length();i++){
                JSONObject k = contacts.getJSONObject(i);
                String image = k.getString("image").trim();
               // String name = k.getString("name").trim();

                Images images = new Images(image,name);

                arrayList.add(images);
                myAdapter.notifyDataSetChanged();

            }
            //myDataBase.close();
            pDialog.dismiss();
            //getDataFRomDb();

            //apply above text on textview

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}


