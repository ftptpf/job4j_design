package ru.job4j.inout.serialization.pojo;


import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarPojo {
    private String brand;
    @XmlAttribute
    private int year;
    @XmlAttribute
    private boolean gas;
    private EnginePojo engine;
    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owner")
    private String[] owners;

    public CarPojo() {

    }

    public CarPojo(String brand, int year, boolean gas, EnginePojo engine, String... owners) {
        this.brand = brand;
        this.year = year;
        this.gas = gas;
        this.engine = engine;
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "CarPojo{"
                + "brand='" + brand + '\''
                + ", year=" + year
                + ", gas=" + gas
                + ", engine=" + engine
                + ", owners=" + Arrays.toString(owners)
                + '}';
    }
}
