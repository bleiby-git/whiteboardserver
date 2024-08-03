package com.whiteboard.whiteboardws.handler;

import java.util.ArrayList;
import java.util.List;

//import javax.swing.text.html.AccessibleHTML.TextElementInfo.TextAccessibleContext;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WhiteboardWebSocketHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> webSocketSessions = new ArrayList<WebSocketSession>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
       for(WebSocketSession webSocketSession: webSocketSessions)
       {
            if(!session.getId().equals(webSocketSession.getId()))
            {
                webSocketSession.sendMessage(message);
            }
       }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session);
    }
}
