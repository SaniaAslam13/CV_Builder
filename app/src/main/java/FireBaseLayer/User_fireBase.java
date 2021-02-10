package FireBaseLayer;

import ObjectLayer.User;

public class User_fireBase extends User {

    private String id;

    public User_fireBase() {
        id = null;
    }


    public User_fireBase(String id, User user) {
        this.id = id;
        super.setUser(user);
    }

    public User_fireBase(String id) {
        this.id = id;
    }


    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
