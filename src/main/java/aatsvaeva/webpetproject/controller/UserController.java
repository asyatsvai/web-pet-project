package aatsvaeva.webpetproject.controller;

import aatsvaeva.webpetproject.response.GetUsersResponse;
import aatsvaeva.webpetproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(path = "/user",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @GetMapping
    public GetUsersResponse getUsers(@RequestParam(value = "cursor", required = false) String cursor,
                                     @RequestParam(value = "limit", required = false) Integer limit) {
        return userService.getUsers(cursor, limit);
    }

    @RequestMapping(path = "/user/department/{department}/min-age",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @GetMapping
    public Integer getMinUserAgeByDepartment(@PathVariable("department") String department) {
        return userService.getUserMinAgeByDepartment(department);
    }
}
