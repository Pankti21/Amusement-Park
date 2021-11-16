package com.team5.HAPark.Cart;

import com.team5.HAPark.Ride.Ride;

import java.util.ArrayList;
import java.util.ListIterator;
public class CartSummary {
   // Ride ride;
        ArrayList<Ride> ride;
        double totalAmount;
        double payableAmount;
        double discount;
        double tax;
        String coupon;
        void CartItems() {
            this.ride = new ArrayList<Ride>();
            this.coupon = "";
            this.totalAmount = 0;
            this.payableAmount = 0;
            this.discount = 0;
            this.tax = 0;
        }
        public void addToCart(Ride ride) {
            this.ride.add(ride);
        }
        public void showCart() {
            ListIterator<Ride> iterator = ride.listIterator();
            while(iterator.hasNext()) {
                Ride ride1 = iterator.next();
                System.out.println(ride1);
            }
        }
        public void removeFromCart(Ride i) {
            ListIterator<Ride> iterator1 = ride.listIterator();
            while(iterator1.hasNext()) {
                Ride item2 = iterator1.next();
                if (item2.getName().equals(i.getName())) {
                    this.ride.remove(i);
                    break;
                }
            }
        }
        public double getTotalAmount() {
            ListIterator<Ride> iterator2 = ride.listIterator();
            this.totalAmount = 0;
            while(iterator2.hasNext()) {
                Ride ride3 = iterator2.next();
                this.totalAmount = this.totalAmount + (ride3.getUnitPrice() * 15);
            }
            return this.totalAmount;
        }

        public void printInvoice() {
            ListIterator<Item> iterator3 = item.listIterator();
            while(iterator3.hasNext()) {
                Item item4 = iterator3.next();
                System.out.print(item4.getProductName() + "\t");
                System.out.print(item4.getQuantity() + "\t");
                System.out.print(item4.getUnitPrice() + "\t");
                System.out.println(item4.getUnitPrice() * item4.getQuantity());
            }
            System.out.println("\t\t\t" + "Total    : " + this.getTotalAmount());
            this.applyCoupon(this.coupon);

        }
    }
}
