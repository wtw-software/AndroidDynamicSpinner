package no.wtw.android.dynamicspinner.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import no.wtw.android.dynamicspinner.R;

public class DynamicItemDropDownView extends FrameLayout {

    private FrameLayout container;
    private ImageView separator;
    private ImageButton deleteButton;

    public DynamicItemDropDownView(Context context) {
        this(context, null);
    }

    public DynamicItemDropDownView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.ds_dynamicSpinnerStyle);
    }

    public DynamicItemDropDownView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(getContext(), R.layout.dynamic_item_dropdown_view, this);
        container = (FrameLayout) findViewById(R.id.view_container);
        separator = (ImageView) findViewById(R.id.separator);
        deleteButton = (ImageButton) findViewById(R.id.delete_button);
        try {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DynamicSpinner, defStyle, R.style.Theme_DynamicSpinnerStyle);
            Drawable icon = a.getDrawable(R.styleable.DynamicSpinner_ds_deleteItemIcon);
            deleteButton.setImageDrawable(icon);
        } catch (NullPointerException | Resources.NotFoundException e) {
        }
    }

    public void bind(View containerContent, boolean isDeletable) {
        container.removeAllViews();
        deleteButton.setVisibility(isDeletable ? VISIBLE : GONE);
        separator.setVisibility(isDeletable ? VISIBLE : GONE);
        container.addView(containerContent);
    }

    public void setOnDeleteButtonClickListener(OnClickListener listener) {
        deleteButton.setOnClickListener(listener);
    }

}
