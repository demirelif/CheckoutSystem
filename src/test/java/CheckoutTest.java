import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutTest {

    private Checkout checkout;
    private Item A;

    @BeforeEach
    public void setUp() {
        A = new Item( "A", 30);

        PriceList priceList = new PriceList();
        priceList.addItem( A );
        priceList.addItem( new Item("B", 20) );
        priceList.addItem( new Item("C", 10) );

        checkout = new Checkout(priceList);

        SpecialOffer specialOffer1 = new SpecialOffer(2, 35);
        SpecialOffer specialOffer2 = new SpecialOffer(3, 50);
        SpecialOffer specialOffer3 = new SpecialOffer(2, 30);

        priceList.addSpecialOfferToItem("A", specialOffer1);
        priceList.addSpecialOfferToItem("A", specialOffer2);
        priceList.addSpecialOfferToItem("B", specialOffer3);
    }

    @Test
    public void testScan(){
        checkout.scan( "A" );
        checkout.scan( "A" );
        assertEquals( 2, checkout.getCart().getCount(A));
    }

    @Test
    public void testCancelScan(){
        checkout.scan( "A" );
        checkout.cancelScan( "A" );
        assertEquals( 0, checkout.getCart().getCount(A));
    }

    @Test
    public void testScanInvalidItem(){
        checkout.scan( "E" );
        assertTrue( checkout.getCart().getItems().isEmpty() );
    }

    @Test
    public void testCalculateTotal(){
        checkout.scan( "A" );
        checkout.scan( "B" );
        assertEquals( 50,  checkout.total());
    }

    @Test
    public void testCalculateTotalWithSpecialPrice(){
        addItemNTimes("A",2 );
        assertEquals( 35,  checkout.total());

        addItemNTimes("A",3);
        assertEquals( 85,  checkout.total());

        addItemNTimes("A",1);
        assertEquals( 100,  checkout.total());

        addItemNTimes("B",2);
        assertEquals( 130,  checkout.total());
    }

    private void addItemNTimes(String item, int nTimes){
        for ( int i = 0; i < nTimes; i++){
            checkout.scan(item);
        }
    }
}
