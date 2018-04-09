package com.example.apple.happybirthday.data.brithday;

import android.content.Context;

import com.example.apple.happybirthday.data.VertexArrary;
import com.example.apple.happybirthday.program.ColorShaderProgram;
import com.example.apple.happybirthday.util.AssetsReadHelper;

import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by apple on 2016/4/3.
 */
public class BirthdayB {
    private VertexArrary vertexArrary;
    float[] data;

    public BirthdayB(Context context) {
        data = AssetsReadHelper.readAssetsVertex(context, "birthday/B.obj");
        vertexArrary = new VertexArrary(data);
    }

    public void bindData(ColorShaderProgram colorShaderProgram) {
        vertexArrary.setData(0, colorShaderProgram.getPositionLocation(), 3, 3 * 4);
        vertexArrary.setData(0, colorShaderProgram.getNormalLocation(), 3, 3 * 4);
    }

    public void ondraw() {
        glDrawArrays(GL_TRIANGLES, 0, data.length / 3);
    }
}
