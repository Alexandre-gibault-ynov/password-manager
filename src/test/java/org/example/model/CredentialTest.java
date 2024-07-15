package org.example.model;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CredentialTest {

    @Test
    void getName() {
        String name = "test";
        Credential credential = new Credential("test", "test", "test", "test");
        assertThat(credential.getName(), equalTo(name));
    }

    @Test
    void setName() {
        String name = "test01";
        Credential credential = new Credential("test", "test", "test", "test");
        credential.setName("test01");
        assertThat(credential.getName(), equalTo(name));
    }

    @Test
    void getIdentifier() {
        String identifier = "test";
        Credential credential = new Credential("test", "test", "test", "test");
        assertThat(credential.getIdentifier(), equalTo(identifier));
    }

    @Test
    void setIdentifier() {
        String identifier = "test01";
        Credential credential = new Credential("test", "test", "test", "test");
        credential.setName("test01");
        assertThat(credential.getIdentifier(), equalTo(identifier));
    }

    @Test
    void getPassword() {
        String password = "test";
        Credential credential = new Credential("test", "test", "test", "test");
        assertThat(credential.getPassword(), equalTo(password));
    }

    @Test
    void setPassword() {
        String password = "test01";
        Credential credential = new Credential("test", "test", "test", "test");
        credential.setPassword("test01");
        assertThat(credential.getPassword(), equalTo(password));
    }

    @Test
    void getAuthService() {
        String authService = "test";
        Credential credential = new Credential("test", "test", "test", "test");
        assertThat(credential.getAuthService(), equalTo(authService));
    }

    @Test
    void setAuthService() {
        String authService = "test01";
        Credential credential = new Credential("test", "test", "test", "test");
        credential.setAuthService("test01");
        assertThat(credential.getAuthService(), equalTo(authService));
    }
}