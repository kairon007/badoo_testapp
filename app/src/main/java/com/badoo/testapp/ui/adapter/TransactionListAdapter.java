package com.badoo.testapp.ui.adapter;

import android.content.Context;

import com.badoo.testapp.R;
import com.badoo.testapp.helper.NumberFormater;
import com.badoo.testapp.model.Transaction;

import java.util.HashMap;
import java.util.List;

/**
 * Product list handling adapter
 */
public class TransactionListAdapter extends TwoTextListAdapter{
    List<Transaction> mTransactions;
    HashMap<String, Float> gbpRates;

    public TransactionListAdapter(Context context) {
        super(context);
    }

    @Override
    public String getMainText(int position) {
        return mTransactions.get(position).getCurrency() + ' '
               + NumberFormater.round(mTransactions.get(position).getAmount());
    }

    @Override
    public String getSubText(int position) {
        if(gbpRates == null || !gbpRates.containsKey(mTransactions.get(position).getCurrency()))
            return "";

        float rate = gbpRates.get(mTransactions.get(position).getCurrency());
        float converted = mTransactions.get(position).getAmount() * rate;
        return mContext.getString(R.string.gbp_amount, NumberFormater.round(converted));
    }

    @Override
    public int getCount() {
        return mTransactions == null ? 0 : mTransactions.size();
    }

    @Override
    public Transaction getItem(int position) {
        return mTransactions.get(position);
    }

    /**
     * Update list entries
     *
     * @param transactions
     */
    public void update(List<Transaction> transactions, HashMap<String, Float> gbpRates) {
        this.gbpRates = gbpRates;
        mTransactions = transactions;
        super.notifyDataSetChanged();
    }
}
