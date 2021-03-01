package utils.resource;

import com.google.gson.annotations.SerializedName;

public class UserAccount {
    @SerializedName("id")
    private String id;
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;


    public UserAccount(Credentials credentials) {
        this.username = credentials.getUsername();
        this.password = credentials.getPassword();
        this.email=credentials.getEmail();
        this.id = null;
    }

    public String getId() {
        return this.id;
    }

    public Credentials getCredentials() {
        return new DefaultCredentials(this.username, this.password,this.email);
    }

    public String toString() {
        return String.format("[id: %s, username: %s, password: %s, organisation: %s, description: %s]", this.id, this.username, this.password);
    }
}

