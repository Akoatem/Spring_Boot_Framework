package com.akoatem.micro.models;

import com.akoatem.micro.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
@Configuration

// to add data into the database
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
                    Student ako = new Student(
                            "Ako",
                            "ako@gmail.com",
                            LocalDate.of(1999, Month.DECEMBER, 21)
                    );
            Student ben = new Student(
                    "Benson",
                    "ben@gmail.com",
                    LocalDate.of(1974, Month.FEBRUARY, 12)
            );

            Student suzan = new Student(
                    "Suzanna",
                    "suzan233@gmail.com",
                    LocalDate.of(2000, Month.NOVEMBER, 10)
            );
            repository.saveAll(
                    List.of(ako, ben, suzan)
            );

        };
    }
}
