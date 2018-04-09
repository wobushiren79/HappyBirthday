package com.example.apple.happybirthday.util;

import android.content.Context;
import android.util.Log;

import static android.opengl.GLES20.*;

/**
 * Created by apple on 2016/3/31.
 */
public class ShaderHelper {

    public static int getVertexID(String code) {
        return complieShader(GL_VERTEX_SHADER, code);
    }

    public static int getFragmentID(String code) {
        return complieShader(GL_FRAGMENT_SHADER, code);
    }

    private static int complieShader(int type, String code) {
        int shaderId = glCreateShader(type);
        if (shaderId == 0) {
            Log.v("this", "创建着色器失败");
            return 0;
        }
        glShaderSource(shaderId, code);
        glCompileShader(shaderId);
        int[] complieStates = new int[1];
        glGetShaderiv(shaderId, GL_COMPILE_STATUS, complieStates, 0);
        if (complieStates[0] == 0) {
            if (type == GL_VERTEX_SHADER) {
                Log.v("this", "编译顶点着色器代码失败");
                Log.v("this",glGetShaderInfoLog(shaderId));
            } else {
                Log.v("this", "编译片段着色器代码失败");
            }
            glDeleteShader(shaderId);
            return 0;
        }
        return shaderId;
    }

    public static int bulidProgram(Context context, int vertexRawID, int fragmentRawID) {
        String vertexCode = RawReadHelper.readRaw(context, vertexRawID);
        String fragmentCode = RawReadHelper.readRaw(context, fragmentRawID);

        int vertexID=getVertexID(vertexCode);
        int fragmentID=getFragmentID(fragmentCode);

        int program=glCreateProgram();
        if(program==0){
            Log.v("this","创建连接程序失败");
            return 0;
        }
        glAttachShader(program, vertexID);
        glAttachShader(program, fragmentID);
        glLinkProgram(program);
        int[] linkStates=new int[1];
        glGetProgramiv(program,GL_LINK_STATUS,linkStates,0);
        if(linkStates[0]==0){
            Log.v("this","连接着色器程序失败");
            glDeleteProgram(program);
            return 0;
        }
        return program;
    }
}
