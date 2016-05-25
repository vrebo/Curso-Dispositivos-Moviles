package com.example.vrebo.practica16;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by VREBO on 25/05/2016.
 */
public class SurfaceThread extends Thread {

    private SurfaceHolder sh;
    private BalloonSurface surface;
    private boolean run;

    public SurfaceThread(BalloonSurface surfaceView) {
        surface = surfaceView;
        sh = surfaceView.getHolder();
        run = false;
    }

    public void setRunning(boolean run) {
        this.run = run;
    }

    @Override
    public void run() {
        Canvas canvas;

        while (run) {

            Log.d("Animando","...");
            canvas = null;
            try {
                canvas = sh.lockCanvas();
                synchronized (sh) {
                    surface.onDraw(canvas);
                }
            } finally {
                if (canvas != null) {
                    sh.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
