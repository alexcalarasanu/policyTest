package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal pricePerUnit;
    private final DiscountType discountType;
    private final int itemId;

    public Product(final BigDecimal pricePerUnit, final DiscountType discountType, final int itemId) {
        this.pricePerUnit = pricePerUnit;
        this.discountType = discountType;
        this.itemId = itemId;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    DiscountType discountType() {
        return discountType;
    }

    int itemId() {
        return itemId;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

}
