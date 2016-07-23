package com.badoo.testapp.helper;

import com.badoo.testapp.model.Rate;
import com.badoo.testapp.model.graph.Currency;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import timber.log.Timber;


/**
 * Parses all available rates1 and derives a conversion to GBP for every currency
 */
public class RatesParser {
    final static String PRIMARY_CURRENCY = "GBP";

    List<Rate> allRates;
    HashMap<String, Float> gbpRates = new HashMap<>();

    public RatesParser(List<Rate> rates){
        this.allRates = rates;
        gbpRates.put(PRIMARY_CURRENCY, Float.valueOf(1)); // primary to primary rate is 1
    }

    /**
     * Performs rate conversion for every currency to GBP
     * @return hashmap of conversion rates from other currencies to GBP
     */
    public HashMap<String, Float> doConversion(){
        if(allRates == null)
            return null;

        // 1. Build a directed graph from list of all known rates
        DirectedGraph<Currency, Rate> rateGraph = new DefaultDirectedGraph<>(Rate.class);

        for(Rate rate : allRates){
            Currency from = new Currency(rate.getFrom());
            Currency to = new Currency(rate.getTo());
            rateGraph.addVertex(from);
            rateGraph.addVertex(to);
            rateGraph.addEdge(from, to, rate);
        }

        // 2. Do a BFS and get derived or direct rates for GBP
        List<Currency> queue = new ArrayList<>();
        Currency gbp = new Currency(PRIMARY_CURRENCY);
        gbp.setSeen(true);
        queue.add(gbp);
        while (queue.size() != 0){
            Currency dst = queue.remove(0); // Fifo behaviour for BFS

            Set<Rate> inEdges = rateGraph.incomingEdgesOf(dst); // We need conversions towards primary.
            for(Rate inedge : inEdges){
                Currency src = rateGraph.getEdgeSource(inedge);

                // We are interested only in the nodes we haven't already seen
                if(!src.getSeen()){
                    src.setSeen(true);

                    // Do not override existing direct or derived rates. Probably doesn't matter.
                    if(!gbpRates.containsKey(src.getName()))
                        gbpRates.put(src.getName(), inedge.getRate() * gbpRates.get(dst.getName()));
                    queue.add(src);
                }
            }

        }

        for(String curr : gbpRates.keySet())
            Timber.i("Currency : " + curr + " rate : " + gbpRates.get(curr));

        return gbpRates;
    }

}
