package com.pos.system;
// package com.pos.system;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartSystemTest {

    private CartSystem cart;

    @BeforeEach
    public void setUp() {
        cart = new CartSystem("ADD", "Test Item", 10.00);
    }

    @Test
    public void testGetAction() {
        assertEquals("ADD", cart.getAction());
    }

    @Test
    public void testGetItem() {
        assertEquals("Test Item", cart.getItem());
    }

    @Test
    public void testGetPrice() {
        assertEquals(10.00, cart.getPrice());
    }

    @Test
    public void testAddToCart() {
        CartSystem.addToCart(10.00);
        assertEquals("Subtotal: $10.00", CartSystem.ShowTotal());
    }

    @Test
    public void testCalculateTax() {
        CartSystem.addToCart(10.00);
        assertEquals("Tax(%10): $ 1.00", CartSystem.CalculateTax());
    }

    @Test
    public void testDisplayFinalTotal() {
        CartSystem.addToCart(10.00);
        CartSystem.CalculateTax(); // Ensure tax is calculated
        assertEquals("Total: $11.00", CartSystem.DisplayFinalTotal());
    }

    @Test
    public void testUpdatePrice() {
        cart.updatePrice(15.00);
        assertEquals(15.00, cart.getPrice());
    }
}