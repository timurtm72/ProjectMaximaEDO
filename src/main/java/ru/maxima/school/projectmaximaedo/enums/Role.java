package ru.maxima.school.projectmaximaedo.enums;

public enum Role {
    USER_ROLE("Пользователь"),
    ADMIN_ROLE("Администратор");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
