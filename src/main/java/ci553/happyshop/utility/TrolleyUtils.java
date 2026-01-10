package ci553.happyshop.utility;

import ci553.happyshop.catalogue.Product;

import java.util.ArrayList;
import java.util.List;

public class TrolleyUtils {

    public static List<Product> mergeAndSortTrolley(List<Product> trolley) {
        List<Product> merged = new ArrayList<>();

        for (Product product : trolley) {
            boolean found = false;

            for (Product existing : merged) {
                if (existing.getProductId().equals(product.getProductId())) {
                    existing.setOrderedQuantity(
                            existing.getOrderedQuantity() + product.getOrderedQuantity()
                    );
                    found = true;
                    break;
                }
            }

            if (!found) {
                merged.add(product);
            }
        }

        // Your Product already implements Comparable (compareTo uses productId),
        // so sorting like this is clean:
        merged.sort(Product::compareTo);

        return merged;
    }

    public static List<Product> removeInsufficientStock(List<Product> trolley) {
        List<Product> valid = new ArrayList<>();

        for (Product product : trolley) {
            if (product.getOrderedQuantity() <= product.getStockQuantity()) {
                valid.add(product);
            }
        }

        return valid;
    }
}
