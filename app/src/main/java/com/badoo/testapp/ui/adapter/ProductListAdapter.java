package com.badoo.testapp.ui.adapter;

import android.content.Context;

import com.badoo.testapp.R;
import com.badoo.testapp.model.Product;

import java.util.List;


/**
 * Product list handling adapter
 */
public class ProductListAdapter extends TwoTextListAdapter{
    List<Product> mProducts;

    public ProductListAdapter(Context context) {
        super(context);
    }

    @Override
    public String getMainText(int position) {
        return mProducts.get(position).getSku();
    }

    @Override
    public String getSubText(int position) {
        int count = mProducts.get(position).getTransactions().size();
        return mContext.getResources().getQuantityString(R.plurals.trans_count, count, count);
    }

    @Override
    public int getCount() {
        return mProducts == null ? 0 : mProducts.size();
    }

    @Override
    public Product getItem(int position) {
        return mProducts.get(position);
    }

    /**
     * Update list entries
     *
     * @param products
     */
    public void update(List<Product> products) {
        mProducts = products;
        super.notifyDataSetChanged();
    }
}
