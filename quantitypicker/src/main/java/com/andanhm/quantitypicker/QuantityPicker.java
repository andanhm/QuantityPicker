package com.andanhm.quantitypicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * <h1>QuantityPicker</h1>
 * A Layout that arranges that extends LinearLayout arranges quantity Button and TextView in a single column or a single row.
 */
public class QuantityPicker extends LinearLayout {
    final String TAG = getClass().getSimpleName();

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    public static final String BOLD = "bold";
    public static final String ITALIC = "italic";
    public static final String BOLD_ITALIC = "bold_italic";
    public static final String NORMAL = "normal";

    private Context mContext;
    private ImageButton mImageIncrement, mImageDecrement;
    private TextView mTextViewQuantity;
    private int minQuantity = 0;
    private int maxQuantity = 50;

    private float mTextSize = 20f;
    private OnQuantityChangeListener onQuantityChangeListener;

    public QuantityPicker(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.quntity_picker_layout, this);
    }

    private OnQuantityChangeListener getOnQuantityChangeListener() {
        return onQuantityChangeListener;
    }

    /**
     * Register a callback to be invoked when this quantity is clicked. If this view is not
     * clickable, it becomes clickable.
     *
     * @param onQuantityChangeListener The callback that will run
     */
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
    /**
     * Interface definition for a callback to be invoked when a quantity is clicked.
     */
    public interface OnQuantityChangeListener {
        /**
         * Called when a quantity has been clicked.
         *
         * @param quantity The quantity value that has be changed.
         */
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
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.QuantityPicker, 0, 0);

        try {
            minQuantity = typedArray.getInteger(R.styleable.QuantityPicker_minQuantity, 0);
            maxQuantity = typedArray.getInteger(R.styleable.QuantityPicker_maxQuantity, 5);
            setQuantityButtonColor(typedArray.getColorStateList(R.styleable.QuantityPicker_buttonColor));
            setQuantityTextColor(typedArray.getColorStateList(R.styleable.QuantityPicker_quantityColor));
            setQuantityPicker(typedArray.getBoolean(R.styleable.QuantityPicker_enable, true));
            setTextSize(typedArray.getDimension(R.styleable.QuantityPicker_textSize, mTextSize));
            setTextStyle(typedArray.getString(R.styleable.QuantityPicker_textStyle));
        } finally {
            typedArray.recycle();
        }
        mTextViewQuantity.setText(String.valueOf(minQuantity));

    }

    /**
     * Sets the default text size to the quantity picker, interpreted as "scaled
     * pixel" units.  This size is adjusted based on the current density and
     * user font size preference.
     *
     * @param size The scaled pixel size.
     */
    public void setTextSize(float size) {
        if (size == 0) {
            return;
        }
        mTextViewQuantity.setTextSize(size);
    }

    /**
     * Sets the default text style to the quantity picker, in which the text should be displayed
     * as bold,italic,normal,bold and italic
     *
     * @param typeface The scaled pixel size.
     */
    public void setTextStyle(String typeface) {
        if (typeface == null)
            return;
        switch (typeface) {
            case "bold":
                mTextViewQuantity.setTypeface(null, Typeface.BOLD);
                break;
            case "italic":
                mTextViewQuantity.setTypeface(null, Typeface.ITALIC);
                break;
            case "bold_italic":
                mTextViewQuantity.setTypeface(null, Typeface.BOLD_ITALIC);
                break;
            case "normal":
                mTextViewQuantity.setTypeface(null, Typeface.NORMAL);
                break;
        }

    }

    /**
     * Set the enabled state of this quantity view picker.
     * By default quantity picker is enabled
     *
     * @param enable True if this view is enabled, false otherwise.
     */
    public void setQuantityPicker(boolean enable) {
        mImageIncrement.setEnabled(enable);
        mImageDecrement.setEnabled(enable);
    }
    /**
     * Sets the text color of the quantity picker
     * By default quantity picker text color is black
     *
     * @param color ColorStateList that returns the specified mapping from states to colors.
     */
    public void setQuantityTextColor(ColorStateList color) {
        if (color == null)
            return;
        int _color = color.getColorForState(getDrawableState(), Color.BLACK);
        mTextViewQuantity.setTextColor(_color);
    }
    /**
     * Sets the text color of the quantity picker
     *
     * @param resId The desired resource identifier, as generated by the aapt
     *           tool. This integer encodes the package, type, and resource
     *           entry. The value 0 is an invalid identifier.
     */
    public void setQuantityTextColor(int resId) {
        if (resId == 0) {
            return;
        }
        mTextViewQuantity.setTextColor(ContextCompat.getColor(mContext, resId));
    }
    /**
     * Sets the text color of the quantity picker
     *
     * @param colorSting A single color value in the form 0xAARRGGBB.
     */
    public void setQuantityTextColor(String colorSting) {
        if (colorSting==null)
            return;
        mTextViewQuantity.setTextColor(ContextCompat.getColor(mContext, Color.parseColor(colorSting)));
    }
    /**
     * Sets the button color of the quantity picker
     * By default quantity picker text color is black
     *
     * @param color ColorStateList that returns the specified mapping from states to colors.
     */
    public void setQuantityButtonColor(ColorStateList color) {
        if (color != null) {
            int _color = color.getColorForState(getDrawableState(), Color.BLACK);
            mImageDecrement.setColorFilter(_color);
            mImageIncrement.setColorFilter(_color);
        }
    }
    /**
     * Sets the button color of the quantity picker
     *
     * @param resId The desired resource identifier, as generated by the aapt
     *           tool. This integer encodes the package, type, and resource
     *           entry. The value 0 is an invalid identifier.
     */
    public void setQuantityButtonColor(int resId) {
        if (resId==0)
            return;
        mImageDecrement.setColorFilter(ContextCompat.getColor(mContext, resId));
        mImageIncrement.setColorFilter(ContextCompat.getColor(mContext, resId));
    }
    /**
     * Sets the text color of the quantity picker
     *
     * @param colorSting A single color value in the form 0xAARRGGBB.
     */
    public void setQuantityButtonColor(String colorSting) {
        if (colorSting==null)
            return;
        mImageDecrement.setColorFilter(ContextCompat.getColor(mContext, Color.parseColor(colorSting)));
        mImageIncrement.setColorFilter(ContextCompat.getColor(mContext, Color.parseColor(colorSting)));
    }
    /**
     * @return  Return the quantity selected as a integer
     */
    public int getQuantity() {
        return Integer.parseInt(mTextViewQuantity.getText().toString());
    }
    /**
     * To set the minimum value of the quantity picker |(default Min Quantity : 1 )
     * @param quantity Minimum value that need to set
     */
    public void setMinQuantity(int quantity) {
        this.minQuantity = quantity;
    }
    /**
     * To set the quantity value to the quantity picker
     */
    private void setQuantitySelected(int quantity) {
        if (quantity < 1)
            mTextViewQuantity.setText("1");
        else
            mTextViewQuantity.setText(String.valueOf(quantity));
    }
    /**
     * To set the maximum value of the quantity picker |(default Max Quantity : 10 )
     * @param quantity Maximum value that need to set
     */
    public void setMaxQuantity(int quantity) {
        this.maxQuantity = quantity;
    }

    OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int quantity = getQuantity();
            int i = v.getId();
            if (i == R.id.imageButtonIncrement) {
                if (minQuantity >= 0 && quantity < maxQuantity) {
                    setQuantitySelected(quantity + 1);
                }
            } else if (i == R.id.imageButtonDecrement) {
                if (minQuantity >= 0 && quantity <= maxQuantity) {
                    setQuantitySelected(quantity - 1);
                }
            }
            if (onQuantityChangeListener != null) {
                onQuantityChangeListener.onValueChanged(getQuantity());
            }
        }

    };
}
