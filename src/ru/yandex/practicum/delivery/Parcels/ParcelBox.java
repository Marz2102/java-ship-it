package ru.yandex.practicum.delivery.Parcels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final ArrayList<T> parcels = new ArrayList<>();
    private final int maxWeight;
    private int currentWeight = 0;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcels(T parcel) {
        if (currentWeight + parcel.getWeight() <= maxWeight) {
            parcels.add(parcel);
            currentWeight += parcel.getWeight();
        } else {
            System.out.println("Превышение допустимого веса коробки");
        }
    }

    public List<T> getAllParcels() {
        return Collections.unmodifiableList(parcels);
    }

    public int getBoxSize() {
        return parcels.size();
    }

    public int getCurrentWeight() {
        return currentWeight;
    }
}
