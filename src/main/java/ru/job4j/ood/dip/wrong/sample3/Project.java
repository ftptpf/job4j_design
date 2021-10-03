package ru.job4j.ood.dip.wrong.sample3;

/**
 * Нарушение принципа DIP. Класс Shop зависит от объектов реализующих классы разработчиков JavaDeveloper и JavaScriptSDeveloper.
 * Решением может быть выделение для них общего интерфейса чтобы зависимость была не от объектов, а от интерфейса.
 */
public class Project {
    JavaDeveloper javaDeveloper = new JavaDeveloper();
    JavaScriptSDeveloper javaScriptSDeveloper = new JavaScriptSDeveloper();

    public void doJob() {
        javaDeveloper.writeCode();
        javaScriptSDeveloper.writeCode();
    }
}
