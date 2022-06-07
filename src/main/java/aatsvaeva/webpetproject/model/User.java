package aatsvaeva.webpetproject.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class User {
    @Id
    private final UUID id;
    private final String name;
}
