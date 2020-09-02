package kata.supermarket;

import java.math.BigDecimal;

public interface Item {
    int itemId();
    BigDecimal price();
    DiscountType discountType();
}
