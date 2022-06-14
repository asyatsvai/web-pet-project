package aatsvaeva.webpetproject.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class User {

    private final UUID id;
    private final String name;
    private final String surname;
    private final Integer age;
    private final String department;
    private final String jobPosition;
    private final LocalDate startPeriod;
}
