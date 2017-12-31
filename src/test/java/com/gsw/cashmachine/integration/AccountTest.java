package com.gsw.cashmachine.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsw.cashmachine.authentication.request.AuthenticationRequest;
import com.gsw.cashmachine.authentication.request.AccountRequest;
import com.gsw.cashmachine.authentication.response.AuthenticationResponse;
import com.gsw.cashmachine.authentication.response.AccountResponse;
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
 * Created by eduardo on 17/08/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountTest extends AbstractApplicationTest {

    private String token = null;

    @Before
    public void loadContext() {
        super.setUpContext();
    }

    @Before
    public void testLogin() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("eduardo", "123456789");
        String jsonInString = mapper.writeValueAsString(authenticationRequest);
        token = super.mockMvcPerformResult("/api/auth", jsonInString, MediaType.APPLICATION_JSON_VALUE);
        AuthenticationResponse authenticationResponse = mapper.readValue(token, AuthenticationResponse.class);
        Assert.assertNotNull(authenticationResponse);
    }

    @Test
    public void cashoutTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        AccountRequest accountRequest = new AccountRequest(650.00, "eduardo");
        String jsonInString = mapper.writeValueAsString(accountRequest);
        String result = super.mockMvcPerformAuthenticatedPostResult("/api/cash", jsonInString, MediaType.APPLICATION_JSON_VALUE, status().isOk(), token);
        AccountResponse response = mapper.readValue(result, AccountResponse.class);
        Assert.assertEquals("SUCCESS", response.getMessage());
    }
}
