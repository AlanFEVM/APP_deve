package com.example.learningapp.CustomViews;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;


public class MultiHintEditText extends androidx.appcompat.widget.AppCompatEditText {
    CharSequence defaulthint;
    int current_index;
    HashMap<Integer, String> Hints = new HashMap<Integer, String>();

    public void setUseOtherHint(boolean useOtherHint) {
        this.useOtherHint = useOtherHint;
    }

    private boolean useOtherHint;

    public void add_hint(int Index,String hint){
        Hints.put(Index,hint);
    }

    public void set_my_hint(int index){
        current_index = index;
    }

    public MultiHintEditText(@NonNull Context context) {
        super(context);
        defaulthint = this.getHint();
    }

    public MultiHintEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        defaulthint = this.getHint();
    }

    public MultiHintEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        defaulthint = this.getHint();
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if(useOtherHint){
            this.setHint(focused ? "" : Hints.get(current_index));
        }else{
            this.setHint(focused ? "" : defaulthint);
        }
    }
    public void refresh_hint() {
        this.setHint(Hints.get(current_index));
    }
}
