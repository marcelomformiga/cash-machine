package com.gsw.cashmachine.configurarion;

import com.gsw.cashmachine.interceptor.IpHandshakeInterceptor;
import com.gsw.cashmachine.utils.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * A classe WebSocketConfig  defini as configuracoes de conexao do spring websocket
 *
 * @author Eduardo Alves
 * @version 1.0
 */
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(Constants.DESTINATION);
        config.setApplicationDestinationPrefixes(Constants.DESTINATION);
        config.setUserDestinationPrefix(Constants.DESTINATIONS_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(Constants.CHAT_CONECTION).addInterceptors(new IpHandshakeInterceptor()).setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setSendTimeLimit(15 * 1000).setSendBufferSizeLimit(512 * 1024)
                .setMessageSizeLimit(16 * 1024);
    }

}