package ci553.happyshop.utility;

import ci553.happyshop.catalogue.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PricingCalculatorTest {

    private Product createProduct(String id, double price, int quantity, int stock) {
        Product p = new Product(id, "Test product " + id, id + ".jpg", price, stock);
        p.setOrderedQuantity(quantity);
        return p;
    }
}