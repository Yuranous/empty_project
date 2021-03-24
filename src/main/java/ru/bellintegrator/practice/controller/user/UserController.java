package ru.bellintegrator.practice.controller.user;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.bellintegrator.practice.view.wrapper.ResponseWrapper.getErrorResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.service.user.UserService;
import ru.bellintegrator.practice.view.UserView;
import ru.bellintegrator.practice.view.wrapper.ResponseWrapper;

@Api(value = "UserController")
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService userService) {
        this.service = userService;
    }

    @ApiOperation(value = "Добавить новый офис", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public ResponseWrapper addUser(@RequestBody UserView user) {
//        {
//  “officeId”:””, //обязательный параметр
//  “firstName”:””, //обязательный параметр
//  “secondName”:””,
//  “middleName”:””,
//  “position”:”” //обязательный параметр
//  “phone”,””,
//  “docCode”:””,
//  “docName”:””,
//  “docNumber”:””,
//  “docDate”:””,
//  “citizenshipCode”:””,
//  “isIdentified”:”true” //пример
//}
        if (user.getOfficeId() == null || user.getFirstName() == null || user.getPosition() == null) {
            return ResponseWrapper.getErrorResponse("400", "id, firstName, position values must be passed");
        } else {
            if (service.add(user)) {
                return ResponseWrapper.getSuccessResponse("OK");
            }
            else {
                return getErrorResponse("400", "Error during saving");
            }
        }
    }

    @ApiOperation(value = "Обновить организацию", httpMethod = "PUT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PutMapping("/update")
    public ResponseWrapper updateUser(@RequestBody UserView user) {
        if (user.getId() == null || user.getFirstName() == null || user.getPosition() == null) {
            return ResponseWrapper.getErrorResponse("400", "id, firstName, position values must be passed");
        } else {
            if (service.update(user)) {
                return ResponseWrapper.getSuccessResponse("OK");
            } else {
                return ResponseWrapper.getErrorResponse("400", "Error during updating");
            }
        }
    }

    @ApiOperation(value = "Получить список всех пользователей", httpMethod = "GET")
    @GetMapping("/list")
    public ResponseWrapper users(
            @RequestParam Long officeId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String middleName,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String docCode,
            @RequestParam(required = false) String citizenshipCode
            ) {
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();

        if (firstName != null) {
            params.add(new SearchCriteria("firstName", ":", firstName));
        }

        if (lastName != null) {
            params.add(new SearchCriteria("lastName", ":", lastName));
        }

        if (middleName != null) {
            params.add(new SearchCriteria("middleName", ":", middleName));
        }

        if (position != null) {
            params.add(new SearchCriteria("position", ":", position));
        }

        if (docCode != null) {
            params.add(new SearchCriteria("docCode", ":", docCode));
        }

        if (citizenshipCode != null) {
            params.add(new SearchCriteria("citizenshipCode", ":", citizenshipCode));
        }

        List<UserView> users = service.users(params, officeId);

        if (users.isEmpty()) {
            return ResponseWrapper.getErrorResponse("404", "No users found");
        } else {
            return ResponseWrapper.getSuccessResponse(users);
        }
    }

    @ApiOperation(value = "Получить организацию по идентификатору", httpMethod = "GET")
    @GetMapping("/{id}")
    public ResponseWrapper user(@PathVariable Long id) {
        Optional<UserView> result = service.user(id);
        if (result.isPresent()) {
            return ResponseWrapper.getSuccessResponse(result.get());
        } else {
            return ResponseWrapper.getErrorResponse("404", "Can't find user with passed id value");
        }
    }
}

