package com.study.todayinformation.main.shenzhen;

import android.opengl.GLSurfaceView;

import com.study.opengl.OpenGlManager;
import com.study.todayinformation.R;
import com.study.todayinformation.base.BaseFragment;
import com.study.todayinformation.base.ViewInject;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import butterknife.BindView;

/**
 * @authour lxw
 * @function
 * @date 2019/9/19
 */
@ViewInject(mainLayoutId = R.layout.fragment_shenzhen)
public class ShenzhenFragment extends BaseFragment {
    @BindView(R.id.gl_surgace_view)
    GLSurfaceView glSurgaceView; //调用系统的OpenGl

    @Override
    public void afterBindView() {

        glSurgaceView.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                //为缓冲区 设置清除颜色的值  相当于初始化
                //直接调用openGL
//                gl.glClearColor(0.0f,0.0f,1.0f,1.0f);
                //调用C++
                OpenGlManager.onSurfaceCreated();
            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
                //设置 视图大小
                //直接调用openGL
//                gl.glViewport(0,0,width,height);
                //调用C++
                OpenGlManager.onSurfaceChanged(width,height);
            }
            //绘制的时候 每一帧 都会被系统调用 在android中 默认最高绘制效率 60帧/s
            @Override
            public void onDrawFrame(GL10 gl) {
                //设置色彩
                //直接调用openGL
//                gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
                //调用C++
                OpenGlManager.onDrawFrame();
            }
        });
    }
}
