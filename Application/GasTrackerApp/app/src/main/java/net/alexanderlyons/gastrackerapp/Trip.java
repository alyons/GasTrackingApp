package net.alexanderlyons.gastrackerapp;

import com.jjoe64.graphview.series.DataPoint;

import java.util.Date;

/**
 * Created by PyroticBlaziken on 3/28/2015.
 */
public class Trip {

    static int count = 0;
    public int recordID;
    public Date time;
    public double miles;
    public double pricePerGallon;
    public double gallonsPurchased;
    public int carID;

    public Trip(Date someTime, double mi, double price, double gallons, int car) {
        recordID = count;
        time = someTime;
        miles = mi;
        pricePerGallon = price;
        gallonsPurchased = gallons;
        carID = car;
        count += 1;
    }

    public double costPerMile() {
        return this.totalPurchasePrice() / miles;
    }

    public double milesPerGallon() {
        return miles / gallonsPurchased;
    }

    public double totalPurchasePrice() {
        return pricePerGallon * gallonsPurchased;
    }

    public DataPoint toTestDataPoint() {
        return new DataPoint(recordID, milesPerGallon());
    }
}
