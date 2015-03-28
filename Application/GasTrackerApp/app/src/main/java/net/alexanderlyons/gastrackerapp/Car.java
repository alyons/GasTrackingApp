package net.alexanderlyons.gastrackerapp;

/**
 * Created by PyroticBlaziken on 3/28/2015.
 */
public class Car {
    public int carID;
    public int year;
    public String make;
    public String model;
    public double cityMPG;
    public double highwayMPG;
    public String name;
    public String description;

    public Car() {
        carID = 0;
        year = 2015;
        make = "Scion";
        model = "FR-S";
        cityMPG = 22.0;
        highwayMPG = 30.0;
        name = "Vivian";
        description = "My black Scion FR-S.";
    }
}
