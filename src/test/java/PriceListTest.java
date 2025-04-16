import org.example.Item;
import org.example.PriceList;
import org.example.SpecialOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PriceListTest {
    PriceList priceList = new PriceList();
    Item A = new Item("A", 10.00);
    Item B = new Item("B", 10.00);

    @BeforeEach
    void setUp() {
        priceList.addItem(A);
        priceList.addItem(B);
    }

    @Test
    public void testIsItemValid() {
        assertTrue( priceList.isItemValid("A") );
    }

    @Test
    public void testIsItemInvalid() {
        assertFalse( priceList.isItemValid("E") );
    }

    @Test
    public void testGetItemByName(){
        assertEquals( priceList.getItemByName("A"), A );
    }

    @Test
    public void testAddSpecialOffer() {
        SpecialOffer offer1 = new SpecialOffer(3, 20.0);
        priceList.addSpecialOfferToItem("A", offer1);
        Item item = priceList.getItemByName("A");
        assertEquals( item.getSpecialOffers().get(0), offer1);
    }

    @Test
    public void testAddSpecialOfferForNonPositiveValue() {
        SpecialOffer offer1 = new SpecialOffer(0, 20.0);
        priceList.addSpecialOfferToItem("A", offer1);
        Item item = priceList.getItemByName("A");
        assertEquals(0,  item.getSpecialOffers().size() );
    }

    @Test
    public void testAddSpecialOfferForNegativeValue() {
        SpecialOffer offer1 = new SpecialOffer(-5, 20.0);
        priceList.addSpecialOfferToItem("A", offer1);
        Item item = priceList.getItemByName("A");
        assertEquals(0,  item.getSpecialOffers().size() );
    }

    @Test
    public void testNoDuplicatesForSpecialOffer(){
        SpecialOffer offer1 = new SpecialOffer(3, 20.0);
        SpecialOffer offer2 = new SpecialOffer(3, 15.0);

        priceList.addSpecialOfferToItem("A", offer1);
        priceList.addSpecialOfferToItem("A", offer2);
        assertEquals( 1, priceList.getItemByName("A").sortSpecialOffers().size() );
    }

    @Test
    public void testRemoveSpecialOffer(){
        SpecialOffer offer1 = new SpecialOffer(3, 20.0);
        priceList.addSpecialOfferToItem("A", offer1);
        priceList.removeSpecialOfferFromItem("A", offer1);
        assertEquals(0, priceList.getItemByName("A").getSpecialOffers().size() );
    }
}
