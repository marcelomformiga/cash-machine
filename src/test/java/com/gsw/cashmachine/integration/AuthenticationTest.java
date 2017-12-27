package com.gsw.cashmachine.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsw.cashmachine.authentication.request.AuthenticationRequest;
import com.gsw.cashmachine.authentication.response.AuthenticationResponse;
import com.gsw.cashmachine.config.AbstractApplicationTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by eduardo on 24/07/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthenticationTest extends AbstractApplicationTest {

    @Before
    public void loadContext() {
        super.setUpContext();
    }

    @Test
    public void testLogin() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("admin", "admin");
        String jsonInString = mapper.writeValueAsString(authenticationRequest);
        String token = super.mockMvcPerformResult("/api/auth", jsonInString, MediaType.APPLICATION_JSON_VALUE);
        AuthenticationResponse authenticationResponse = mapper.readValue(token, AuthenticationResponse.class);
        Assert.assertNotNull(authenticationResponse);
    }

    @Test
    public void testBadCredentialsLogin() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("admin", "wrong password");
        String jsonInString = mapper.writeValueAsString(authenticationRequest);
        int status = super.mockMvcLoginPost("/api/auth", jsonInString, MediaType.APPLICATION_JSON_VALUE, status().isUnauthorized());
        Assert.assertEquals(401, status);
    }
}
