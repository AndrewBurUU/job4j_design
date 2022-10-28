package ru.job4j.ood.template;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled
class GeneratorTest {

    @Test
    public void whenFillTemplateThenGetMessage() {
        String phrase = "I am a ${name}, Who are ${subject}?";
        Generator generator = new GenerateNameSubject();
        Map<String, String> mapNameSubject = Map.of(
                "name", "Peter",
                "subject", "you");
        String rsl = generator.produce(phrase, mapNameSubject);
        String expected = "I am a Peter, Who are you?";
        assertThat(rsl).isEqualTo(expected);
    }

    @Test
    public void whenTemplateKeyIsAbsentInMapThenGetException() {
        String phrase = "I am a ${surname}, Who are ${subject}?";
        Generator generator = new GenerateNameSubject();
        Map<String, String> mapNameSubject = Map.of(
                "name", "Peter",
                "subject", "you");
       assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(phrase, mapNameSubject);
        });
    }

    @Test
    public void whenTemplateKeyLessMapKeyThenGetException() {
        String phrase = "I am a ${name}, Who are ${subject}?";
        Generator generator = new GenerateNameSubject();
        Map<String, String> mapNameSubject = Map.of(
                "name", "Peter",
                "subject", "you",
                "details", "comment");
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(phrase, mapNameSubject);
        });
    }

    @Test
    public void whenNoTemplateKeyThenGetException() {
        String phrase = "I am a Peter, Who are you?";
        Generator generator = new GenerateNameSubject();
        Map<String, String> mapNameSubject = Map.of(
                "name", "Peter",
                "subject", "you",
                "details", "comment");
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(phrase, mapNameSubject);
        });
    }

    @Test
    public void whenNoMapKeyThenGetException() {
        String phrase = "I am a ${name}, Who are ${subject}?";
        Generator generator = new GenerateNameSubject();
        Map<String, String> mapNameSubject = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(phrase, mapNameSubject);
        });
    }

}