package ci553.happyshop.utility;

import ci553.happyshop.catalogue.Product;

import java.util.*;

/**
 * Utility methods for trolley/cart operations.
 * Kept framework-agnostic to support unit testing.
 */
public final class TrolleyUtils {

    private TrolleyUtils() { }

    /**
     * Merge products with the same productId by summing orderedQuantity, then sort by productId.
     * The returned list is a new list; it does not modify the input list elements.
     */
    public static ArrayList<Product> mergeAndSortById(List<Product> products) {
        Map<String, Product> merged = new HashMap<>();
        for (Product p : products) {
            if (p == null) continue;
            String id = p.getProductId();
            if (id == null) continue;

            Product existing = merged.get(id);
            if (existing == null) {
                Product copy = new Product(p.getProductId(), p.getProductDescription(),
                        p.getProductImageName(), p.getUnitPrice(), p.getStockQuantity());
                int qty = Math.max(0, p.getOrderedQuantity());
                copy.setOrderedQuantity(qty);
                merged.put(id, copy);
            } else {
                existing.setOrderedQuantity(existing.getOrderedQuantity() + Math.max(0, p.getOrderedQuantity()));
            }
        }

        ArrayList<Product> out = new ArrayList<>(merged.values());
        out.sort(Comparator.comparing(Product::getProductId));
        return out;
    }

    /**
     * Remove any items from trolley that have productIds appearing in the insufficient list.
     * Returns a new list (does not mutate the input list).
     */
    public static ArrayList<Product> removeInsufficientById(List<Product> trolley, List<Product> insufficient) {
        Set<String> badIds = new HashSet<>();
        for (Product p : insufficient) {
            if (p != null && p.getProductId() != null) badIds.add(p.getProductId());
        }
        ArrayList<Product> kept = new ArrayList<>();
        for (Product p : trolley) {
            if (p == null || p.getProductId() == null) continue;
            if (!badIds.contains(p.getProductId())) kept.add(p);
        }
        return kept;
    }
}
