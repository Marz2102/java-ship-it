package ru.yandex.practicum.delivery.Parcels;

public abstract class Parcel {
    private final int deliveryCost;
    private final String description;
    private final int weight;
    private final String deliveryAddress;
    private final int sendDay;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay, int deliveryCost) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
        this.deliveryCost = deliveryCost;
    }

    public void packageItem() {
        System.out.println("Посылка " + getDescription() + " упакована");
    }

    public void deliver() {
        System.out.println("Посылка " + getDescription() + " доставлена по адресу " + getDeliveryAddress());
    }

    public int calculateDeliveryCost() {
        return deliveryCost * getWeight();
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }

}
