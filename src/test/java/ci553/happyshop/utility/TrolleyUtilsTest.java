package ci553.happyshop.utility;

import ci553.happyshop.catalogue.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrolleyUtilsTest {

    private Product createProduct(String id, double price, int quantity, int stock) {
        Product p = new Product(id, "Test product " + id, id + ".jpg", price, stock);
        p.setOrderedQuantity(quantity);
        return p;
    }

    @Test
    void mergeAndSort_mergesProductsWithSameId() {
        ArrayList<Product> trolley = new ArrayList<>();

        trolley.add(createProduct("0002", 1.50, 1, 100));
        trolley.add(createProduct("0002", 1.50, 2, 100));
        trolley.add(createProduct("0001", 2.00, 1, 100));

        List<Product> result = TrolleyUtils.mergeAndSortTrolley(trolley);

        assertEquals(2, result.size());
        assertEquals("0001", result.get(0).getProductId());
        assertEquals("0002", result.get(1).getProductId());
        assertEquals(3, result.get(1).getOrderedQuantity());
    }

    @Test
    void mergeAndSort_emptyTrolleyReturnsEmptyList() {
        ArrayList<Product> trolley = new ArrayList<>();

        List<Product> result = TrolleyUtils.mergeAndSortTrolley(trolley);

        assertTrue(result.isEmpty());
    }

    @Test
    void mergeAndSort_singleProductUnchanged() {
        ArrayList<Product> trolley = new ArrayList<>();
        trolley.add(createProduct("0003", 3.00, 1, 50));

        List<Product> result = TrolleyUtils.mergeAndSortTrolley(trolley);

        assertEquals(1, result.size());
        assertEquals("0003", result.get(0).getProductId());
        assertEquals(1, result.get(0).getOrderedQuantity());
    }
    @Test
    void mergeAndSort_preservesUnitPriceForMergedItem() {
        ArrayList<Product> trolley = new ArrayList<>();
        trolley.add(createProduct("0005", 9.99, 1, 20));
        trolley.add(createProduct("0005", 9.99, 2, 20));

        List<Product> result = TrolleyUtils.mergeAndSortTrolley(trolley);

        assertEquals(1, result.size());
        assertEquals("0005", result.get(0).getProductId());
        assertEquals(3, result.get(0).getOrderedQuantity());
        assertEquals(9.99, result.get(0).getUnitPrice(), 0.0001);
    }

    @Test
    void mergeAndSort_sortsByProductIdAscending() {
        ArrayList<Product> trolley = new ArrayList<>();
        trolley.add(createProduct("0010", 1.00, 1, 10));
        trolley.add(createProduct("0002", 1.00, 1, 10));
        trolley.add(createProduct("0009", 1.00, 1, 10));

        List<Product> result = TrolleyUtils.mergeAndSortTrolley(trolley);

        assertEquals(3, result.size());
        assertEquals("0002", result.get(0).getProductId());
        assertEquals("0009", result.get(1).getProductId());
        assertEquals("0010", result.get(2).getProductId());
    }
    @Test
    void mergeAndSort_doesNotModifyOriginalListSize() {
        ArrayList<Product> trolley = new ArrayList<>();
        trolley.add(createProduct("0001", 2.50, 1, 10));
        trolley.add(createProduct("0001", 2.50, 1, 10));

        int originalSize = trolley.size();

        TrolleyUtils.mergeAndSortTrolley(trolley);

        assertEquals(originalSize, trolley.size());
    }

    @Test
    void mergeAndSort_handlesLargeQuantitiesCorrectly() {
        ArrayList<Product> trolley = new ArrayList<>();
        trolley.add(createProduct("0010", 1.00, 50, 200));
        trolley.add(createProduct("0010", 1.00, 25, 200));

        List<Product> result = TrolleyUtils.mergeAndSortTrolley(trolley);

        assertEquals(1, result.size());
        assertEquals(75, result.get(0).getOrderedQuantity());
    }





}

