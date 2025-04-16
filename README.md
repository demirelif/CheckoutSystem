# Checkout System 🛒

This is a simple checkout system where you can scan items with predefined prices. It is also possible to add special prices and have discounts. 

## Installation
Clone the repository
  ```bash
git clone https://github.com/demirelif/CheckoutSystem.git
```
Install Java — the project was developed with Java 22, but it is compatible with Java 17.

## Run 
Build the project. This project uses Gradle as the building automation tool.

```bash
./gradlew build
```
Finally, run the project
```bash
./gradlew run
```

## Tests 
To run the JUnit tests, simply use the following. You can see the test report at `build/reports/tests/test/index.html`
```bash
./gradlew test
```
## Features

### Checkout
In order to use the system, you can use main class and create a `PriceList` first, and then pass it to the `Checkout` to create a new one. 

Using the checkout system, you can scan items, cancel a scan, and calculate the total. 
To scan an item use the following code. 
```java
Checkout checkout = new Checkout( priceList );
checkout.scan("A");
```
And you can cancel the scan with the following.
```java
checkout.cancelScan("A");
```
Here, you only need the name of the item, or a simple char. `Checkout` system automatically matches this string with its database. 
For instance, let's assume we have the following price list. `Char A` corresponds to the `Item A`. 
```bash
Item    Unit Price    Special Price
-------------------------------------
A       80             3 for 220
B       35             2 for 55
C       20
D       10
```
In this case, the system adds one from `Item A` to the cart. 
### PriceList
The table above represents the `PriceList`. Here, you can define pricing rules including regular prices and discounted prices. 
```java
PriceList priceList = new PriceList();
priceList.addItem( new Item("A", 80.0));
```
You can also define `SpecialOffer`s for n items as follows. 
```java
SpecialOffer specialOffer = new SpecialOffer( n, specialPrice );
```
To assign any special price to any item, simply use the following method. 
```java
priceList.addSpecialOfferToItem( "A", specialOffer );
```
You can add multiple special offers for an item. The checkout system will apply the deals from most to least in terms of number of items. 
