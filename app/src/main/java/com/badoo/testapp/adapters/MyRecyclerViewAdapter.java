package com.badoo.testapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.badoo.testapp.R;
import com.badoo.testapp.models.Videoz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kairo on 4/5/2016.
 */
public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> implements Filterable {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private List<Videoz.VideosBean> mDataset;
    private static MyClickListener myClickListener;

    public Videoz.VideosBean removeItem(int position) {
        final Videoz.VideosBean model = mDataset.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Videoz.VideosBean model) {
        mDataset.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Videoz.VideosBean model = mDataset.remove(fromPosition);
        mDataset.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public Filter getFilter() {
          return new FilterCustomerSearch(this,mDataset);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView label;
        TextView dateTime;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView);
            dateTime = (TextView) itemView.findViewById(R.id.textView2);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (myClickListener != null)
                myClickListener.onItemClick(getPosition(), v);
        }
    }
    public class FilterCustomerSearch extends Filter {
        private MyRecyclerViewAdapter mAdapter;
        List<Videoz.VideosBean> contactList;
        List<Videoz.VideosBean> filteredList;

        public FilterCustomerSearch(MyRecyclerViewAdapter mAdapter, List<Videoz.VideosBean> contactList) {
            this.mAdapter = mAdapter;
            this.contactList = contactList;
            filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                filteredList.addAll(contactList);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();

                for (final Videoz.VideosBean contact : contactList) {
                    if (contact.getVideo().contains(constraint)) {
                        filteredList.add(contact);
                    } else if (contact.getNombre().contains(constraint)) {
                        filteredList.add(contact);

                    }

                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mAdapter.mDataset.clear();
            mAdapter.mDataset.addAll((ArrayList<Videoz.VideosBean>) results.values);
            mAdapter.notifyDataSetChanged();
        }
    }
    public void mAnimate(List<Videoz.VideosBean> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateMovedItems(List<Videoz.VideosBean> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Videoz.VideosBean model = newModels.get(toPosition);
            final int fromPosition = mDataset.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Videoz.VideosBean> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Videoz.VideosBean model = newModels.get(i);
            if (!mDataset.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateRemovals(List<Videoz.VideosBean> newModels) {
        for (int i = mDataset.size() - 1; i >= 0; i--) {
            final Videoz.VideosBean model = mDataset.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(List<Videoz.VideosBean> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.label.setText(mDataset.get(position).getVideo());
        holder.dateTime.setText(mDataset.get(position).getNombre());
    }

    public void addItem(Videoz.VideosBean dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}