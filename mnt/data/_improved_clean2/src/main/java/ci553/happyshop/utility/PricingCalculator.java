package ci553.happyshop.utility;

import ci553.happyshop.catalogue.Product;

import java.util.List;

/**
 * Pricing calculations for a list of products (trolley or order).
 */
public final class PricingCalculator {

    private PricingCalculator() { }

    /**
     * Calculates subtotal as sum(unitPrice * orderedQuantity).
     * orderedQuantity <= 0 contributes 0.
     */
    public static double subtotal(List<Product> products) {
        double total = 0.0;
        if (products == null) return 0.0;
        for (Product p : products) {
            if (p == null) continue;
            int qty = Math.max(0, p.getOrderedQuantity());
            total += p.getUnitPrice() * qty;
        }
        return total;
    }
}
