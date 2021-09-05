package ru.job4j.ood.srp.reports.format;

public class FormatTxt extends OutputFormat {
    String title = "";
    String startRow = "";
    String element = "";
    String endRow = "";
    String end = "";

    @Override
    public void format() {
    }

    public String getTitle() {
        return title;
    }

    public String getStartRow() {
        return startRow;
    }

    public String getElement() {
        return element;
    }

    public String getEndRow() {
        return endRow;
    }

    public String getEnd() {
        return end;
    }
}
