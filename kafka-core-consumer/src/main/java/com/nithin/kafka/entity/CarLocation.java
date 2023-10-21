package com.nithin.kafka.entity;

public class CarLocation {

    private String carId;

    private long timeStamp;

    private int distance;

    public CarLocation(String carId, long timeStamp, int distance) {
        this.carId = carId;
        this.timeStamp = timeStamp;
        this.distance = distance;
    }

    public CarLocation(){

    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "CarLocation{" +
                "carId='" + carId + '\'' +
                ", timeStamp=" + timeStamp +
                ", distance=" + distance +
                '}';
    }
}
