package com.example.apple.happybirthday.program;

import android.content.Context;

import static android.opengl.GLES20.*;

/**
 * Created by apple on 2016/3/31.
 */
public class ColorShaderProgram extends ShaderProgram {
    private int matrixLocation;
    private int positionLocation;
    private int cmeraLocation;
    private int lightLocation;
    private int normalLocation;
    private int colorLocation;
    private int uMMatrixLocation;

    public ColorShaderProgram(Context context, int vertexRawID, int fragmentRawID) {
        super(context, vertexRawID, fragmentRawID);

        matrixLocation = glGetUniformLocation(program, "Matrix");
        positionLocation = glGetAttribLocation(program, "Position");
        cmeraLocation = glGetUniformLocation(program, "uCamera");
        lightLocation = glGetUniformLocation(program, "uLightLocationSun");
        normalLocation = glGetAttribLocation(program, "aNormal");
        colorLocation = glGetUniformLocation(program, "Color");
        uMMatrixLocation = glGetUniformLocation(program, "uMMatrix");

    }

    public void setUniform(float[] matrix, float[] cmera, float[] light, float[] color) {
        glUniformMatrix4fv(matrixLocation, 1, false, matrix, 0);
        glUniformMatrix4fv(uMMatrixLocation, 1, false, matrix, 0);

        glUniform3fv(cmeraLocation, 1, cmera, 0);
        glUniform3fv(lightLocation, 1, light, 0);

        glUniform4fv(colorLocation, 1, color, 0);

    }

    public int getNormalLocation() {
        return normalLocation;
    }

    public int getPositionLocation() {
        return positionLocation;
    }
}
