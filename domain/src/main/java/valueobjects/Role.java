package valueobjects;

import lombok.Getter;
@Getter
public enum Role {
    ADMIN("admin"),
    USER("user"),
    MODERATOR("moderator"),
    VIP("vip"),
    admin("admin"),
    user("user"),
    moderator("moderator"),
    vip("vip");

    private final String rolename;

    Role(String rolename){
        this.rolename = rolename;
    }


}
