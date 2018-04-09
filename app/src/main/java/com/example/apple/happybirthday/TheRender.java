package com.example.apple.happybirthday;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.example.apple.happybirthday.data.body.HuaBian;
import com.example.apple.happybirthday.data.body.QiuBing;
import com.example.apple.happybirthday.data.body.QiuHuan;
import com.example.apple.happybirthday.data.body.XiaoQ;
import com.example.apple.happybirthday.data.body.XiaoYe;
import com.example.apple.happybirthday.data.body.Xingxing1;
import com.example.apple.happybirthday.data.body.Xingxing2;
import com.example.apple.happybirthday.data.body.Xingxing3;
import com.example.apple.happybirthday.data.body.ZhiJia;
import com.example.apple.happybirthday.data.body.ZhuTi;
import com.example.apple.happybirthday.data.brithday.BirthdayA;
import com.example.apple.happybirthday.data.brithday.BirthdayB;
import com.example.apple.happybirthday.data.brithday.BirthdayD;
import com.example.apple.happybirthday.data.brithday.BirthdayH;
import com.example.apple.happybirthday.data.brithday.BirthdayI;
import com.example.apple.happybirthday.data.brithday.BirthdayR;
import com.example.apple.happybirthday.data.brithday.BirthdayT;
import com.example.apple.happybirthday.data.brithday.BirthdayY;
import com.example.apple.happybirthday.data.happy.HappyA;
import com.example.apple.happybirthday.data.happy.HappyH;
import com.example.apple.happybirthday.data.happy.HappyP;
import com.example.apple.happybirthday.data.happy.HappyP2;
import com.example.apple.happybirthday.data.happy.HappyY;
import com.example.apple.happybirthday.program.ColorShaderProgram;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.*;
import static android.opengl.Matrix.*;

/**
 * Created by apple on 2016/3/31.
 */
public class TheRender implements GLSurfaceView.Renderer {
    private Context context;
    private float[] projectMatrix = new float[16];
    private float[] eyeMatrix = new float[16];
    private float[] itemMatrix = new float[16];

    private float[] eyeLocation = {
            0f, 12f, 17f
    };

    private float[] lightLocation = {
            100f, 100f, 10f
    };

    ZhuTi zhuTi;
    HuaBian huaBian;
//    QiuBing qiuBing1;

    ZhiJia zhiJia1;
    ZhiJia zhiJia2;
    ZhiJia zhiJia3;
    ZhiJia zhiJia4;

    XiaoQ xiaoQ;
    XiaoYe xiaoYe;
    QiuHuan qiuHuan;

    HappyH happyH;
    HappyA happyA;
    HappyP happyP;
    HappyP2 happyP2;
    HappyY happyY;

    BirthdayA birthdayA;
    BirthdayB birthdayB;
    BirthdayI birthdayI;
    BirthdayR birthdayR;
    BirthdayT birthdayT;
    BirthdayH birthdayH;
    BirthdayD birthdayD;
    BirthdayY birthdayY;

    Xingxing1 xingxing1;
    Xingxing2 xingxing2;
    Xingxing3 xingxing3;

    ColorShaderProgram shaderProgram;


    public TheRender(Context context) {
        this.context = context;
    }


    public void setXY(float x, float y) {
        eyex -= x;
    }

    float al = 0;

    public void start() {
        start = true;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.v("this", "onSurfaceCreated");
        glClearColor(0, 0, 0, 1);
        glEnable(GL_DEPTH_TEST);

        zhuTi = new ZhuTi(context);
        huaBian = new HuaBian(context);

//        qiuBing1 = new QiuBing(context);

        zhiJia1 = new ZhiJia(context);
        zhiJia2 = new ZhiJia(context);
        zhiJia3 = new ZhiJia(context);
        zhiJia4 = new ZhiJia(context);

//        xiaoQ = new XiaoQ(context);
        xiaoYe = new XiaoYe(context);

        qiuHuan = new QiuHuan(context);

        happyH = new HappyH(context);
        happyA = new HappyA(context);
        happyP = new HappyP(context);
        happyP2 = new HappyP2(context);
        happyY = new HappyY(context);

        birthdayB = new BirthdayB(context);
        birthdayI = new BirthdayI(context);
        birthdayR = new BirthdayR(context);
        birthdayT = new BirthdayT(context);
        birthdayH = new BirthdayH(context);
        birthdayD = new BirthdayD(context);
        birthdayA = new BirthdayA(context);
        birthdayY = new BirthdayY(context);

        xingxing1 = new Xingxing1(context);
        xingxing2 = new Xingxing2(context);
        xingxing3 = new Xingxing3(context);

        shaderProgram = new ColorShaderProgram(context, R.raw.color_vertex_shader, R.raw.color_fragment_shader);
        Log.v("this", "onSurfaceCreated over");
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
        perspectiveM(projectMatrix, 0, 45, (float) width / (float) height, 1f, 100f);
        setLookAtM(eyeMatrix, 0, eyeLocation[0], eyeLocation[1], eyeLocation[2], 0f, 0f, 0f, 0f, 1f, 0f);
        Log.v("this", "onSurfaceChanged over");
    }

