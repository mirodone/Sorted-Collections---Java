package ro.mirodone;


import java.util.Map;

public class Main {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {


        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.56, 20);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.3, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("egg", 0.22, 50);
        stockList.addStock(temp);


        temp = new StockItem("juice", 2.25, 12);
        stockList.addStock(temp);

        temp = new StockItem("plate", 2.25, 60);
        stockList.addStock(temp);

        System.out.println(stockList);

        //////////////////////////////////////////////////////////

        Basket myBasket = new Basket("MyBasket");
        sellItem(myBasket, "cake", 1);
        System.out.println(myBasket);

        sellItem(myBasket, "cake", 19);
        System.out.println(myBasket);

        if(sellItem(myBasket, "cake", 2) != 1) {
            System.out.println("No more Cake in stock !");
        }


        sellItem(myBasket, "ring", 1);

        sellItem(myBasket, "egg", 10);
        sellItem(myBasket, "cup", 15);
        sellItem(myBasket, "juice", 2);
        sellItem(myBasket, "plate", 12);

        //System.out.println(stockList);

        Basket basket = new Basket("Customer");
        sellItem(basket, "cup", 100);
        sellItem(basket, "juice", 3);
        removeItem(basket, "cup", 1);
        System.out.println(basket);

        removeItem(myBasket, "cake", 19);
        removeItem(myBasket, "egg" , 10);
        removeItem(myBasket, "cake", 1);
        System.out.println("cake removed: " + removeItem(myBasket, "cake", 1)); // should not remove any

        System.out.println(myBasket);


        System.out.println("\nDisplay stock list before and after checkout");
        System.out.println(basket);
        System.out.println(stockList);
        checkOut(basket);
        System.out.println(basket);
        System.out.println(stockList);

        //error as we cannot modify the Map
       // temp = new StockItem("pen", 1.1);
      //  stockList.Items().put(temp.getName(), temp);

        checkOut(myBasket);
        System.out.println(myBasket);


    }

    public static int sellItem(Basket basket, String item, int qty) {
        //retrieve the item from stock list

        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We do not sell " + item);
            return 0;
        }

        if (stockList.reserveStock(item, qty) != 0) {
            return basket.addToBasket(stockItem,qty);
        }
        return 0;
    }


    public static int removeItem(Basket basket, String item, int qty) {
        //retrieve the item from stock list

        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We do not sell " + item);
            return 0;
        }

        if (basket.removeFromBasket(stockItem, qty) == qty) {
            return stockList.unreserveStock(item, qty);
        }
        return 0;
    }


    public static void checkOut (Basket basket) {
        for (Map.Entry<StockItem, Integer> item : basket.Items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }

}
