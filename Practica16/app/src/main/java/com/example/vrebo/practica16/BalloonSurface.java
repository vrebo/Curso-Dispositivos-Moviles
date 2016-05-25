package com.example.vrebo.practica16;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by VREBO on 24/05/2016.
 */
public class BalloonSurface extends SurfaceView implements SurfaceHolder.Callback {

    private float cX;
    private float cY;
    private int width;
    private int height;
    private int radius = 70;
    private Balloon balloon;
    private Paint paint;
    private boolean firstDraw;
    private boolean moving;
    private SurfaceThread thread;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (!moving) {
                return;
            }
            float x = event.values[0];
            float y = event.values[1];

            int dX = (int) (balloon.getcX() - x);
            int dY = (int) (balloon.getcY() + y);
            int radius = balloon.getRadius();

            if (dX - radius > 0 && dX + radius < width) {
                balloon.setcX(dX);
            }
            if (dY - radius > 0 && dY + radius < height) {
                balloon.setcY(dY);
            }

            Log.d("Sensor  Changed", "...");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public BalloonSurface(Context context) {
        super(context);
        init();
    }

    public BalloonSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BalloonSurface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        firstDraw = true;
        moving = true;
        balloon = new Balloon();

        getHolder().addCallback(this);
        paint = new Paint();
        paint.setColor(balloon.getColor());


        String service_name = Context.SENSOR_SERVICE;
        SensorManager sensorManager = (SensorManager) getContext().getSystemService(service_name);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener,
                sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        if (firstDraw) {
            width = canvas.getWidth();
            height = canvas.getHeight();
            balloon.setcX(width / 2);
            balloon.setcY(height / 2);
            firstDraw = false;
        }
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(balloon.getcX(), balloon.getcY(), balloon.getRadius(), paint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new SurfaceThread(this);
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private int getDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                if (getDistance(balloon.getcX(), balloon.getcY(), (int) event.getX(), (int) event.getY()) < balloon.getRadius()) {
                    thread.setRunning(moving = false);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int pX = (int)event.getX();
                int pY = (int)event.getY();
                if (getDistance(balloon.getcX(), balloon.getcY(), pX, pY) < balloon.getRadius()) {
                    balloon.setcX(pX);
                    balloon.setcY(pY);
                    Canvas canvas = null;
                    try {
                        canvas = getHolder().lockCanvas();
                        synchronized (getHolder()) {
                            onDraw(canvas);
                        }
                    } finally {
                        if (canvas != null) {
                            getHolder().unlockCanvasAndPost(canvas);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d("Touch Event", "Action Up");
                thread = new SurfaceThread(this);
                thread.setRunning(moving = true);
                thread.start();
                break;
        }

        return true;
    }
}
