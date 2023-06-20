package ru.maxima.school.projectmaximaedo.model;

import java.util.List;

public class TemplateFiledNumbers {
    private List<Integer> numbers;

    public TemplateFiledNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
