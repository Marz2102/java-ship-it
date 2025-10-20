package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.yandex.practicum.delivery.Parcels.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class DeliveryCostTest {
    private ArrayList<FragileParcel> fragileParcels = new ArrayList<>();
    private ArrayList<StandardParcel> standardParcels = new ArrayList<>();
    private ArrayList<PerishableParcel> perishableParcels = new ArrayList<>();

    @BeforeEach
    void setUp() {
        fragileParcels.add(new FragileParcel("Хрупкая посылка 1", 10, "Москва", 20));
        standardParcels.add(new StandardParcel("Стандартная посылка 1", 5, "Санкт-Петербург", 15));
        perishableParcels.add(new PerishableParcel("Скоропортящаяся посылка 1", 3, "Казань", 7, 14));
    }

    @Test
    public void shouldReturn40WhenFragileAnd10Kg() {
        assertEquals(40, fragileParcels.getFirst().calculateDeliveryCost());
    }

    @Test
    public void shouldReturn10WhenStandardAnd5Kg() {
        assertEquals(10, standardParcels.getFirst().calculateDeliveryCost());
    }

    @Test
    public void shouldReturn9WhenPerishableAnd3Kg() {
        assertEquals(9, perishableParcels.getFirst().calculateDeliveryCost());
    }

    @Test
    public void shouldReturn59ForAllParcels() {
        int fragileParcelPrice = fragileParcels.getFirst().calculateDeliveryCost();
        int standardParcelPrice = standardParcels.getFirst().calculateDeliveryCost();
        int perishableParcelPrice = perishableParcels.getFirst().calculateDeliveryCost();
        assertEquals(59, fragileParcelPrice + standardParcelPrice + perishableParcelPrice);
    }

    @Test
    public void shouldReturnFalseWhenExpiryDateLessThenCurrentDate() {
        assertTrue(perishableParcels.getFirst().isExpired(22));
    }

    @Test
    public void shouldReturnFalseWhenExpiryDateIsEqualToCurrentDate() {
        assertFalse(perishableParcels.getFirst().isExpired(21));
    }

    @Test
    public void shouldReturnTrueWhenExpiryDateMoreThenCurrentDate() {
        assertFalse(perishableParcels.getFirst().isExpired(20));
    }

    @Test
    public void shouldAddNewParcelWhenTotalWeightLessThenBoxWeight() {
        ParcelBox<StandardParcel> parcelBox = new ParcelBox<StandardParcel>(25);
        parcelBox.addParcels(standardParcels.getFirst());
        parcelBox.addParcels(new StandardParcel("Стандартная посылка 2", 10, "Краснодар", 17));
        assertAll(
                () -> assertEquals(parcelBox.getBoxSize(), 2),
                () -> assertEquals(parcelBox.getCurrentWeight(), 15)
        );
    }

    @Test
    public void shouldAddNewParcelWhenTotalWeightIsEqualThenBoxWeight() {
        ParcelBox<StandardParcel> parcelBox = new ParcelBox<StandardParcel>(25);
        parcelBox.addParcels(standardParcels.getFirst());
        parcelBox.addParcels(new StandardParcel("Стандартная посылка 2", 20, "Краснодар", 17));
        assertAll(
                () -> assertEquals(parcelBox.getBoxSize(), 2),
                () -> assertEquals(parcelBox.getCurrentWeight(), 25)
        );
    }

    @Test
    public void shouldNotAddNewParcelWhenTotalWeightMoreThenBoxWeight() {
        ParcelBox<StandardParcel> parcelBox = new ParcelBox<StandardParcel>(25);
        parcelBox.addParcels(standardParcels.getFirst());
        parcelBox.addParcels(new StandardParcel("Стандартная посылка 2", 21, "Краснодар", 17));
        assertAll(
                () -> assertEquals(parcelBox.getBoxSize(), 1),
                () -> assertEquals(parcelBox.getCurrentWeight(), 5)
        );
    }

}
