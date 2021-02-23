package dev.knacion.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }

    private static int userCount = users.size();

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users)
            if (user.getId() == id)
                return user;

        return null;
    }

    public User findOne(User user) {
        for (User x : users) {
            if (x.equals(user))
                return x;
        }

        return null;
    }

    public User findOne(int id, User user) {
        if (user == null) {
            return findOne(id);
        }

        for (User x : users) {
            if (x.getId() == id && x.equals(user)) {
                return x;
            }
//            else
//                throw new UserMismatchException();
        }

        return null;
    }

    public int deleteUser(User user) {
        if (user != null) {
            users.remove(user);
            return 1;
        }

        return -1;
    }

    public User deleteUserById(int id) {
        Iterator<User> iterator = users.iterator();
        User user = null;
        while (iterator.hasNext()) {
            user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
        iterator.forEachRemaining(user1 -> users.add(user1));
        return user;
    }
}
