package org.example.util;

import java.util.HashSet;

public class RevokedTokensHelper {

    private final static HashSet<String> revokedTokens = new HashSet<>();

    public static boolean isTokenRevoked(String token) {
        return revokedTokens.contains(token);
    }

    public static void revokeToken(String token) {
        revokedTokens.add(token);
    }

    public static void removeFromRevokedIfContains(String token) {
        revokedTokens.remove(token);
    }

}
