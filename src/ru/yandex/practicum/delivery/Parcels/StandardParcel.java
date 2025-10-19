package ru.yandex.practicum.delivery.Parcels;

public class StandardParcel extends Parcel {
    private static final int DELIVERY_COST = 2;

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int calculateDeliveryCost() {
        return DELIVERY_COST * getWeight();
    }
}
