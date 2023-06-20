package ru.maxima.school.projectmaximaedo.enums;

public enum DocumentType {
    CONTRACT("Контракт"),
    AGREEMENT("Соглашение"),
    APPLICATION("Приложение"),
    ACT("Акт"),
    REFERENCE("Ссылка");
    private final String name;

    DocumentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
