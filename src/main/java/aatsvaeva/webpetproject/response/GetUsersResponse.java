package aatsvaeva.webpetproject.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
public class GetUsersResponse {

    private final boolean hasNext;
    private final String nextCursor;
    private final List<User> items;
}
