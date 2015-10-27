package net.alexanderlyons.firstlesson.DataObjects;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by PyroticBlaziken on 10/24/2015.
 */
public class Car extends RealmObject {

    @PrimaryKey
    private String uuid;
    private String nickname;
    private String make;
    private String model;
    private int year;
    private double cityMPG;
    private double highwayMPG;
    private RealmList<Trip> trips;
    private double baseMileage;

    public Car() {
        this.uuid = UUID.randomUUID().toString();
        this.trips = new RealmList<Trip>();
        this.baseMileage = 0;
    }

    public Car(String make, String model, int year, double cityMPG, double highwayMPG) {
        this();
        this.make = make;
        this.model = model;
        this.year = year;
        this.cityMPG = cityMPG;
        this.highwayMPG = highwayMPG;
    }

    public Car(String nickname, String make, String model, int year, double cityMPG, double highwayMPG) {
        this(make, model, year, cityMPG, highwayMPG);
        this.nickname = nickname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCityMPG() {
        return cityMPG;
    }

    public void setCityMPG(double cityMPG) {
        this.cityMPG = cityMPG;
    }

    public double getHighwayMPG() {
        return highwayMPG;
    }

    public void setHighwayMPG(double highwayMPG) {
        this.highwayMPG = highwayMPG;
    }

    public RealmList<Trip> getTrips() {
        return trips;
    }

    public void setTrips(RealmList<Trip> trips) {
        this.trips = trips;
    }

    public double getBaseMileage() { return this.baseMileage; }

    public void setBaseMileage(double baseMileage) { this.baseMileage = baseMileage; }
}
