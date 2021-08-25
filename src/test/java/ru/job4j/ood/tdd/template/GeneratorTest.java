package ru.job4j.ood.tdd.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void produce() {
        Generator generator = new QuestionGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        String name = "Petr Arsentev";
        String subject = "you";
        Map<String, String> map = new HashMap<>();
        map.put(name, subject);
        String result = generator.produce(template,map);
        assertEquals("I am a Petr Arsentev, Who are you?", result);
    }

    @Test(expected = Exception.class)
    public void mapHasMoreKey() {
        Generator generator = new QuestionGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("Petr Arsentev", "you");
        map.put("Ivan Ivanov", "they");
        String result = generator.produce(template,map);
        assertEquals("I am a Petr Arsentev, Who are you?", result);
    }


}