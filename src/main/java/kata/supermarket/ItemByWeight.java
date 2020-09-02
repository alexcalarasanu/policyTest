package kata.supermarket;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final WeighedProduct product;

    public BigDecimal getWeightInKilos() {
        return weightInKilos;
    }

    private final BigDecimal weightInKilos;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    public int itemId() {
        return  product.itemId();
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public DiscountType discountType() {
        return product.discountType();
    }
}
