package vbs.vvi.com.bs.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.TextView;

import vbs.vvi.com.bs.R;


public class ExpandableTextView extends TextView {
    private static final int DEFAULT_TRIM_LENGTH = 60; // 超过60字折叠
    private static final String ELLIPSIS = ".....";
   
    private CharSequence originalText;
    private CharSequence trimmedText;
    private BufferType bufferType;
    private boolean trim = true;  
    private int trimLength;  
   
    public ExpandableTextView(Context context) {
        this(context, null);  
    }  
   
    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);  
   
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        this.trimLength = typedArray.getInt(R.styleable.ExpandableTextView_trimLength, DEFAULT_TRIM_LENGTH);  
        typedArray.recycle();  
    }


    /**
     * 全文和收起轮回
     */
    public boolean toggle() {
        trim = !trim;
        setText();
        requestFocusFromTouch();
        return trim;
    }
   
    private void setText() {  
        super.setText(getDisplayableText(), bufferType);  
    }  
   
    private CharSequence getDisplayableText() {
        return trim ? trimmedText : originalText;  
    }  
   
    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;  
        trimmedText = getTrimmedText(text);  
        bufferType = type;  
        setText();  
    }  
   
    private CharSequence getTrimmedText(CharSequence text) {
        if (originalText != null && originalText.length() > trimLength) {  
            return new SpannableStringBuilder(originalText, 0, trimLength + 1).append(ELLIPSIS);
        } else {  
            return originalText;  
        }  
    }  
   
    public CharSequence getOriginalText() {
        return originalText;  
    }  
   
    public void setTrimLength(int trimLength) {  
        this.trimLength = trimLength;  
        trimmedText = getTrimmedText(originalText);  
        setText();  
    }  
   
    public int getTrimLength() {  
        return trimLength;  
    }

    /**
     * 是否折叠
     * @return
     */
    public boolean isTrimed() {
        return getOriginalText().length() > getTrimLength();
    }
} 