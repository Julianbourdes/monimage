package fr.univtln.bruno.samples;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Log
public class Personne {

    @Min(value = 100)
    @Max(value = 200)
    int id;
    @Size(min = 1, max = 30, message = "Attention le nom [{min},{max}]")
    String name;
    @Size(min = 1, max = 30)
    String firstname;

    public static void m(Optional<Personne> personne) throws Exception {
        System.out.println(personne.orElseThrow(Exception::new).firstname);
    }

    public static void main(String[] args) throws Exception {
        Personne personne = Personne.of(1, "X", "Y");
        Personne personne2 = Personne.builder().id(-2).name("A").firstname("B").build();

        Personne[] personnes = {personne, personne2};

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<Personne>> constraintsViolations = null;
        for (Personne p : personnes) {
            constraintsViolations = validator.validate(p);
            if (!constraintsViolations.isEmpty())
                log.severe(constraintsViolations.toString());

        }

        m(Optional.of(personne));


        log.info(personne.toString());
        log.info(personne2.toString());

    }

}
