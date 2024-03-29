package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenEmptyStringsWithComment() {
        String path = "./data/EmptyStringsWithComment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.getValues().size()).isEqualTo(0);
    }

    @Test
    void whenIncorrectPairNoKey() {
        String path = "./data/IncorrectPairNoKey.properties";
        Config config = new Config(path);
        String errLine = "=Petr Arsentev";
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(errLine)
                .hasMessageContaining("Incorrect pair line");
    }

    @Test
    void whenIncorrectPairNoValue() {
        String path = "./data/IncorrectPairNoValue.properties";
        Config config = new Config(path);
        String errLine = "name=";
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(errLine)
                .hasMessageContaining("Incorrect pair line");
    }

    @Test
    void whenIncorrectPairNoDelim() {
        String path = "./data/IncorrectPairNoDelim.properties";
        Config config = new Config(path);
        String errLine = "namePetr Arsentev";
        assertThatThrownBy(() -> config.load())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(errLine)
                .hasMessageContaining("Incorrect pair line");
   }

    @Test
    void whenIncorrectPairManyDelims() {
        String path = "./data/IncorrectPairManyDelims.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr=Arsentev");
    }

}