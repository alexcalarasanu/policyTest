package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;

    ItemByUnit(final Product product) {
        this.product = product;
    }


    public BigDecimal price() {
        return product.pricePerUnit();
    }


    public DiscountType discountType() {
        return product.discountType();
    }

    public int itemId(){
        return product.itemId();
    }
}
