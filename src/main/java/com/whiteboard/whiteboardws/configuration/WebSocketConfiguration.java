package com.whiteboard.whiteboardws.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.whiteboard.whiteboardws.handler.WhiteboardWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final static String WHITEBOARD_ENDPOINT = "/whiteboard"; 
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getWhiteboardWebSocketHandler(), WHITEBOARD_ENDPOINT).setAllowedOrigins("*");
    }

    @Bean
    public TextWebSocketHandler getWhiteboardWebSocketHandler()
    {
        return new WhiteboardWebSocketHandler();
    }
}
