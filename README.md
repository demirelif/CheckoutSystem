# Checkout System 🛒

This is a simple checkout system where you can scan items with predefined prices. It is also possible to add special prices and have discounts. 

## Installation
1. Clone the repository
  ```bash
git clone https://github.com/demirelif/CheckoutSystem.git
```
2. Install Java - The project was developed with Java 22; however it is compatible with Java 17.

## Usage 
Build the project. This project uses Gradle as the building automation tool.

```bash
./gradlew build
```

4. Finally, run the project
./gradlew run 

## Features

### Checkout
Using the checkout system, you can scan items, cancel a scan, and calculate the total. 
In order to scan an item use the following code. 
```bash
Checkout checkout = new Checkout( priceList );
checkout.scan("A");
```
Here, you only need to name of the item, or a simple char. Checkout system automatically matches this string with its database. 
For instance, let's assume we have the following price list. Char A corresponds to the Item A. 
```bash
Item    Unit Price    Special Price
-------------------------------------
A       80             3 for 220
B       35             2 for 55
C       20
D       10
```
In this case, the system adds one from Item A to the cart. 
### PriceList
The table given before represents the PriceList. Here, you can define pricing rules including regular prices and discounted prices. 
```bash
PriceList priceList = new PriceList();
priceList.addItem( new Item("A", 80.0));
```
You can also define SpecialPrices for n amount of items as follows. 
```bash
SpecialOffer specialOffer = new SpecialOffer( 3, 220);
```
To assign any special price to any item, simply use the following method. 
```bash
  priceList.addSpecialOfferToItem( "A", specialOffer );
```
