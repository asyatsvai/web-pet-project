package aatsvaeva.webpetproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Builder
@ToString
public class UserDto {

    private final UUID id;
    private final Integer position;
    private final String name;
    private final String surname;
    private final Integer age;
    private final String department;
    private final String jobPosition;
    private final LocalDate startPeriod;
}
