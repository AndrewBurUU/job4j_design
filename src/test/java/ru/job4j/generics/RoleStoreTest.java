package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleIsJunior() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Junior"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Junior");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Junior"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleIsJunior() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Junior"));
        store.add(new Role("1", "Intern"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Junior");
    }

    @Test
    void whenReplaceThenRoleIsMiddle() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Junior"));
        store.replace("1", new Role("1", "Middle"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Middle");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Junior"));
        store.replace("10", new Role("10", "Middle"));
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Junior");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Junior"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleIsJunior() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Junior"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Junior");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Junior"));
        boolean rsl = store.replace("1", new Role("1", "Middle"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Junior"));
        boolean rsl = store.replace("10", new Role("10", "Intern"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Intern"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Junior"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}