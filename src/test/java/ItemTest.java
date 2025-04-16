import org.example.Item;
import org.example.SpecialOffer;
import org.example.exceptions.SpecialOfferAlreadyDefinedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemTest {
    Item item;
    SpecialOffer specialOffer1;
    SpecialOffer specialOffer2;
    SpecialOffer specialOffer3;
    SpecialOffer specialOffer4;

    @BeforeEach
    public void setUp() {
        item = new Item("A", 10.0);
        specialOffer1 = new SpecialOffer(3, 20);
        specialOffer2 = new SpecialOffer(2, 18);
        specialOffer3 = new SpecialOffer(5, 20);
        specialOffer4 = new SpecialOffer(3, 19);
    }

    @Test
    public void testSortSpecialOffers() throws SpecialOfferAlreadyDefinedException {
        item.addSpecialOffer(specialOffer1);
        item.addSpecialOffer(specialOffer2);
        item.addSpecialOffer(specialOffer3);
        List<SpecialOffer> expected = new ArrayList<>();
        expected.add(specialOffer3); // 5
        expected.add(specialOffer1);
        expected.add(specialOffer2);

        assertEquals(expected, item.sortSpecialOffers());
    }

    @Test
    public void testAddSpecialOffer() throws SpecialOfferAlreadyDefinedException {
        item.addSpecialOffer(specialOffer1);
        assertThrows( SpecialOfferAlreadyDefinedException.class, () -> item.addSpecialOffer(specialOffer4));
    }
}
