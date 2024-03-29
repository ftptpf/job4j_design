package ru.job4j.control1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void diff() {
        Analize analize = new Analize();
        List<User> previous = new ArrayList<>();
        previous.add(new User(1, "Roy"));
        previous.add(new User(2, "Tom"));
        previous.add(new User(3, "Din"));
        previous.add(new User(4, "Bill"));
        previous.add(new User(5, "Garri"));

        List<User> current = new ArrayList<>();
        current.add(new User(1, "Roy"));
        current.add(new User(2, "Inga"));
        current.add(new User(3, "Din"));

        current.add(new User(5, "Zoya"));
        current.add(new User(6, "Nika"));

        Info rsl = analize.diff(previous, current);

        assertThat(rsl.getChanged(), is(2));
        assertThat(rsl.getAdded(), is(1));
        assertThat(rsl.getDeleted(), is(1));
    }
}
