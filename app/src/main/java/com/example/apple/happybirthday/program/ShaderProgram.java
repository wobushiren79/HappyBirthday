package com.example.apple.happybirthday.program;

import android.content.Context;

import com.example.apple.happybirthday.util.ShaderHelper;

import static android.opengl.GLES20.*;

/**
 * Created by apple on 2016/3/31.
 */
public class ShaderProgram {
    protected int program;

    protected ShaderProgram(Context context, int vertexRawID, int fragmentRawID) {
        program = ShaderHelper.bulidProgram(context, vertexRawID, fragmentRawID);
    }

    public void useProgram() {
        glUseProgram(program);
    }

}
