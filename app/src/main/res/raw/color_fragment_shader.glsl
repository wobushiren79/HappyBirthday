precision mediump float;
uniform vec4 Color;

varying vec4 vAmbient;           //环境光信息
varying vec4 vDiffuse;           //漫反射信息
varying vec4 vSpecular;          //镜面反射信息
void main(){
gl_FragColor=Color*vAmbient+Color*vDiffuse+Color*vSpecular;
}