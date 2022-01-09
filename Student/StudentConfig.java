package Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {
    @Bean
   CommandLineRunner commandLineRunner(StudentRepository repository){
       return args -> {
           Student mariam = new Student( "Mariam","mariam.jones@gmail.com",
                   LocalDate.of(2000, JANUARY,5)

           );
           Student alex = new Student( "Alex","alex.@gmail.com",
                   LocalDate.of(2007, JANUARY,6)
           );
           repository.saveAll(List.of(mariam,alex)
           );
       };
   }
}
