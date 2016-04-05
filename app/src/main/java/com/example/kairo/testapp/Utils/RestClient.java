package com.example.kairo.testapp.Utils;

/**
 * Created by kairo on 4/5/2016.
 */
public class RestClient {
    //You need to change the IP if you testing environment is not local machine
    //or you may have different from what we have here
    private static final String URL = "http://instinctcoder.com/wp-content/uploads/2015/08/";
    private retrofit.RestAdapter restAdapter;
 //   private StudentService studentService;

    public RestClient()
    {

        restAdapter = new retrofit.RestAdapter.Builder()
                .setClient(new MockClient())
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .build();

     //   studentService = restAdapter.create(StudentService.class);
    }


}