    float eyex = 0;
    float eyey = 0;
    boolean flightx = true;
    float ligthx = 100f;

    boolean flightz = true;
    float ligthz = 10f;

    boolean start = false;

    @Override
    public void onDrawFrame(GL10 gl) {
        setLight();
        if (start) {
            eyex += 0.1f;

        }

        glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);


        multiplyMM(itemMatrix, 0, projectMatrix, 0, eyeMatrix, 0);

        shaderProgram.useProgram();

        float[] temp = new float[16];
        //主体的控制
        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] zhutitemp = new float[16];
        multiplyMM(zhutitemp, 0, itemMatrix, 0, temp, 0);
        float[] zhuticolor = {0.478f, 0.408f, 0.635f, 1f};
        shaderProgram.setUniform(zhutitemp, eyeLocation, lightLocation, zhuticolor);
        zhuTi.bindData(shaderProgram);
        zhuTi.ondraw();


        //花边的控制
        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] huabiantemp = new float[16];
        multiplyMM(huabiantemp, 0, itemMatrix, 0, temp, 0);
        float[] huabiancolor = {0.914f, 0.686f, 0.733f, 1f};
        shaderProgram.setUniform(huabiantemp, eyeLocation, lightLocation, huabiancolor);
        huaBian.bindData(shaderProgram);
        huaBian.ondraw();

        //球饼的控制
//        setIdentityM(temp, 0);
//        rotateM(temp, 0, eyex, 0f, 1f, 0f);
//        float[] qiubing1temp = new float[16];
//        multiplyMM(qiubing1temp, 0, itemMatrix, 0, temp, 0);
//        float[] qiubing1color = {0.5f, 1f, 1f, 1f};
//        shaderProgram.setUniform(qiubing1temp, eyeLocation, lightLocation, qiubing1color);
//        qiuBing1.bindData(shaderProgram);
//        qiuBing1.ondraw();

        //支架设置
        setZhiJia(temp);

        //Happy字体的控制
        setHappy(temp);

        //Birthday字体的控制
        setBirthday(temp);

        //星星的控制
        setStar(temp);

