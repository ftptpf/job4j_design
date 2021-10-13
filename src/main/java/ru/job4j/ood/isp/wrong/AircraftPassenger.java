package ru.job4j.ood.isp.wrong;

/**
 * Нарушение принципа ISP. Классы Economy и Business реализующие данный интерфейс
 * не будут в полной мере использовать объявленные методы.
 * Для Economy методы chooseAlcohol() и chooseSpecialFood() не должны быть доступны.
 * Решением будет создание отдельных интерфейсов для Economy и Business классов.
 */
public interface AircraftPassenger {
    void stewardessCall();
    void chooseFood();
    void chooseAlcohol();
    void chooseSpecialFood();
}
