package no.wtw.android.dynamicspinner.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import no.wtw.android.dynamicspinner.R;
import no.wtw.android.dynamicspinner.view.DynamicItemDropDownView;
import no.wtw.android.dynamicspinner.view.DynamicItemSelectedView;

import java.util.Collection;

public abstract class DynamicSpinnerAdapter<T> extends ArrayAdapter<T> {

    protected DynamicSpinnerInternalListener<T> internalListener;
    private int emptyViewLayoutResourceId = R.layout.dynamic_item_not_selected_view;
    private int addViewLayoutResourceId = R.layout.dynamic_item_add_view;

    private LayoutInflater inflater;

    public DynamicSpinnerAdapter(Context context) {
        super(context, 0);
        init();
    }

    public DynamicSpinnerAdapter(Context context, int notSelectedViewLayoutResourceId, int addViewLayoutResourceId) {
        super(context, 0);
        this.emptyViewLayoutResourceId = notSelectedViewLayoutResourceId;
        this.addViewLayoutResourceId = addViewLayoutResourceId;
        init();
    }

    @Override
    public void addAll(Collection<? extends T> collection) {
        if (Build.VERSION.SDK_INT >= 11)
            super.addAll(collection);
        else
            for (T t : collection)
                super.add(t);
    }

    private void init() {
        inflater = LayoutInflater.from(getContext());
    }

    public void setInternalListener(DynamicSpinnerInternalListener<T> internalListener) {
        this.internalListener = internalListener;
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DynamicItemSelectedView view = (convertView == null) ? new DynamicItemSelectedView(getContext()) : (DynamicItemSelectedView) convertView;
        if (position < super.getCount())
            view.bind(getItemView(getItem(position), position));
        else
            view.bind(inflater.inflate(emptyViewLayoutResourceId, null));
        return view;
    }

    @Override
    public View getDropDownView(final int position, View convertView, ViewGroup parent) {
        DynamicItemDropDownView view = (convertView == null) ? new DynamicItemDropDownView(getContext()) : (DynamicItemDropDownView) convertView;
        if (position < super.getCount()) {
            final T item = getItem(position);
            view.bind(getItemDropDownView(item, position), isItemDeletable(item, position));
            view.setOnDeleteButtonClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    internalListener.onItemDelete(item, position);
                }
            });
        } else {
            view.bind(inflater.inflate(addViewLayoutResourceId, null), false);
            view.setOnDeleteButtonClickListener(null);
        }
        return view;
    }

    public abstract View getItemDropDownView(final T item, final int position);

    public abstract View getItemView(final T item, final int position);

    public abstract boolean isItemDeletable(final T item, final int position);

    public interface DynamicSpinnerInternalListener<T> {
        public void onItemDelete(T item, int position);
    }

}