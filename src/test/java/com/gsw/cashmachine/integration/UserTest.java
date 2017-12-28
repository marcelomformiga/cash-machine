package com.gsw.cashmachine.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsw.cashmachine.authentication.request.AuthenticationRequest;
import com.gsw.cashmachine.authentication.response.AuthenticationResponse;
import com.gsw.cashmachine.config.AbstractApplicationTest;
import com.gsw.cashmachine.domain.account.Account;
import com.gsw.cashmachine.domain.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by eduardo on 17/08/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserTest extends AbstractApplicationTest {

    private String token = null;

    @Before
    public void loadContext() {
        super.setUpContext();
    }

    @Before
    public void testLogin() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("admin", "admin");
        String jsonInString = mapper.writeValueAsString(authenticationRequest);
        token = super.mockMvcPerformResult("/api/auth", jsonInString, MediaType.APPLICATION_JSON_VALUE);
        AuthenticationResponse authenticationResponse = mapper.readValue(token, AuthenticationResponse.class);
        Assert.assertNotNull(authenticationResponse);
    }

    @Test
    public void crudTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        User user = new User("thiago", "1234", "thiago@hotmail.com", "ROLE_ADMIN", new Account());
        String jsonInString = mapper.writeValueAsString(user);
        int status = super.mockMvcPerformAuthenticatedPostStatus("/api/user", jsonInString, MediaType.APPLICATION_JSON_VALUE, status().isCreated(), token);
        Assert.assertEquals(201, status);

        String result = super.mockMvcPerformGetRequestParam("/api/user", "username", "thiago");
        user = mapper.readValue(result, User.class);
        Assert.assertNotNull(user);

        user.setUsername("odraude");
        jsonInString = mapper.writeValueAsString(user);
        status = super.mockMvcPerformAuthenticatedPutResult("/api/user", jsonInString, MediaType.APPLICATION_JSON_VALUE, status().isOk(), token);
        Assert.assertEquals(200, status);

        jsonInString = mapper.writeValueAsString(user);
        status = super.mockMvcPerformAuthenticatedDeleteResult("/api/user", jsonInString, MediaType.APPLICATION_JSON_VALUE, status().isOk(), token);
        Assert.assertEquals(200, status);
    }

    @Test
    public void getAllUsersTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String result = super.mockMvcPerformGetAll("/api/users");
        List<User> userList = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(List.class, User.class));
        Assert.assertNotNull(userList);
    }
}
