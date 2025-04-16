package org.example;

public class Main {
    public static void main(String[] args) {

        // Setting up the price list
        PriceList priceList = new PriceList();
        priceList.addItem( new Item("A", 80) );
        priceList.addItem( new Item("B", 35) );
        priceList.addItem( new Item("C", 20) );
        priceList.addItem( new Item("D", 10) );

        // Setting up special offers
        SpecialOffer specialOffer1 = new SpecialOffer( 3, 220);
        SpecialOffer specialOffer2 = new SpecialOffer( 2, 55);

        // Assigning special offers to items
        priceList.addSpecialOfferToItem( "A", specialOffer1 );
        priceList.addSpecialOfferToItem( "B", specialOffer2 );

        Checkout checkout = new Checkout( priceList );

        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("B");
        checkout.scan("B");
        checkout.scan("C");
        checkout.scan("A");
        checkout.scan("A");
        checkout.scan("D");

        System.out.println("----Welcome To The Store------");
        System.out.println(checkout.getCart());
        System.out.println( "Total: " + checkout.total() );

        checkout.cancelScan("A");
        checkout.cancelScan("B");

        System.out.println("------------------------------");
        System.out.println(checkout.getCart());
        System.out.println( "Total: " + checkout.total() );
    }
}