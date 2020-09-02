package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                buyOneGetOneFreeWithTwoOfTheSameItems(),
                buyOneGetOneFreeWithDifferentItems(),
                buyOneGetOneFreeWithOddNumberOfItemsOnOffer(),
                kiloHalfPrice(),
                kiloHalfPriceOneKiloAndAHalf(),
                twoForOnePoundWithTwoOfTheSameItems(),
                twoForOnePoundWithDifferentItems(),
                twoForOnePoundWithOddNumberOfItemsOnOffer()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Arguments buyOneGetOneFreeWithTwoOfTheSameItems() {
        return Arguments.of("buy one get one free - two of the same items", "0.50", Arrays.asList(aCanOfBeans(), aCanOfBeans()));
    }

    private static Arguments buyOneGetOneFreeWithDifferentItems() {
        return Arguments.of("buy one get one free - different items", "0.99", Arrays.asList(aCanOfBeans(), aCanOfBeans(), aPintOfMilk()));
    }

    private static Arguments buyOneGetOneFreeWithOddNumberOfItemsOnOffer() {
        return Arguments.of("buy one get one free - odd number of items", "1.00", Arrays.asList(aCanOfBeans(), aCanOfBeans(), aCanOfBeans()));
    }

    private static Arguments kiloHalfPrice() {
        return Arguments.of("kiloHalfPrice", "0.50",
                Collections.singleton(aKilogramOfCarrots())
        );
    }

    private static Arguments kiloHalfPriceOneKiloAndAHalf() {
        return Arguments.of("kiloHalfPriceOneKiloAndAHalf", "1.00",
                Collections.singleton(aKilogramAndAHalfOfCarrots())
        );
    }

    private static Arguments twoForOnePoundWithTwoOfTheSameItems() {
        return Arguments.of("two for one pound - two of the same items", "1.00", Arrays.asList(aCanOfTomatoSoup(), aCanOfTomatoSoup()));
    }

    private static Arguments twoForOnePoundWithDifferentItems() {
        return Arguments.of("two for one pound - different items", "2.55", Arrays.asList(aCanOfTomatoSoup(), aCanOfTomatoSoup(), aPackOfDigestives()));
    }

    private static Arguments twoForOnePoundWithOddNumberOfItemsOnOffer() {
        return Arguments.of("two for one pound - odd number of items", "1.75", Arrays.asList(aCanOfTomatoSoup(), aCanOfTomatoSoup(), aCanOfTomatoSoup()));
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49"), DiscountType.NONE, 1).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55"), DiscountType.NONE, 2).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"), DiscountType.NONE, 3);
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"), DiscountType.NONE, 4);
    }

    private static WeighedProduct aKiloOfCarrots() {
        return new WeighedProduct(new BigDecimal("1.00"), DiscountType.ONE_KILO_HALF_PRICE, 8);
    }

    private static Item aCanOfBeans() {
        return new Product(new BigDecimal("0.50"), DiscountType.BUY_ONE_GET_ONE_FREE, 5).oneOf();
    }

    private static Item aCanOfTomatoSoup() {
        return new Product(new BigDecimal("0.75"), DiscountType.TWO_FOR_ONE_POUND, 6).oneOf();
    }


    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

    private static Item aKilogramOfCarrots() {
        return aKiloOfCarrots().weighing(new BigDecimal("1.00"));
    }

    private static Item aKilogramAndAHalfOfCarrots() {
        return aKiloOfCarrots().weighing(new BigDecimal("1.50"));
    }

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }
}