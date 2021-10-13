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

    /**
     * Проверяем получены ли деньги. Начинаем выполнять заказ. Отправляем подтверждение на email.
     * @return
     */
    private boolean startFulfillOrder() {
        if (checkMoney()) {
            sendConfirmation();
        }
        return true;
    }

    /**
     * Отправляем сообщение заказчику на email.
     */
    private void sendConfirmation() {
    }
}
