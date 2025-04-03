package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Product;

@DisplayName("Product Tests")
class ProductTest {
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Sample Product", 100);
        System.out.println("Setup complete.");
    }

    @Test
    @DisplayName("Product is created correctly with valid input")
    void testValidProductCreation() {
        assertNotNull(product);
        assertEquals("Sample Product", product.getName());
    }

    @Test
    @DisplayName("Creating product with negative price throws exception")
    void testInvalidProductCreation() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Invalid Product", -50));
    }

    @ParameterizedTest
    @CsvSource({"0,100", "10,90", "50,50"})
    @DisplayName("Final price calculation with valid discounts")
    void testFinalPriceWithValidDiscount(double discount, double expectedFinalPrice) {
        product.applyDiscount(discount);
        assertEquals(expectedFinalPrice, product.getFinalPrice());
    }

    @Test
    @Timeout(1)
    @DisplayName("Price calculation should complete within timeout")
    void testPriceCalculationTimeout() {
        product.applyDiscount(10);
        assertEquals(90, product.getFinalPrice());
    }
}