//        //小Q的控制
//        setIdentityM(temp, 0);
//        rotateM(temp, 0, eyex, 0f, 1f, 0f);
//        float[] xiaoqtemp = new float[16];
//        multiplyMM(xiaoqtemp, 0, itemMatrix, 0, temp, 0);
//        float[] xiaoqcolor = {0.969f, 0.094f, 0f, 1f};
//        shaderProgram.setUniform(xiaoqtemp, eyeLocation, lightLocation, xiaoqcolor);
//        xiaoQ.bindData(shaderProgram);
//        xiaoQ.ondraw();


        //小业的控制
        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] xiaotemp = new float[16];
        multiplyMM(xiaotemp, 0, itemMatrix, 0, temp, 0);
        float[] xiaoyecolor = {0.969f, 0.094f, 0f, 1f};
        shaderProgram.setUniform(xiaotemp, eyeLocation, lightLocation, xiaoyecolor);
        xiaoYe.bindData(shaderProgram);
        xiaoYe.ondraw();

        //球环的控制
        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] qiuhuantemp = new float[16];
        multiplyMM(qiuhuantemp, 0, itemMatrix, 0, temp, 0);
        float[] qiuhuancolor = {0.776f, 0.549f, 0f, 1f};
        shaderProgram.setUniform(qiuhuantemp, eyeLocation, lightLocation, qiuhuancolor);
        qiuHuan.bindData(shaderProgram);
        qiuHuan.ondraw();
    }

    private void setStar(float[] temp) {
        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] xingxing1temp = new float[16];
        multiplyMM(xingxing1temp, 0, itemMatrix, 0, temp, 0);
        float[] xingxing1color = {0.717f, 0.816f, 0.423f, 1f};
        shaderProgram.setUniform(xingxing1temp, eyeLocation, lightLocation, xingxing1color);
        xingxing1.bindData(shaderProgram);
        xingxing1.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] xingxing2temp = new float[16];
        multiplyMM(xingxing2temp, 0, itemMatrix, 0, temp, 0);
        float[] xingxing2color = {0.914f, 0.686f, 0.733f, 1f};
        shaderProgram.setUniform(xingxing2temp, eyeLocation, lightLocation, xingxing2color);
        xingxing2.bindData(shaderProgram);
        xingxing2.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] xingxing3temp = new float[16];
        multiplyMM(xingxing3temp, 0, itemMatrix, 0, temp, 0);
        float[] xingxing3color = {0.776f, 0.549f, 0f, 1f};
        shaderProgram.setUniform(xingxing3temp, eyeLocation, lightLocation, xingxing3color);
        xingxing3.bindData(shaderProgram);
        xingxing3.ondraw();
    }

    private void setLight() {
        if (ligthx >= 100 && flightx) {
            flightx = false;
        } else if (ligthx <= -100 && !flightx) {
            flightx = true;
        }
        if (!flightx) {
            ligthx--;
        } else {
            ligthx++;
        }


        if (ligthz >= 100 && flightz) {
            flightz = false;
        } else if (ligthz <= -100 && !flightz) {
            flightz = true;
        }
        if (!flightz) {
            ligthz--;
        } else {
            ligthz++;
        }
        lightLocation = new float[]{
                ligthx, 100f, ligthz
        };
    }

    private void setBirthday(float[] temp) {
        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] birthdayBtemp = new float[16];
        multiplyMM(birthdayBtemp, 0, itemMatrix, 0, temp, 0);
        float[] birthdayBcolor = {0.717f, 0.816f, 0.423f, 1f};
        shaderProgram.setUniform(birthdayBtemp, eyeLocation, lightLocation, birthdayBcolor);
        birthdayB.bindData(shaderProgram);
        birthdayB.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] birthdayItemp = new float[16];
        multiplyMM(birthdayItemp, 0, itemMatrix, 0, temp, 0);
        float[] birthdayIcolor = {0.776f, 0.549f, 0f, 1f};
        shaderProgram.setUniform(birthdayItemp, eyeLocation, lightLocation, birthdayIcolor);
        birthdayI.bindData(shaderProgram);
        birthdayI.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] birthdayRtemp = new float[16];
        multiplyMM(birthdayRtemp, 0, itemMatrix, 0, temp, 0);
        float[] birthdayRcolor = {0.717f, 0.816f, 0.423f, 1f};
        shaderProgram.setUniform(birthdayRtemp, eyeLocation, lightLocation, birthdayRcolor);
        birthdayR.bindData(shaderProgram);
        birthdayR.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] birthdayTtemp = new float[16];
        multiplyMM(birthdayTtemp, 0, itemMatrix, 0, temp, 0);
        float[] birthdayTcolor = {0.776f, 0.549f, 0f, 1f};
        shaderProgram.setUniform(birthdayTtemp, eyeLocation, lightLocation, birthdayTcolor);
        birthdayT.bindData(shaderProgram);
        birthdayT.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] birthdayHtemp = new float[16];
        multiplyMM(birthdayHtemp, 0, itemMatrix, 0, temp, 0);
        float[] birthdayHcolor = {0.717f, 0.816f, 0.423f, 1f};
        shaderProgram.setUniform(birthdayHtemp, eyeLocation, lightLocation, birthdayHcolor);
        birthdayH.bindData(shaderProgram);
        birthdayH.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] birthdayDtemp = new float[16];
        multiplyMM(birthdayDtemp, 0, itemMatrix, 0, temp, 0);
        float[] birthdayDcolor = {0.914f, 0.686f, 0.733f, 1f};
        shaderProgram.setUniform(birthdayDtemp, eyeLocation, lightLocation, birthdayDcolor);
        birthdayD.bindData(shaderProgram);
        birthdayD.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] birthdayAtemp = new float[16];
        multiplyMM(birthdayAtemp, 0, itemMatrix, 0, temp, 0);
        float[] birthdayAcolor = {0.717f, 0.816f, 0.423f, 1f};
        shaderProgram.setUniform(birthdayAtemp, eyeLocation, lightLocation, birthdayAcolor);
        birthdayA.bindData(shaderProgram);
        birthdayA.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] birthdayYtemp = new float[16];
        multiplyMM(birthdayYtemp, 0, itemMatrix, 0, temp, 0);
        float[] birthdayYcolor = {0.776f, 0.549f, 0f, 1f};
        shaderProgram.setUniform(birthdayYtemp, eyeLocation, lightLocation, birthdayYcolor);
        birthdayY.bindData(shaderProgram);
        birthdayY.ondraw();
    }

    private void setHappy(float[] temp) {
        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] happyHtemp = new float[16];
        multiplyMM(happyHtemp, 0, itemMatrix, 0, temp, 0);
        float[] happyHcolor = {0.717f, 0.816f, 0.423f, 1f};
        shaderProgram.setUniform(happyHtemp, eyeLocation, lightLocation, happyHcolor);
        happyH.bindData(shaderProgram);
        happyH.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] happyAtemp = new float[16];
        multiplyMM(happyAtemp, 0, itemMatrix, 0, temp, 0);
        float[] happyAcolor = {0.776f, 0.549f, 0f, 1f};
        shaderProgram.setUniform(happyAtemp, eyeLocation, lightLocation, happyAcolor);
        happyA.bindData(shaderProgram);
        happyA.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] happyPtemp = new float[16];
        multiplyMM(happyPtemp, 0, itemMatrix, 0, temp, 0);
        float[] happyPcolor = {0.914f, 0.686f, 0.733f, 1f};
        shaderProgram.setUniform(happyPtemp, eyeLocation, lightLocation, happyPcolor);
        happyP.bindData(shaderProgram);
        happyP.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] happyP2temp = new float[16];
        multiplyMM(happyP2temp, 0, itemMatrix, 0, temp, 0);
        float[] happyP2color = {0.776f, 0.549f, 0f, 1f};
        shaderProgram.setUniform(happyP2temp, eyeLocation, lightLocation, happyP2color);
        happyP2.bindData(shaderProgram);
        happyP2.ondraw();

        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] happyYtemp = new float[16];
        multiplyMM(happyYtemp, 0, itemMatrix, 0, temp, 0);
        float[] happyYcolor = {0.914f, 0.686f, 0.733f, 1f};
        shaderProgram.setUniform(happyYtemp, eyeLocation, lightLocation, happyYcolor);
        happyY.bindData(shaderProgram);
        happyY.ondraw();
    }

    private void setZhiJia(float[] temp) {
        //支架1的控制
        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex, 0f, 1f, 0f);
        float[] zhijia1temp = new float[16];
        multiplyMM(zhijia1temp, 0, itemMatrix, 0, temp, 0);
        float[] zhijia1color = {0.914f, 0.686f, 0.733f, 1f};
        shaderProgram.setUniform(zhijia1temp, eyeLocation, lightLocation, zhijia1color);
        zhiJia1.bindData(shaderProgram);
        zhiJia1.ondraw();

        //支架2的控制
        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex + 25, 0f, 1f, 0f);
        float[] zhijia2temp = new float[16];
        multiplyMM(zhijia2temp, 0, itemMatrix, 0, temp, 0);
        float[] zhijia2color = {0.776f, 0.549f, 0f, 1f};
        shaderProgram.setUniform(zhijia2temp, eyeLocation, lightLocation, zhijia2color);
        zhiJia2.bindData(shaderProgram);
        zhiJia2.ondraw();

        //支架3的控制
        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex + 50, 0f, 1f, 0f);
        float[] zhijia3temp = new float[16];
        multiplyMM(zhijia3temp, 0, itemMatrix, 0, temp, 0);
        float[] zhijia3color = {0.717f, 0.816f, 0.423f, 1f};
        shaderProgram.setUniform(zhijia3temp, eyeLocation, lightLocation, zhijia3color);
        zhiJia3.bindData(shaderProgram);
        zhiJia3.ondraw();

        //支架4的控制
        setIdentityM(temp, 0);
        rotateM(temp, 0, eyex + 75, 0f, 1f, 0f);
        float[] zhijia4temp = new float[16];
        multiplyMM(zhijia4temp, 0, itemMatrix, 0, temp, 0);
        float[] zhijia4color = {0.729f, 0.259f, 0.208f, 1f};
        shaderProgram.setUniform(zhijia4temp, eyeLocation, lightLocation, zhijia4color);
        zhiJia4.bindData(shaderProgram);
        zhiJia4.ondraw();
    }
}
