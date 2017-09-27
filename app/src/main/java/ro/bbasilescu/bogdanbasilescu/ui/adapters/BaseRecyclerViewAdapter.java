package ro.bbasilescu.bogdanbasilescu.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {
    private List<T> itemList;

    public BaseRecyclerViewAdapter(List<T> itemList) {
        this.itemList = itemList;
    }

    @Override
    public abstract V onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    protected List<T> getItemList() {
        return itemList;
    }
}
