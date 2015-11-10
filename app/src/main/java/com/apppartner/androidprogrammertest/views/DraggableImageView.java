package com.apppartner.androidprogrammertest.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.apppartner.androidprogrammertest.R;

/**
 * Created by vipulmittal on 06/11/15.
 */
public class DraggableImageView extends View {
    private static final int INVALID_POINTER_ID = -1;
    private final GestureDetector gestures;
    private Matrix translate;
    private Bitmap droid;
    private boolean firstDraw = true;
    private float x = 0;
    private float y = 0;
    private float fX, fY, sX, sY;
    private int ptrID1, ptrID2;
    private float mAngle;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int mHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        if (x == 0) {
            x = mWidth / 2;
            y += mHeight;
            setInitialPosition(x, y);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    public DraggableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        translate = new Matrix();
        gestures = new GestureDetector(getContext(), new TouchGestureDetector());
        droid = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_apppartner);
        droid = Bitmap.createScaledBitmap(droid, 2*droid.getWidth() / 3, 2*droid.getHeight() / 3, true);
    }

    public DraggableImageView(Context context) {
        this(context, null);
    }

    public void onMove(float dx, float dy) {
        translate.postTranslate(dx, dy);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                ptrID1 = event.getPointerId(event.getActionIndex());
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                ptrID2 = event.getPointerId(event.getActionIndex());
                sX = event.getX(event.findPointerIndex(ptrID1));
                sY = event.getY(event.findPointerIndex(ptrID1));
                fX = event.getX(event.findPointerIndex(ptrID2));
                fY = event.getY(event.findPointerIndex(ptrID2));
                break;
            case MotionEvent.ACTION_MOVE:
                if (ptrID1 != INVALID_POINTER_ID && ptrID2 != INVALID_POINTER_ID) {
                    float nfX, nfY, nsX, nsY;
                    nsX = event.getX(event.findPointerIndex(ptrID1));
                    nsY = event.getY(event.findPointerIndex(ptrID1));
                    nfX = event.getX(event.findPointerIndex(ptrID2));
                    nfY = event.getY(event.findPointerIndex(ptrID2));

                    mAngle = angleBetweenLines(fX, fY, sX, sY, nfX, nfY, nsX, nsY);
                    translate.postRotate(-1 * mAngle, (fX + sX) / 2, (fY + sY) / 2);

                }
                break;
            case MotionEvent.ACTION_UP:
                ptrID1 = INVALID_POINTER_ID;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                ptrID2 = INVALID_POINTER_ID;
                break;
            case MotionEvent.ACTION_CANCEL:
                ptrID1 = INVALID_POINTER_ID;
                ptrID2 = INVALID_POINTER_ID;
                break;
        }
        return gestures.onTouchEvent(event);
    }

    private float angleBetweenLines(float fX, float fY, float sX, float sY, float nfX, float nfY, float nsX, float nsY) {
        float angle1 = (float) Math.atan2((fY - sY), (fX - sX));
        float angle2 = (float) Math.atan2((nfY - nsY), (nfX - nsX));

        float angle = ((float) Math.toDegrees(angle1 - angle2)) % 360;
        if (angle < -180.f) angle += 360.0f;
        if (angle > 180.f) angle -= 360.0f;
        return angle;
    }

    protected void onDraw(Canvas canvas) {
        if (firstDraw) {
            firstDraw = false;
            this.onResetLocation();
        } else {
            canvas.drawBitmap(droid, translate, null);
            Matrix m = canvas.getMatrix();
        }
    }

    public void onResetLocation() {
        translate.reset();
        translate.postTranslate(x, y);
        invalidate();
    }


    public void setInitialPosition(float x, float y) {
        this.x = x - droid.getWidth() / 2;
        this.y = y - droid.getHeight();
        this.onResetLocation();
    }

    public void setBottomOffset(int v) {
        y -= v;
    }

    class TouchGestureDetector implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            DraggableImageView.this.onResetLocation();
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            DraggableImageView.this.onResetLocation();
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            DraggableImageView.this.onMove(-distanceX, -distanceY);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            return true;
        }

    }

}