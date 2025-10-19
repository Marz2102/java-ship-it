package ru.yandex.practicum.delivery.Parcels;

import ru.yandex.practicum.delivery.Trackable;

public class FragileParcel extends Parcel implements Trackable {
    private static final int DELIVERY_COST = 4;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + getDescription() + " обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка " + getDescription() + " изменила местоположение на " + newLocation);
    }

    @Override
    public int calculateDeliveryCost() {
        return DELIVERY_COST * getWeight();
    }
}
