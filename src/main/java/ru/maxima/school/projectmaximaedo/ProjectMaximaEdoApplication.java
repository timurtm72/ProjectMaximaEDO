package ru.maxima.school.projectmaximaedo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectMaximaEdoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectMaximaEdoApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
