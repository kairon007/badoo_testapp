package com.example.kairo.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kairo.testapp.adapters.MyRecyclerViewAdapter;
import com.example.kairo.testapp.models.DividerItemDecoration;
import com.example.kairo.testapp.models.Videoz;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements
        SearchView.OnQueryTextListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "RecyclerViewActivity";
    List<Videoz.VideosBean> mTotalList = new ArrayList<>();
    public SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSearchView = (SearchView) findViewById(R.id.action_search);

        setupList();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Welcome", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
    }

    private List<Videoz.VideosBean> filter(List<Videoz.VideosBean> models, String query) {
        query = query.toLowerCase();

        final List<Videoz.VideosBean> filteredModelList = new ArrayList<>();
        for (Videoz.VideosBean model : models) {
            String number = "";
            String video = "";
            if(model.getNombre()!=null){
                number = model.getNombre().toLowerCase();
            }
            if(model.getVideo()!=null){
                video = model.getVideo().toLowerCase();
            }

            if (video.contains(query)||number.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        final List<Videoz.VideosBean> filteredModelList = filter(mTotalList, newText);
        mRecyclerView.setAdapter(new MyRecyclerViewAdapter(filteredModelList));
        mRecyclerView.scrollToPosition(0);
        return true;
       /* if (TextUtils.isEmpty(newText.toString())) {
          //  mRecyclerView.cle
        } else {
           // mListView.setFilterText(newText.toString());
        }
        return true;*/
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    public void setupList() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        InputStream inputStream = this.getResources().openRawResource(R.raw.amit);
        String jsonString = readJsonFile(inputStream);

        Gson gson = new Gson();
        Videoz mVideos = gson.fromJson(jsonString, Videoz.class);
        mTotalList = mVideos.getVideos();
        mAdapter = new MyRecyclerViewAdapter(mVideos.getVideos());
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        setupSearchView();
        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
                startActivity(new Intent(getApplicationContext(),YouPlayer.class));
            }
        });
    }

    public void parseJson() {
        //Reading source from local file
        InputStream inputStream = this.getResources().openRawResource(R.raw.amit);
        String jsonString = readJsonFile(inputStream);

        Gson gson = new Gson();
        Videoz mVideos = gson.fromJson(jsonString, Videoz.class);

    }

    private String readJsonFile(InputStream inputStream) {
// TODO Auto-generated method stub
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte bufferByte[] = new byte[1024];
        int length;
        try {
            while ((length = inputStream.read(bufferByte)) != -1) {
                outputStream.write(bufferByte, 0, length);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


}
