package ru.job4j.ood.tdd.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    @Ignore
    public void produce() {
        Generator generator = new QuestionGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan Ivanov");
        map.put("subject", "you");
        String result = generator.produce(template, map);
        assertEquals("I am a Ivan Ivanov, Who are you?", result);
    }

    /**
     * Тест учитывает что в шаблоне есть ключи, которых нет в карте и кидает исключение.
     */
    @Test(expected = Exception.class)
    @Ignore
    public void moreKeyInTemplate() {
        Generator generator = new QuestionGenerator();
        String template = "I am a ${name}, Who are ${subject}? I live in ${city}.";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan Ivanov");
        map.put("subject", "you");
        String result = generator.produce(template, map);
    }

    /**
     * Тест учитывает что в карте есть лишние ключи и кидает исключение.
     */
    @Test(expected = Exception.class)
    @Ignore
    public void mapHasMoreKey() {
        Generator generator = new QuestionGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivan Ivanov");
        map.put("subject", "you");
        map.put("city", "Brest");
        String result = generator.produce(template, map);
    }
}