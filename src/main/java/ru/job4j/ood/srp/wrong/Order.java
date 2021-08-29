package ru.job4j.ood.srp.wrong;

public class Order {
    private int id;
    private String type;
    private int price;

    private boolean checkMoney() {
        return true;
    }

    private void receiveOrder(int id, String type, int price) {

    }

    private boolean startFulfillOrder() {
        if (checkMoney()) {
            // начинаем выполнять заказ
            sendConfirmation();
        }
        return true;
    }

    private void sendConfirmation() {
        // отправляем сообщение заказчику на email

    }
}
