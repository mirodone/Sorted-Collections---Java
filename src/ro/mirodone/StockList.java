package ro.mirodone;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {

    private final Map<String, StockItem> list;


    public StockList() {
     //   this.list = new HashMap<>();

        //changed to Linked list to maintain the insertion order
      this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            //check if we already have qtys of this item
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // if there are already stocks on this item, adjust the qty
            if (inStock != item) {
                item.adjustStock(inStock.availableQuantity());
            }

            list.put(item.getName(), item);
            return item.availableQuantity();

        }
        return 0;
    }

    public int sellStock(String item, int qty) {

        StockItem inStock = list.get(item);

        if((inStock != null) && (qty > 0 )){
            return inStock.finaliseStock(qty);
        }
        return 0;

 /*        StockItem inStock = list.getOrDefault(item, null);

       if ((inStock != null) && (inStock.availableQuantity() >= qty) && (qty > 0)) {
            inStock.adjustStock(-qty);
            return qty;
        }
        // nothing was taken from the stock
        return 0;*/
    }

    public int reserveStock ( String item, int qty) {
        StockItem inStock = list.get(item);

        if((inStock != null) && (qty > 0 )){
            return inStock.reserveStock(qty);
        }
        return 0;
    }

    public int unreserveStock ( String item, int qty) {
        StockItem inStock = list.get(item);

        if((inStock != null) && (qty > 0 )){
            return inStock.unreserveStock(qty);
        }
        return 0;
    }


    ///////////////////////////////////
    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nStock list\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : list.entrySet()){
            StockItem stockItem = item.getValue();

            double itemValue  = stockItem.getPrice() * stockItem.availableQuantity();

            s = s + stockItem + ". There are " +stockItem.availableQuantity() + " in stock. Value of items:  ";
            s = s + String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;

        }
        return  s + "Total stock value " + String.format("%.2f", totalCost);
    }
}
