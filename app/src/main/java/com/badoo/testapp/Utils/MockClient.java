package com.badoo.testapp.Utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.util.Collections;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by kairo on 4/5/2016.
 */
public class MockClient implements Client {

    @Override
    public Response execute(Request request) throws IOException {
        Uri uri = Uri.parse(request.getUrl());

        Log.d("MOCK SERVER", "fetching uri: " + uri.toString());

        String responseString = "";

        if(uri.getPath().equals("/path/of/interest")) {
            responseString = "JSON STRING HERE";
        } else {
            responseString = "{\n" +
                    "\t\"videos\": [{\n" +
                    "\t\t\"video\": \"https://www.youtube.com/watch?v=aVS4W7GZSq0\",\n" +
                    "\t\t\"nombre\": \"Video 1\"\n" +
                    "\t}, {\n" +
                    "\t\t\"video\": \"https://www.youtube.com/watch?v=kPDnw3_1GOI\",\n" +
                    "\t\t\"nombre\": \"Video 2\"\n" +
                    "\t}, {\n" +
                    "\t\t\"video\": \"https://www.youtube.com/watch?v=dRjE1JwdDLI\",\n" +
                    "\t\t\"nombre\": \"Video 3\"\n" +
                    "\t}, {\n" +
                    "\t\t\"video\": \"https://vimeo.com/50713841\",\n" +
                    "\t\t\"nombre\": \"Video 4\"\n" +
                    "\t}, {\n" +
                    "\t\t\"video\": \"https://www.youtube.com/watch?v=Y4zfkeTsto0\",\n" +
                    "\t\t\"nombre\": \"Video 5\"\n" +
                    "\t}, {\n" +
                    "\t\t\" video\": \"https://www.youtube.com/watch?v=2lWJQmCf2CY&list=RDY4zfkeTsto0&index=2\",\n" +
                    "\t\t\"nombre\": \"Video 6\"\n" +
                    "\t}, {\n" +
                    "\t\t\"video\": \"https://www.youtube.com/watch?v=CZLEQKM1neE&list=RDY4zfkeTsto0&index=4\",\n" +
                    "\t\t\"nombre\": \"Video 7\"\n" +
                    "\t}],\n" +
                    "\t\"total\": 7\n" +
                    "}";
        }

        return new Response(request.getUrl(), 200, "nothing", Collections.EMPTY_LIST, new TypedByteArray("application/json", responseString.getBytes()));
    }
}