package ru.maxima.school.projectmaximaedo.enums;

public enum CredentialType {
    /**
     * физическое лицо
     */
    NATURAL_PERSON("Физическое лицо"),
    /**
     * юридическое лицо
     */
    LEGAL_PERSON("Юридическое лицо");

    private final String name;

    CredentialType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
