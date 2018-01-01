package com.gsw.cashmachine.restapi;

import com.gsw.cashmachine.authentication.request.AuthenticationRequest;
import com.gsw.cashmachine.authentication.request.AuthenticationSocketRequest;
import com.gsw.cashmachine.authentication.response.AuthenticationResponse;
import com.gsw.cashmachine.authentication.security.TokenUtils;
import com.gsw.cashmachine.authentication.service.UserSocketService;
import com.gsw.cashmachine.authentication.user.AuthenticationUser;
import com.gsw.cashmachine.interceptor.StompConnectEvent;
import lombok.extern.log4j.Log4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


/**
 * A classe AuthenticationController é reponsavel por disponiblizar o serviço de autenticacao
 *
 * @author Eduardo Alves
 * @version 1.0
 */
@Log4j
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final Log logger = LogFactory.getLog(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserSocketService socketService;

    @Autowired
    private StompConnectEvent stompConnectEvent;

    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {

        Authentication credentials = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword());

        SecurityContextHolder.getContext().setAuthentication(credentials);

        try {
            AuthenticationUser userDetails = (AuthenticationUser) authenticationManager.authenticate(credentials).getPrincipal();
            String token = this.tokenUtils.generateTokenByUserDetails(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(token, userDetails.getUsername(), userDetails.getAuthority()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(path = "/auth", method = RequestMethod.GET)
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestParam("username") String username) {
        String newToken = this.tokenUtils.generateTokenByUsername(username);
        return new ResponseEntity(new AuthenticationResponse(newToken, username, "ROLE_USER"), HttpStatus.OK);
    }

    @MessageMapping("/join")
    public void join(AuthenticationSocketRequest request, SimpMessageHeaderAccessor headerAccessor) {
        logger.info("Join " + headerAccessor.getSessionId());
        request.setRemoteAddress(headerAccessor.getSessionAttributes().get("ip").toString());
        request.setSessionId(headerAccessor.getSessionId());
        socketService.connectUser(request);
    }

    @MessageMapping("/leave")
    public void leave(AuthenticationSocketRequest request) {
        logger.info("Leave " + request.getSessionId());
        socketService.disconnectUser(request);
    }

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        logger.info("Connected " + headers.getSessionId());
        stompConnectEvent.onApplicationEvent(event);
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        logger.info("Disconnected " + event.getSessionId());
        socketService.disconnectUser(new AuthenticationSocketRequest(event.getSessionId()));
    }
}
