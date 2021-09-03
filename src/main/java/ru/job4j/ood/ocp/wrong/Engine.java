package ru.job4j.ood.ocp.wrong;

public class Engine {
    private int power;

    public class CarEngine {
        public Double calculateHorsepower() {
            return power / 1.3596;
        }
    }

    public class ElectricalEngine {
        public Double calculateAmperes() {
            return power / 380.0;
        }
    }
}
