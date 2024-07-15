package org.example.model;

/**
 * Credential class model represents a credential
 * used in by the view.
 */
public class Credential {

    /**
     * The name of the credential
     */
    private String name;

    /**
     * The identifier of the credential (eg: email)
     */
    private String identifier;

    /**
     * The password of the credential
     */
    private String password;

    /**
     * The service that needs authentication credentials (eg: url)
     */
    private String authService;

    /**
     * Constructs a credential with the given parameters
     *
     * @param name The name of the credential
     * @param identifier The identifier of the credential
     * @param password The password of the credential
     * @param authService The service that needs authentication credentials
     */
    public Credential(String name, String identifier, String password, String authService) {
        this.name = name;
        this.identifier = identifier;
        this.password = password;
        this.authService = authService;
    }

    /**
     * Returns the name of the credential
     *
     * @return Name of the credential
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the credential
     *
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the identifier of the credential
     *
     * @return Identifier of the credential
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Set the identifier of the credential
     *
     * @param identifier The identifier to set
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the password of the credential
     *
     * @return The password of the credential
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the credential
     *
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the authentication service that need the credentials
     *
     * @return authentication service of the credential
     */
    public String getAuthService() {
        return authService;
    }

    /**
     * Set the authentication service that requires the credentials
     *
     * @param authService The Authentication service to set
     */
    public void setAuthService(String authService) {
        this.authService = authService;
    }
}
