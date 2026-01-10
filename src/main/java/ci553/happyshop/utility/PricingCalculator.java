package ci553.happyshop.utility;

import ci553.happyshop.catalogue.Product;
import java.util.List;

public class PricingCalculator {

    public static double calculateSubtotal(List<Product> products) {
        double total = 0.0;

        for (Product product : products) {
            total += product.getUnitPrice() * product.getOrderedQuantity();
        }

        return total;
    }
}
