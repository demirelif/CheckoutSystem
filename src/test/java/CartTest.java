import org.example.Cart;
import org.example.Item;
import org.example.exceptions.ItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    private Cart cart;
    private Item A;
    private Item B;

    @BeforeEach
    public void setUp() {
        cart = new Cart();
        A = new Item("A", 0.50);
        B = new Item("B", 0.60);
    }

    @Test
    public void testPutAndGetCount() {
        cart.put(A);
        cart.put(B);
        cart.put(A);

        assertEquals(2, cart.getItems().get(A));
        assertEquals(1, cart.getItems().get(B));
    }

    @Test
    public void testRemoveItem() throws ItemNotFoundException {
        cart.put(A);
        cart.put(A);
        cart.removeItem(A);
        assertEquals(1, cart.getItems().get(A));
    }

    @Test
    public void testRemoveItemCompletely() throws ItemNotFoundException {
        cart.put(A);
        cart.removeItem(A);
        assertFalse(cart.getItems().containsKey(A));
    }

    @Test
    public void testRemoveNonexistentItem() {
        assertThrows(ItemNotFoundException.class, () -> cart.removeItem(B));
    }
}
