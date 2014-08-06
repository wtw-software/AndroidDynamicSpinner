package no.wtw.android.dynamicspinner.listener;

public interface DynamicSpinnerListener<T> {

    public boolean onItemDelete(T item, int itemPosition);

    public void onItemAdd();

    public void onItemSelected(T item);

}
