package com.example.apple.happybirthday.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by apple on 2016/3/31.
 */
public class RawReadHelper {
    public static String readRaw(Context context, int id) {
        StringBuffer body = new StringBuffer();
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().openRawResource(id);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String nextline;
            while ((nextline = reader.readLine()) != null) {
                body.append(nextline);
                body.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return body.toString();
    }
}
