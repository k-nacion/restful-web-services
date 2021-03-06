package dev.knacion.restfulwebservices.user;


import dev.knacion.restfulwebservices.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.ControllerEntityLinksFactoryBean;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public List<User> getAllUsers() {
        List<User> all = userDaoService.findAll();

        RepresentationModel<?> of = RepresentationModel.of(all);

        all.forEach(user -> {
            if (!user.hasLink(LinkRelation.of("user-link")))
                user.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUser(user.getId()))
                        .withRel(LinkRelation.of("user-link"))
                );
        });

        return all;

    }


    //GET /users/{id}
    //retrieveUser(int id)
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        User user = userDaoService.findOne(id);

        if (user == null)
            throw new UserNotFoundException("id-" + id);


        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers

        if (!user.hasLink(LinkRelation.of("all-user")))
            user.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getAllUsers())
                .withRel("all-user"));


        return user;
    }

    //POST /user
    @PostMapping
    public ResponseEntity insertUser(@Valid @RequestBody User user) {
        userDaoService.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        ResponseEntity.BodyBuilder created = ResponseEntity.created(uri);

        return created.build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Integer id, @RequestBody @Nullable User user) {
        User one = userDaoService.findOne(id, user);
        if (one != null)
            userDaoService.deleteUserById(id);
        else
            throw new UserNotFoundException("id=" + id);

        return ResponseEntity.ok(one);
    }

}
