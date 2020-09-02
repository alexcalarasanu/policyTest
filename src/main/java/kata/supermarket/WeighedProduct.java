package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;
    private final DiscountType discountType;
    private final int itemId;

    public WeighedProduct(final BigDecimal pricePerKilo, final DiscountType discountType, final int itemId) {
        this.pricePerKilo = pricePerKilo;
        this.discountType = discountType;
        this.itemId = itemId;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }
    DiscountType discountType() { return discountType; }
    int itemId() { return itemId; }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
