package ro.mirodone;

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

        Basket myBasket = new Basket("MyBasket");
        sellItem(myBasket, "cake", 1);
        System.out.println(myBasket);

        sellItem(myBasket, "cake", 19);
        System.out.println(myBasket);

        sellItem(myBasket, "cake", 2);
        sellItem(myBasket, "ring", 1);
        System.out.println(myBasket);

        sellItem(myBasket, "egg", 10);
        sellItem(myBasket, "cup", 15);
        sellItem(myBasket, "juice", 2);
        sellItem(myBasket, "plate", 12);
        System.out.println(myBasket);

        System.out.println(stockList);

        //error as we cannot modify the Map
       // temp = new StockItem("pen", 1.1);
      //  stockList.Items().put(temp.getName(), temp);


    }

    public static int sellItem(Basket basket, String item, int qty) {
        //retrieve the item from stock list

        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We do not sell " + item);
            return 0;
        }

        if (stockList.sellStock(item, qty) != 0) {
            basket.addToBasket(stockItem, qty);
            return qty;
        }
        return 0;
    }

}
