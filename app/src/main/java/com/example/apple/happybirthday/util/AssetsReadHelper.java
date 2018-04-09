package com.example.apple.happybirthday.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2016/4/1.
 */
public class AssetsReadHelper {
    public static float[] readAssetsVertex(Context context, String fileName) {
        InputStream inputStream = null;

        List<Float> listVertexAll=new ArrayList<>();
        List<Float> listVertex=new ArrayList<>();

        float[] vXYZ = new float[0];
        try {
            inputStream = context.getResources().getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String nextline;
            while ((nextline = reader.readLine()) != null) {
                String[] temp = nextline.split("[ ]+");
                if(temp[0].trim().equals("v")){
                    listVertexAll.add(Float.parseFloat(temp[1])/10f);
                    listVertexAll.add(Float.parseFloat(temp[2])/10f);
                    listVertexAll.add(Float.parseFloat(temp[3])/10f);
                }
                else if(temp[0].trim().equals("f")){

                    int[] index=new int[3];
                    index[0]=Integer.parseInt(temp[1])-1;
                    index[1]=Integer.parseInt(temp[2])-1;
                    index[2]=Integer.parseInt(temp[3])-1;

                    float x0=listVertexAll.get(3*index[0]);
                    float y0=listVertexAll.get(3*index[0]+1);
                    float z0=listVertexAll.get(3*index[0]+2);
                    listVertex.add(x0);
                    listVertex.add(y0);
                    listVertex.add(z0);

                    float x1=listVertexAll.get(3*index[1]);
                    float y1=listVertexAll.get(3*index[1]+1);
                    float z1=listVertexAll.get(3*index[1]+2);
                    listVertex.add(x1);
                    listVertex.add(y1);
                    listVertex.add(z1);

                    float x2=listVertexAll.get(3*index[2]);
                    float y2=listVertexAll.get(3*index[2]+1);
                    float z2=listVertexAll.get(3*index[2]+2);
                    listVertex.add(x2);
                    listVertex.add(y2);
                    listVertex.add(z2);

                    int size=listVertex.size();
                    vXYZ=new float[size];
                    for(int i=0;i<size;i++)
                    {
                        vXYZ[i]=listVertex.get(i);
                    }
                }

            }
        } catch (IOException e) {
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

        return vXYZ;

    }
}
