package net.alexanderlyons.firstlesson;

import java.util.Date;

/**
 * Created by PyroticBlaziken on 10/18/2015.
 */
public class Trip {
    private Date date;
    private double distance;
    private double fuelCost;
    private double fuelPurchased;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(double fuelCost) {
        this.fuelCost = fuelCost;
    }

    public double getFuelPurchased() {
        return fuelPurchased;
    }

    public void setFuelPurchased(double fuelPurchased) {
        this.fuelPurchased = fuelPurchased;
    }

    public Trip(Date date, double distance, double fuelCost, double fuelPurchased) {
        this.date = date;
        this.distance = distance;
        this.fuelCost = fuelCost;
        this.fuelPurchased = fuelPurchased;
    }

    public double milesPerGallon() { return distance / fuelPurchased; }

    public double totalPrice() { return fuelCost * fuelPurchased; }

    public double costPerMile() { return totalPrice() / distance; }
}
