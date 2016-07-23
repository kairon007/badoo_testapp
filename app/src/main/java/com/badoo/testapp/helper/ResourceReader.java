package com.badoo.testapp.helper;

import android.content.res.Resources;

import java.io.InputStream;

/**
 * Convenience class to keep resource reading code DRY
 */
public class ResourceReader {

    /**
     * Reads an android raw resource
     * @param res
     *      App Resources
     * @param resid
     *      Resource to be read
     * @return
     *      String of the file contents
     */
    public static String readResource(Resources res, @android.support.annotation.RawRes int resid){
        String data = "";
        try {
            InputStream inputStream = res.openRawResource(resid);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            data = new String(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
