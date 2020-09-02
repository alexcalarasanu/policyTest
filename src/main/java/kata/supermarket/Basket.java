package kata.supermarket;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Basket {
    private final List<Item> items;
    public Map<Integer, Integer> itemCountToItemIdMap;


    public Basket() {
        this.items = new ArrayList<>();
        this.itemCountToItemIdMap = new HashMap<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }


        private BigDecimal discounts() {
            DiscountCalculator discountCalculator = new DiscountCalculator();
            BigDecimal totalDiscounts = BigDecimal.ZERO;
            //Mapping the number of items to the itemId, thus we are able to calculate all the different types of discounts
            itemCountToItemIdMap = items.stream().collect(Collectors.groupingBy(Item::itemId, Collectors.summingInt(i -> 1)));
            for (Integer itemId : itemCountToItemIdMap.keySet()) {
                Item item = items.stream().filter(i -> itemId.equals(i.itemId())).findAny().orElse(null);
                if (item.discountType() != null) {
                    switch (item.discountType()) {
                        case BUY_ONE_GET_ONE_FREE:
                            totalDiscounts = totalDiscounts.add(discountCalculator.calculateBuyOneGetOneFree(item, itemCountToItemIdMap.get(itemId)));
                            break;
                        case TWO_FOR_ONE_POUND:
                            totalDiscounts = totalDiscounts.add(discountCalculator.calculateTwoForOnePound(item, itemCountToItemIdMap.get(itemId)));
                            break;
                        case THREE_FOR_THE_PRICE_OF_TWO:
                            break;
                        case ONE_KILO_HALF_PRICE:
                            totalDiscounts = totalDiscounts.add(discountCalculator.calculateOneKiloHalfPrice(item));
                            break;
                        default:
                            break;
                    }
                }
            }
            return totalDiscounts;
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }

    public class DiscountCalculator {
        public BigDecimal calculateBuyOneGetOneFree(Item item, Integer numberOfItems) {
            BigDecimal discountAmount = item.price().multiply(BigDecimal.valueOf(numberOfItems));
            if (numberOfItems % 2 == 0) {
                discountAmount = discountAmount.divide(BigDecimal.valueOf(2));
            } else {
                discountAmount = discountAmount.subtract(item.price()).divide(BigDecimal.valueOf(2));
            }
            return discountAmount;
        }

        public BigDecimal calculateOneKiloHalfPrice(Item item) {
            if(item instanceof ItemByWeight){
                BigDecimal weight = ((ItemByWeight) item).getWeightInKilos();
                //We have to use BigDecimal.valueOf(000, 2) because the weight is already scaled and BigDecimal.ZERO is not 0.00
                //This will work with all values that are not fractions
                if( weight.remainder(BigDecimal.ONE).equals(BigDecimal.valueOf(000,2))){
                    return item.price().divide(BigDecimal.valueOf(2), 2, RoundingMode.UP);
                }
                //TODO: add implementation for weights that are not exact in kilos
            }
            return BigDecimal.ZERO;
        }

        public BigDecimal calculateTwoForOnePound(Item item, Integer numberOfItems) {
            BigDecimal discountAmount = item.price().multiply(BigDecimal.valueOf(numberOfItems));
            if (numberOfItems % 2 == 0) {
                discountAmount = discountAmount.subtract(BigDecimal.valueOf(numberOfItems / 2));
            } else {
                discountAmount = discountAmount.subtract(BigDecimal.valueOf(numberOfItems / 2).add(item.price()));
            }
            return discountAmount;
        }
    }
}