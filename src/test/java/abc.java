import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;

public record abc(
        String a,
        @JsonInclude(NON_ABSENT)
        Optional<String> b
) {

}
