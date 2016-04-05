package com.example.kairo.testapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "RecyclerViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
setupList();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
public void setupList(){
    mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
    mRecyclerView.setHasFixedSize(true);
    mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);
    InputStream inputStream = this.getResources().openRawResource(R.raw.amit);
    String jsonString = readJsonFile(inputStream);

    Gson gson = new Gson();
    Videoz mVideos = gson.fromJson(jsonString, Videoz.class);
    mAdapter = new MyRecyclerViewAdapter(mVideos.getVideos());
    mRecyclerView.setAdapter(mAdapter);
    RecyclerView.ItemDecoration itemDecoration =
            new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
    mRecyclerView.addItemDecoration(itemDecoration);

    // Code to Add an item with default animation
    //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

    // Code to remove an item with default animation
    //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
}
    public void parseJson(){
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
    @Override
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
    }
}
