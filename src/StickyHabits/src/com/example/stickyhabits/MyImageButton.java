package com.example.stickyhabits;

import android.content.Context;
import android.widget.ImageButton;

public class MyImageButton extends ImageButton {

    public MyImageButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private int mImageResource = 0;

    @Override
    public void setImageResource (int resId) {
        mImageResource = resId;
        super.setBackgroundResource(resId);
    }

    public int getImageResource() {
        return mImageResource;
    }
}