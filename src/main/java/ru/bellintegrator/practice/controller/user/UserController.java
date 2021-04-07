package ru.bellintegrator.practice.controller.user;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.exceptions.DataNotFoundException;
import ru.bellintegrator.practice.exceptions.SaveException;
import ru.bellintegrator.practice.exceptions.UpdateException;
import ru.bellintegrator.practice.service.user.UserService;
import ru.bellintegrator.practice.view.user.UserListFilter;
import ru.bellintegrator.practice.view.user.UserListItemView;
import ru.bellintegrator.practice.view.user.UserSaveView;
import ru.bellintegrator.practice.view.user.UserUpdateView;
import ru.bellintegrator.practice.view.user.UserView;

@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService userService) {
        this.service = userService;
    }

    @PostMapping("/save")
    public void save(@RequestBody @Valid UserSaveView user) throws SaveException {
        service.save(user);
    }

    @PutMapping("/update")
    public void update(@RequestBody @Valid UserUpdateView user) throws UpdateException {
        service.update(user);
    }

    @PostMapping("/list")
    public List<UserListItemView> users(@RequestBody @Valid UserListFilter filter) {
        return service.findAllUsersByFilter(filter);
    }

    @GetMapping("/{id}")
    public UserView user(@PathVariable Long id) throws DataNotFoundException {
        return service.findById(id);
    }
}

