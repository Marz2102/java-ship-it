package ru.yandex.practicum.delivery.Parcels;

public class PerishableParcel extends Parcel {
    private static final int DELIVERY_COST = 3;
    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress,
                            int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        return getSendDay() + timeToLive > currentDay;
    }

    @Override
    public int calculateDeliveryCost() {
        return DELIVERY_COST * getWeight();
    }
}
