package com.small.cell.server.session;

import org.apache.mina.core.session.IoSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private final static Map<String, IoSession> sessions = new ConcurrentHashMap<String, IoSession>();
    private final static SessionManager manager = new SessionManager();

    public static SessionManager getManager() {
        return manager;
    }

    private SessionManager() {}

    public void set(String mac,IoSession ioSession) {
        if (ioSession == null) return;

        sessions.put(mac, ioSession);
    }
    
    public IoSession get(String mac) {
       
        return sessions.get(mac);
    }

    public void remove(IoSession ioSession) {
        if (ioSession == null) return;

        sessions.remove(ioSession);
    }

    public void removeAll() {
        if (sessions.size() == 0) return;

        sessions.clear();
    }

    public void update(Object message) {
        for (IoSession ioSession: sessions.values()) {
            ioSession.write(message);
        }
    }
}