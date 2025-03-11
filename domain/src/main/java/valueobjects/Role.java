package valueobjects;

import jakarta.persistence.Entity;
import lombok.Getter;
@Getter
public enum Role {
    ADMIN("admin"),
    USER("user"),
    MODERATOR("moderator"),
    VIP("vip");

    private final String rolename;

    Role(String rolename){
        this.rolename = rolename;
    }


}
