"use client";

import React, { createContext, ReactNode, useContext, useEffect, useState, } from "react";
import keycloak from "@/lib/keycloak";
import Keycloak from "keycloak-js";

type AuthContextType = {
  keycloak: Keycloak | null;
  initialized: boolean;
  authenticated: boolean;
  token?: string | undefined;
  login: () => void;
  logout: () => void;
};

const AuthContext = createContext<AuthContextType>({
  keycloak: null,
  initialized: false,
  authenticated: false,
  login: () => {},
  logout: () => {},
});

export const useAuth = () => useContext(AuthContext);

type Props = {
  children: ReactNode;
};

export const KeycloakProvider = ({ children }: Props) => {
  const [initialized, setInitialized] = useState(false);
  const [authenticated, setAuthenticated] = useState(false);
  const [token, setToken] = useState<string | undefined>(undefined);

  useEffect(() => {
    // Init Keycloak (PKCE is used automatically with S256 when configured in KC)
    keycloak
      .init({
        onLoad: "check-sso", // or "login-required" if you want hard redirect
        checkLoginIframe: false,
        pkceMethod: "S256", // important: matches Keycloak client config
        redirectUri: window.location.origin, // or a specific callback URL
      })
      .then((auth) => {
        setInitialized(true);
        setAuthenticated(!!auth);
        setToken(keycloak.token);

        // refresh token before it expires
        const refreshInterval = setInterval(() => {
          keycloak
            .updateToken(60) // refresh if < 60s left
            .then((refreshed) => {
              if (refreshed && keycloak.token) {
                setToken(keycloak.token);
              }
            })
            .catch(() => {
              console.warn("Failed to refresh token, logging out");
              keycloak.logout();
            });
        }, 30000);

        return () => clearInterval(refreshInterval);
      })
      .catch((err) => {
        console.error("Keycloak init error", err);
        setInitialized(true);
        setAuthenticated(false);
      });
  }, []);

  const login = () => keycloak.login();
  const logout = () =>
    keycloak.logout({ redirectUri: window.location.origin });

  return (
    <AuthContext.Provider
      value={{ keycloak, initialized, authenticated, token, login, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
};
