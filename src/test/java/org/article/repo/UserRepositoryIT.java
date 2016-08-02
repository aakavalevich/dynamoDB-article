package org.article.repo;

import static org.junit.Assert.assertEquals;

import org.article.Application;

import org.article.domain.User;

import org.junit.After;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.SpringApplicationConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringApplicationConfiguration(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void findByUserName() {
        final User user = new User();
        user.setUserName("userName");
        user.setFirstName("firstName");
        user.setFirstName("lastName");
        userRepository.save(user);

        final User actualUser = userRepository.findOne(user.getUserName());
        assertEquals(user, actualUser);
    }

}
