package kata.supermarket;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeighedProductTest {

    @ParameterizedTest
    @MethodSource
    void itemFromWeighedProductHasExpectedUnitPrice(String pricePerKilo, String weightInKilos, String expectedPrice, DiscountType discountType, int itemId) {
        final WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal(pricePerKilo), discountType, itemId);
        final Item weighedItem = weighedProduct.weighing(new BigDecimal(weightInKilos));
        assertEquals(new BigDecimal(expectedPrice), weighedItem.price());
    }

    static Stream<Arguments> itemFromWeighedProductHasExpectedUnitPrice() {
        return Stream.of(
                Arguments.of("100.00", "1.00", "100.00", DiscountType.NONE, 1),
                Arguments.of("100.00", "0.33333", "33.33", DiscountType.NONE, 2),
                Arguments.of("100.00", "0.33335", "33.34", DiscountType.NONE, 3),
                Arguments.of("100.00", "0", "0.00",DiscountType.NONE, 4)
        );
    }

}