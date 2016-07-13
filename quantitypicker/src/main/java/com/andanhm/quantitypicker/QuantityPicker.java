package com.andanhm.quantitypicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * QuantityPicker
 */
public class QuantityPicker extends LinearLayout {
    final String TAG = getClass().getSimpleName();
    private Context mContext;
    private ImageButton mImageIncrement, mImageDecrement;
    private TextView mTextViewQuantity;
    private int minQuantity = 1;
    private int maxQuantity = 5;
    private OnQuantityChangeListener onQuantityChangeListener;

    public QuantityPicker(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.quntity_picker_layout, this);
    }

    public OnQuantityChangeListener getOnQuantityChangeListener() {
        return onQuantityChangeListener;
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener onQuantityChangeListener) {
        this.onQuantityChangeListener = onQuantityChangeListener;
    }

    public QuantityPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(context, attrs);
    }

    public QuantityPicker(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
        init(context, attrs);
    }

    public interface OnQuantityChangeListener {
        void onValueChanged(int quantity);
    }

    private void init(Context context, AttributeSet attrs) {

        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.quntity_picker_layout, this);
        mImageIncrement = (ImageButton) this.findViewById(R.id.imageButtonIncrement);
        mImageIncrement.setOnClickListener(clickListener);
        mImageDecrement = (ImageButton) this.findViewById(R.id.imageButtonDecrement);
        mImageDecrement.setOnClickListener(clickListener);
        mTextViewQuantity = (TextView) this.findViewById(R.id.textViewQuantity);
        TypedArray typedValue = context.getTheme().obtainStyledAttributes(attrs, R.styleable.QuantityPicker, 0, 0);

        try {
            minQuantity = typedValue.getInteger(R.styleable.QuantityPicker_minQuantity, 1);
            maxQuantity = typedValue.getInteger(R.styleable.QuantityPicker_maxQuantity, 5);
            setButtonColor(typedValue.getColorStateList(R.styleable.QuantityPicker_buttonColor));
            setQuantityTextColor(typedValue.getColorStateList(R.styleable.QuantityPicker_quantityColor));
            setQuantityPicker(typedValue.getBoolean(R.styleable.QuantityPicker_enable, true));
            setTextSize(typedValue.getDimension(R.styleable.QuantityPicker_textSize, 20));
        } finally {
            typedValue.recycle();
        }
        mTextViewQuantity.setText(String.valueOf(minQuantity));

    }

    public void setTextSize(float size) {
        mTextViewQuantity.setTextSize(size);
    }

    public void setQuantityPicker(boolean enable) {
        mImageIncrement.setEnabled(enable);
        mImageDecrement.setEnabled(enable);
    }

    public void setQuantityTextColor(ColorStateList color) {
        if (color!=null) {
            int _color = color.getColorForState(getDrawableState(), Color.BLACK);
            mTextViewQuantity.setTextColor(_color);
        }
    }

    public void setQuantityTextColor(@ColorRes int resId) {
        mTextViewQuantity.setTextColor(ContextCompat.getColor(mContext, resId));
    }

    public void setQuantityTextColor(@NonNull String colorSting) {
        mTextViewQuantity.setTextColor(ContextCompat.getColor(mContext, Color.parseColor(colorSting)));
    }

    public void setButtonColor(ColorStateList color) {
        if (color!=null) {
            int _color = color.getColorForState(getDrawableState(), Color.BLACK);
            mImageDecrement.setColorFilter(_color);
            mImageIncrement.setColorFilter(_color);
        }
    }

    public void setButtonColor(@ColorRes int resId) {
        mImageDecrement.setColorFilter(ContextCompat.getColor(mContext, resId));
        mImageIncrement.setColorFilter(ContextCompat.getColor(mContext, resId));
    }

    public void setButtonColor(@NonNull String colorSting) {
        mImageDecrement.setColorFilter(ContextCompat.getColor(mContext, Color.parseColor(colorSting)));
        mImageIncrement.setColorFilter(ContextCompat.getColor(mContext, Color.parseColor(colorSting)));
    }

    public int getQuantity() {
        return Integer.parseInt(mTextViewQuantity.getText().toString());
    }

    public void setMinQuantity(int quantity) {
        this.minQuantity = quantity;
    }

    private void setTextViewQuantity(int quantity) {
        if (quantity < 1)
            mTextViewQuantity.setText("1");
        else
            mTextViewQuantity.setText(String.valueOf(quantity));
    }

    private void setMaxQuantity(int quantity) {
        this.maxQuantity = quantity;
    }

    OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int quantity = getQuantity();
            int i = v.getId();
            if (i == R.id.imageButtonIncrement) {
                if (minQuantity >= 1 && quantity < maxQuantity) {
                    setTextViewQuantity(quantity + 1);
                }
            } else if (i == R.id.imageButtonDecrement) {
                if (minQuantity >= 1 && quantity <= maxQuantity) {
                    setTextViewQuantity(quantity - 1);
                }
            }
            if (onQuantityChangeListener != null) {
                onQuantityChangeListener.onValueChanged(getQuantity());
            }
        }

    };
}
