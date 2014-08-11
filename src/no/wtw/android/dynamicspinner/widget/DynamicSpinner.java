package no.wtw.android.dynamicspinner.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import no.wtw.android.dynamicspinner.adapter.DynamicSpinnerAdapter;
import no.wtw.android.dynamicspinner.listener.DynamicSpinnerListener;

public class DynamicSpinner<T> extends Spinner implements DynamicSpinnerAdapter.DynamicSpinnerInternalListener<T> {

    private static final String TAG = DynamicSpinner.class.getSimpleName();
    private DynamicSpinnerListener<T> listener;
    private boolean isFirstSelection = true;

    public DynamicSpinner(final Context context) {
        super(context);
        init();
    }

    public DynamicSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    if (isFirstSelection) {
                        isFirstSelection = false;
                    } else {
                        if (position == getCount() - 1) {
                            Log.d(TAG, "Adding an item");
                            listener.onItemAdd();
                        } else {
                            Log.d(TAG, "Selected an item");
                            listener.onItemSelected((T) getItemAtPosition(position));
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "Nothing selected");
            }
        });
    }

    public void setDynamicSpinnerListener(DynamicSpinnerListener<T> listener) {
        this.listener = listener;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        if (!(adapter instanceof DynamicSpinnerAdapter))
            throw new IllegalArgumentException("Adapter must be of type DynamicSpinnerAdapter");
        super.setAdapter(adapter);
        ((DynamicSpinnerAdapter<T>) adapter).setInternalListener(this);
    }

    @Override
    public void setSelection(int position) {
        boolean sameSelected = position == getSelectedItemPosition();
        super.setSelection(position);
        if (sameSelected) {
            OnItemSelectedListener listener = getOnItemSelectedListener();
            if (listener != null)
                listener.onItemSelected(this, getSelectedView(), position, getSelectedItemId());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getSelectedItem() {
        try {
            return (T) getAdapter().getItem(getSelectedItemPosition());
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public void onItemDelete(T item, int deletedPosition) {
        boolean wasDeleted = listener.onItemDelete(item, deletedPosition);
        if (wasDeleted) {
            try {
                if (getSelectedItemPosition() >= deletedPosition)
                    setSelection(Math.max(0, getSelectedItemPosition() - 1));
            } catch (Exception e) {
                setSelection(0);
            }
        }
    }

}