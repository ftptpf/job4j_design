package ru.job4j.ood.lsp.products;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.lsp.products.product.Food;
import ru.job4j.ood.lsp.products.product.Meat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class ControlQualityTest {
    private LocalDate today;
    private LocalDate meat1ExpiryDate, meat1CreateDate;
    private LocalDate meat2ExpiryDate, meat2CreateDate;
    private LocalDate meat3ExpiryDate, meat3CreateDate;
    Food meat1, meat2, meat3;
    ControlQuality controlQuality;

    @Before
    public void initObjects() {
        today = LocalDate.now();
        meat1CreateDate = today.minusDays(1);
        meat1ExpiryDate = today.plusDays(10);
        meat2CreateDate = today.minusDays(10);
        meat2ExpiryDate = today.plusDays(10);
        meat3CreateDate = today.minusDays(4);
        meat3ExpiryDate = today.plusDays(10);
        meat1 = new Meat("red", meat1CreateDate, meat1ExpiryDate, new BigDecimal(50), 0);
        meat2 = new Meat("red", meat2CreateDate, meat2ExpiryDate, new BigDecimal(50), 0);
        meat3 = new Meat("red", meat3CreateDate, meat3ExpiryDate, new BigDecimal(50), 0);
        controlQuality = new ControlQuality();
    }


    @Test
    public void moveToWarehouse() {
        controlQuality.moveToStorage(meat1);
        StringBuilder expected = new StringBuilder()
                .append("[Food{name='")
                .append(controlQuality.getFromWarehouse().get(0).getName())
                .append("', expiryDate=")
                .append(controlQuality.getFromWarehouse().get(0).getCreateDate())
                .append("");
        assertEquals(expected.toString(), controlQuality.getFromWarehouse());
        assertEquals(expected.toString(), controlQuality.getFromShop());
        assertEquals(expected.toString(), controlQuality.getFromTrash());
    }

    @Test
    public void moveToShop() {

    }

    @Test
    public void moveToShopDiscount() {

    }

    @Test
    public void moveToTrash() {

    }



    // ---------------

    // ControlQuality содержит List хранилищ... и содержит два публичных метода,
    // один добавляет хранилище, а именно интерфейс хранилища в List,
    // а второй метод проверяет "качество" и возвращает какому же хранилищу принадлежит данный продукт


    // Я стратегию использовал создав интерфейс IStorage в котором был метод проверки соответствия продукта на текущую дату boolean isSuitable(IFood food, Date currentDate);
    // и в ControlQuality вызывал этот метод, а в каждом конкретном хранилище реализовывал проверку соответствия

    // ----------------

    // у нас есть много разных тиаов хранилищь, у нас есть много разных разновидностей еды, и унас есть контроль качества
    // ну все просто.. контроль качества проверяет еду на срок годности

    // ----------------
    // Получается, что у каждого storage будет метод, пытающийся принять в себя продукт
    // и тогда прогоняя food через все storage они будут добавляться куда-то в зависимости от условий, описанных в каждом из storage

    // Ага, это и есть паттерн стратегия, т.к. ты вынес принятие решений из основного потока в объекты storage.
    // Плюсы - нет простыни if, можешь создать новый storage и передавать в метод без изменения самого метода
}