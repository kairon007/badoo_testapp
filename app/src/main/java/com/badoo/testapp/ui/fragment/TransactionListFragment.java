package com.badoo.testapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.badoo.testapp.DataSets;
import com.badoo.testapp.R;
import com.badoo.testapp.helper.NumberFormater;
import com.badoo.testapp.helper.RatesParser;
import com.badoo.testapp.helper.ResourceReader;
import com.badoo.testapp.model.Product;
import com.badoo.testapp.model.Rate;
import com.badoo.testapp.model.Transaction;
import com.badoo.testapp.ui.adapter.TransactionListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * This fragment handles listing of transactions1
 */
public class TransactionListFragment extends Fragment {
    Context mContext;
    Product mProduct;
    TransactionListAdapter mTransactionListAdapter;
    HashMap<String, Float> gbpRates = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_transactionlist,
                container, false);
        mProduct = (Product) this.getArguments().getSerializable("product");
        getActivity().setTitle(getString(R.string.tras_title, mProduct.getSku()));

        // Init rates and set total
        initRates();
        TextView totalView = (TextView) rootView.findViewById(R.id.transaction_total);
        totalView.setText(getString(R.string.trans_total, NumberFormater.round(getTotal())));

        // Setup listview with data
        ListView transListView = (ListView) rootView.findViewById(R.id.transaction_list);
        mTransactionListAdapter = new TransactionListAdapter(mContext);
        mTransactionListAdapter.update(mProduct.getTransactions(), gbpRates);
        transListView.setAdapter(mTransactionListAdapter);

        return rootView;
    }

    /**
     * Initialize the conversion rates for other currencies to GBP conversions.
     */
    void initRates(){
        List<Rate> rates = new Gson().fromJson(
                ResourceReader.readResource(getResources(), DataSets.SOURCE_RATES),
                new TypeToken<ArrayList<Rate>>() {}.getType());
        RatesParser p = new RatesParser(rates);
        gbpRates = p.doConversion();
    }

    /**
     * Total of transactions in GBP.
     *
     * Note : This ignores amounts for which a conversion rate doesn't exit and cannot be
     * derived either
     *
     * @return total in GBP
     */
    float getTotal(){
        if(gbpRates ==  null) return 0;

        float total = 0;
        for(Transaction transaction : mProduct.getTransactions())
            if(gbpRates.containsKey(transaction.getCurrency()))
                total += transaction.getAmount() * gbpRates.get(transaction.getCurrency());
        return total;
    }
}
