package ru.job4j.inout.serialization.pojo;

import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "engine")
public class EnginePojo {
    @XmlAttribute
    private int power;

    public EnginePojo() {

    }

    public EnginePojo(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "EnginePojo{"
                + "power=" + power
                + '}';
    }
}
