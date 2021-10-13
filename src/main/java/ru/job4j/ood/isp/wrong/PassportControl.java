package ru.job4j.ood.isp.wrong;

/**
 * Нарушение принципа ISP. Классы RussianCitizen и ForeignCitizen реализующие данный интерфейс
 * не будут в полной мере использовать объявленные методы.
 * Для RussianCitizen на паспортном контроле по прилету в свою страну будут задействован только метод checkPassport()
 * для ForeignCitizen будут выполняться все три метода:
 * checkPassport(), checkVisa(), checkInvitation().
 * Решением будет создание отдельных интерфейсов для RussianCitizen и ForeignCitizen.
 */
public interface PassportControl {
    boolean checkPassport();
    boolean checkVisa();
    boolean checkInvitation();
}
