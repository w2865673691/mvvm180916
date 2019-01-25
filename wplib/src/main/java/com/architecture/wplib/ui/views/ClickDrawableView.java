package com.architecture.wplib.ui.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.architecture.wplib.common.LogActs;

//com.architecture.wplib.ui.views.ClickDrawableView
public class ClickDrawableView extends AppCompatTextView {

    private GestureDetector gestureDetector;

    public ClickDrawableView(Context context) {
        super(context);

        init(context);
    }

    private void init(Context context) {
        gestureDetector = new GestureDetector(getContext(), new GestureListener());
//        setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                LogActs.d("event="+event);
//                GestureDetector gestureDetector = new GestureDetector(new GestureListener());
//                return gestureDetector.onTouchEvent(event);
//            }
//        });
    }


    public ClickDrawableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ClickDrawableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        MessureResult widthResult = getMessureSize(suggestedMinimumWidth, widthMeasureSpec);
        MessureResult heightResult = getMessureSize(suggestedMinimumHeight, heightMeasureSpec);

//        if(heightResult.specMode==MeasureSpec.EXACTLY&&widthResult.specMode==MeasureSpec.AT_MOST){
//            widthResult.result=widthResult.result
//        }

        setMeasuredDimension(widthResult.result, heightResult.result);
    }


    /**
     * 获取默认的宽高值
     */
    public MessureResult getMessureSize(int size, int measureSpec) {
        MessureResult messureResult = new MessureResult();
        messureResult.result = size;
        messureResult.specMode = MeasureSpec.getMode(measureSpec);
        messureResult.specSize = MeasureSpec.getSize(measureSpec);
        switch (messureResult.specMode) {
            case MeasureSpec.UNSPECIFIED: {
                messureResult.result = size;
                //   LogActs.d("UNSPECIFIED=" + messureResult.result);
            }
            break;
            case MeasureSpec.AT_MOST: {
                messureResult.result = messureResult.specSize;
                //  LogActs.d("AT_MOST=" + messureResult.result);
            }
            break;
            case MeasureSpec.EXACTLY: {
                messureResult.result = messureResult.specSize;
                //LogActs.d("EXACTLY=" + messureResult.result);
            }
            break;
        }

        return messureResult;
    }


    private class MessureResult {
        int result;
        int specMode;
        int specSize;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = gestureDetector.onTouchEvent(event);
        return result;
    }

    private class GestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            LogActs.d("");
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            StringBuilder stringBuilder = new StringBuilder("x=" + event.getX() + ";y=" + event.getY());
            Drawable background = getBackground();
            if(background!=null){
                stringBuilder.append("\ngetBounds=" + background.getBounds() );
                stringBuilder.append("\ngetMinimumWidth=" + background.getMinimumWidth()+ ";getMinimumHeight=" + background.getMinimumHeight() );
                stringBuilder.append("\ngetIntrinsicWidth=" + background.getIntrinsicWidth()+ ";getIntrinsicHeight=" + background.getIntrinsicHeight() );
            }

            stringBuilder.append("\nonSingleTapUp");
            setText(stringBuilder);
            LogActs.d("");

            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            LogActs.d("");
        }


        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            LogActs.d("");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            LogActs.d("");
            return false;
        }
    }
}
