package ru.epatko.reportutil.repository;

import ru.epatko.reportutil.model.Token;

import java.util.HashMap;
import java.util.Map;

public class TokenRepository {

    private final Map<String, Token> storage = new HashMap<>();
    private final static String KEY = "token";

    public Token find() {
        return storage.get(KEY);
    }

    public Token save(Token token) {
        storage.put(KEY, token);
        return null;
    }

    public void deleteToken() {
        storage.remove(KEY);
    }
}
