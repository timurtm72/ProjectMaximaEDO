package ru.maxima.school.projectmaximaedo.enums;

public enum FieldType {
    /**
     * однострочный текст
     */
    TEXT_SINGLE_LINE("Текст однострочный"),
    /**
     * число
     */
    NUMBER("Число"),
    /**
     * многострочный текст
     */
    TEXT_MULTILINE("Текст многострочный"),
    /**
     *выпадающий список
     */
    DROP_DOWN_LIST("Выпадающий список");

    private final String name;

    FieldType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
