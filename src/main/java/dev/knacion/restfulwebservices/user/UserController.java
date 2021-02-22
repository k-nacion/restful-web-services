package dev.knacion.restfulwebservices.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    //GET /users
    //retrieveAllUsers
    @GetMapping
    public List<User> getAllUsers(){
        return userDaoService.findAll();
    }


    //GET /users/{id}
    //retrieveUser(int id)
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        User user = userDaoService.findOne(id);

        if (user == null)
            throw new UserNotFoundException("id-" + id);

        return user;
    }

    //POST /user
    @PostMapping
    public ResponseEntity insertUser(@RequestBody User user) {
        userDaoService.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        ResponseEntity.BodyBuilder created = ResponseEntity.created(uri);
        return created.build();
    }

}
