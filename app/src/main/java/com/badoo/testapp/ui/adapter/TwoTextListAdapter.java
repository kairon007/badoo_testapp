package com.badoo.testapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.badoo.testapp.R;


/**
 * Adapter to handle listing entries in list with 2 entries per item
 */
public abstract class TwoTextListAdapter extends BaseAdapter {
    Context mContext;
    View listEmptyLayout;

    public TwoTextListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.listitem_custom, parent,
                    false);

            viewHolder.maintext = (TextView) convertView
                    .findViewById(R.id.maintext);
            viewHolder.subtext = (TextView) convertView
                    .findViewById(R.id.subtext);

            // Save the holder with the view
            convertView.setTag(viewHolder);
        } else {
            // Just use the viewHolder and avoid findviewbyid()
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Set values
        viewHolder.maintext.setText(getMainText(position));
        viewHolder.subtext.setText(getSubText(position));

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        if(listEmptyLayout != null)
            if (getCount() != 0)
                listEmptyLayout.setVisibility(View.GONE);
            else
                listEmptyLayout.setVisibility(View.VISIBLE);
        super.notifyDataSetChanged();
    }

    public abstract String getMainText(int position);

    public abstract String getSubText(int position);

    static class ViewHolder {
        TextView maintext;
        TextView subtext;
    }
}
