package com.badoo.testapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.badoo.testapp.DataSets;
import com.badoo.testapp.R;
import com.badoo.testapp.helper.ResourceReader;
import com.badoo.testapp.helper.UIInterfaces;
import com.badoo.testapp.model.Product;
import com.badoo.testapp.model.Transaction;
import com.badoo.testapp.ui.adapter.ProductListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;


/**
 * This fragment handles listing of products
 */
public class ProductListFragment extends Fragment implements AdapterView.OnItemClickListener{
    Context mContext;
    List<Product> mProducts;
    ProductListAdapter mProductListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_productlist,
                container, false);
        getActivity().setTitle(getString(R.string.products_title));

        // Init list of products and transactions
        initProductList();
        ListView productListView = (ListView) rootView.findViewById(R.id.product_list);
        mProductListAdapter = new ProductListAdapter(mContext);
        mProductListAdapter.update(mProducts);

        // Set the list adapters and item listeners
        productListView.setAdapter(mProductListAdapter);
        productListView.setOnItemClickListener(this);

        return rootView;
    }

    /**
     * Initialize the products list from the transactions.
     * In a realistic scenario, this could be cleaner using something like Retrofit.
     */
    void initProductList(){
        List<Transaction> transactions = new Gson().fromJson(
                ResourceReader.readResource(getResources(), DataSets.SOURCE_TRANSACTIONS),
                new TypeToken<ArrayList<Transaction>>() {}.getType());

        // Bundle transactions of same product into one product
        HashMap<String, Product> productHashMap = new HashMap<>();
        if(transactions != null)
            for(Transaction transaction : transactions){
                String sku = transaction.getSku();
                if(!productHashMap.containsKey(sku))
                    productHashMap.put(sku, new Product(sku));
                productHashMap.get(sku).addTransaction(transaction);
            }

        mProducts = new ArrayList<>(productHashMap.values());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle args = new Bundle();
        args.putSerializable("product", mProductListAdapter.getItem(position));
        TransactionListFragment transactionListFragment = new TransactionListFragment();
        transactionListFragment.setArguments(args);
        ((UIInterfaces.FragmentChanger) getActivity()).changeFragment(transactionListFragment);
    }
}
