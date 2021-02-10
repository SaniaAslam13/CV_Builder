package ObjectLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    UserBasic userBasic;
    List<Education> list_Education;
    List<Experience> list_Experience;

    public User(UserBasic userBasic, List<Education> list_Education, List<Experience> list_Experience) {
        this.userBasic = userBasic;
        this.list_Education = list_Education;
        this.list_Experience = list_Experience;
    }

    public User() {

        userBasic = new UserBasic();


    }

    public void setUser(User user) {

        this.setUserBasic( user.getUserBasic() );
        this.setList_Education( user.getList_Education() );
        this.setList_Experience( user.getList_Experience());
    }

    public UserBasic getUserBasic() {
        return userBasic;
    }

    public void setUserBasic(UserBasic userBasic) {
        this.userBasic = userBasic;
    }

    public List<Education> getList_Education() {
        if(list_Education == null)
            this.list_Education = new ArrayList<>();
        return list_Education;
    }

    public void setList_Education(List<Education> list_Education) {
        this.list_Education = list_Education;
    }

    public List<Experience> getList_Experience() {
        if(list_Experience == null)
            this.list_Experience = new ArrayList<>();
        return list_Experience;
    }

    public void setList_Experience(List<Experience> list_Experience) {
        this.list_Experience = list_Experience;
    }


}
