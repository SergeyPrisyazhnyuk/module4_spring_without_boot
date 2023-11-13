/*
package ru.practicum.user;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Primary
public class UserRepositoryImpl implements UserRepository {
    private static final Map<Long, User> users = new HashMap<>();
    private Long id = 1L;

    public Long setIdReturn() {
        return id++;
    }

    @Override
    public List<User> findAll() {
        return users
                .values()
                .stream()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public User save(User user) {
        Long id = setIdReturn();
        user.setId(id);
        users.put(id, user);
        return user;
    }

    private static List<User> createThreeUsers(int count) {
        for (long id = 1; id <= count; id++) {
            users.put(id, createFakeUser(id));
        }
        return users
                .values()
                .stream()
                .collect(Collectors.toUnmodifiableList());
    }

    private static User createFakeUser(long id) {
        User fakeUser = new User();
        fakeUser.setId(id);
        fakeUser.setEmail("mail" + id + "@example.com");
        fakeUser.setName("Akakiy Akakievich #" + id);
        return fakeUser;
    }

}
*/
