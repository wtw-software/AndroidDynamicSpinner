package no.wtw.android.dynamicspinner.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import no.wtw.android.dynamicspinner.R;

public class DynamicItemSelectedView extends FrameLayout {

    private FrameLayout container;

    public DynamicItemSelectedView(Context context) {
        super(context);
        init();
    }

    public DynamicItemSelectedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    protected void init() {
        inflate(getContext(), R.layout.dynamic_item_selected_view, this);
        container = (FrameLayout) findViewById(R.id.view_container);
    }

    public void bind(View containerContent) {
        container.removeAllViews();
        container.addView(containerContent);
    }

}
