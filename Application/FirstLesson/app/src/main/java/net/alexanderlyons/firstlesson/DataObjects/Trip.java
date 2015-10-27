package net.alexanderlyons.firstlesson.DataObjects;

import android.content.Context;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import java.util.UUID;

/**
 * Created by PyroticBlaziken on 10/18/2015.
 */
public class Trip extends RealmObject {

    @PrimaryKey
    private String uuid;
    private Date date;
    private double distance;
    private double fuelCost;
    private double fuelPurchased;

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }

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

    public Trip() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Trip(Date date, double distance, double fuelCost, double fuelPurchased) {
        this();
        this.date = date;
        this.distance = distance;
        this.fuelCost = fuelCost;
        this.fuelPurchased = fuelPurchased;
    }
}
