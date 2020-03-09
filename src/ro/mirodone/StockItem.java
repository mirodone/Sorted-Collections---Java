package ro.mirodone;

public class StockItem implements Comparable<StockItem> {

    private final String name;
    private double price;
    private int qtyStock = 0;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.qtyStock = 0;
    }

    public StockItem(String name, double price, int qtyStock) {
        this.name = name;
        this.price = price;
        this.qtyStock = qtyStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    //getQtyStock getter changed to  quantityInStock to make more sense in what it does
    public int quantityInStock() {
        return qtyStock;
    }

    public void setPrice(double price) {
        if(price > 0.0){
        this.price = price;
        }
    }

    // change setter setQtyStock to adjustStock to make more sense
    public void adjustStock(int quantity) {
        int newQty = this.qtyStock+quantity;
        if(newQty >= 0 ) {
            this.qtyStock = newQty;
        }
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");
        if(obj == this) {
            return true;
        }

        if((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() +31;
    }

    @Override
    public int compareTo(StockItem o) {
        System.out.println("Entering StockItem.compareTo");
        if(this == o) {
            return 0;
        }

        if(o != null) {
            return this.name.compareTo(o.getName());
        }

        throw new NullPointerException();

    }

    @Override
    public String toString() {
        return this.name + " : price " + this.price;
    }
}
