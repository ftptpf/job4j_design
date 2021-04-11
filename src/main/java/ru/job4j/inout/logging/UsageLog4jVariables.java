package ru.job4j.inout.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UsageLog4jVariables {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4jVariables.class.getName());

    public static void main(String[] args) {
        String name = "Ivan Ivanov";
        int age = 33;
        String city = "Krasnoyarsk";
        String street = "Lenina str.";
        int flat = 12;
        int passport = 896656562;
        long distance = 333243423333332334L;
        char ch = 80;
        double p = 3.14;
        LOG.debug("User info name: {}, age: {} ", name, age);
        LOG.debug("Address: {} , {}, {}", city, street, flat);
        LOG.debug("Passport: {}", passport);
        LOG.debug("Distance from NY to Krasnoyarsk: {} miles", distance);
        LOG.debug("The: {} is equals to {}", ch, p);
    }
}
