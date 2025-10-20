package ru.yandex.practicum.delivery;

import ru.yandex.practicum.delivery.Parcels.FragileParcel;
import ru.yandex.practicum.delivery.Parcels.Parcel;
import ru.yandex.practicum.delivery.Parcels.PerishableParcel;
import ru.yandex.practicum.delivery.Parcels.StandardParcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> trackableParcels = new ArrayList<>();

    public static void main(String[] args) {

        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    updateReportStatus();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 - Обновить статус посылок");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        showListOfParcels();
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                createStandardParcel();
                break;
            case 2:
                createPerishableParcel();
                break;
            case 3:
                createFragileParcel();
                break;
            default:
                System.out.println("Неверный выбор");
        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int totalPrice = 0;
        for (Parcel parcel : allParcels) {
            totalPrice += parcel.calculateDeliveryCost();
        }

        System.out.println(totalPrice);
    }

    private static void updateReportStatus() {
        for (Trackable parcel : trackableParcels) {
            System.out.println("Введите город для обновления статуса:");
            String newLocation = scanner.nextLine();
            parcel.reportStatus(newLocation);
        }
    }

    private static void createStandardParcel() {
        System.out.println("Укажите описание посылки");
        String description = scanner.nextLine();

        System.out.println("Укажите вес посылки (кг)");
        int weight = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Укажите адрес посылки");
        String address = scanner.nextLine();

        System.out.println("Укажите дату отправки посылки");
        int deliveryDate = scanner.nextInt();
        scanner.nextLine();

        allParcels.add(new StandardParcel(description, weight, address, deliveryDate));
    }

    private static void createFragileParcel() {
        System.out.println("Укажите описание посылки");
        String description = scanner.nextLine();

        System.out.println("Укажите вес посылки (кг)");
        int weight = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Укажите адрес посылки");
        String address = scanner.nextLine();

        System.out.println("Укажите дату отправки посылки");
        int deliveryDate = scanner.nextInt();
        scanner.nextLine();

        FragileParcel fragileParcel = new FragileParcel(description, weight, address, deliveryDate);
        allParcels.add(fragileParcel);
        trackableParcels.add(fragileParcel);
    }

    private static void createPerishableParcel() {
        System.out.println("Укажите описание посылки");
        String description = scanner.nextLine();

        System.out.println("Укажите вес посылки (кг)");
        int weight = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Укажите адрес посылки");
        String address = scanner.nextLine();

        System.out.println("Укажите дату отправки посылки");
        int deliveryDate = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Укажите срок годности посылки");
        int timeToLive = scanner.nextInt();
        scanner.nextLine();

        allParcels.add(new PerishableParcel(description, weight, address, deliveryDate, timeToLive));
    }

    private static void showListOfParcels() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1 — Стандартная посылка");
        System.out.println("2 — Скоропортящаяся посылка");
        System.out.println("3 — Хрупкая посылка");
    }

}

