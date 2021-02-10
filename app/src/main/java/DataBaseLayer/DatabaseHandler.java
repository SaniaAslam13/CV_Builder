package DataBaseLayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ObjectLayer.Education;
import ObjectLayer.Experience;
import ObjectLayer.User;
import ObjectLayer.UserBasic;

public class DatabaseHandler extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cvapp";

    DL_User dl_user;
    DL_Education dl_education;
    DL_Experience dl_experience;
    L_UserExperience l_userExperience;
    L_UserEducation l_userEducation;
    DL_Conf dl_conf;



    public void onCreate(SQLiteDatabase db) {





        db.execSQL(dl_user.createTable());
        db.execSQL(dl_education.createTable());
        db.execSQL(dl_experience.createTable());
        db.execSQL(l_userEducation.createTable());
        db.execSQL(l_userExperience.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }





    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

//        doDBCheck();

        dl_user = new DL_User(this);
        dl_education = new DL_Education(this);
        dl_experience = new DL_Experience(this);
        l_userEducation = new L_UserEducation(this);
        l_userExperience = new L_UserExperience(this);
        dl_conf = new DL_Conf(this);
    }

    public void insertUser(UserBasic userBasic, List<Education> list_Education, List<Experience> list_Experience) {

        this.getWritableDatabase();

        int userId = (int) dl_user.insert(userBasic);


        for (Education education: list_Education) {
            int educationId = (int) dl_education.insert(education);

            l_userEducation.insert(userId,educationId);
        }

        for(Experience experience : list_Experience) {

            int experienceId =  (int)dl_experience.insert(experience);

            l_userExperience.insert(userId,experienceId);
        }
    }

    public Boolean checkIfExist(int userId) {
        return dl_user.checkIfExist(userId);
    }



    public User getUser(int userId) {

        UserBasic userBasic = dl_user.getUser_ById(userId);


        List<Education> list_Education = new ArrayList<>();
        List<Experience> list_Experience = new ArrayList<>();


        List<Integer> list_educationId = l_userEducation.getListEducationId(userBasic.getId());

        for (int educationId : list_educationId) {

            Education education = dl_education.getEducation_ById(educationId);
            list_Education.add(education);
        }


        List<Integer> list_experienceId = l_userExperience.getListExperienceId(userBasic.getId());

        for (int experienceId : list_experienceId) {

            Experience experience = dl_experience.getExperience_ById(experienceId);
            list_Experience.add(experience);
        }


        User user = new User(userBasic, list_Education,list_Experience);

        return user;


    }

    public List<User> getListUser() {

        List<User> list_User = new ArrayList<>();
        List<Integer> list_UserId = dl_user.getlistUsersId();

        for(int userId : list_UserId) {

            list_User.add( this.getUser(userId) );

        }

        return list_User;
    }

    public void deleteUser(int userId) {

        for( int educationId : l_userEducation.getListEducationId(userId) ) {

            dl_education.deleteEducation(educationId);
            l_userEducation.deleteUserEducation(userId);

        }


        for( int experienceId : l_userExperience.getListExperienceId(userId) ) {

            dl_experience.deleteExperience(experienceId);
            l_userExperience.deleteUserExperience(userId);

        }

        dl_user.deleteUser(userId);


    }


    private void doDBCheck()
    {
        String appname = "cv_builder";

        String DB_PATH = "data/data/com.example.arslan.+ " + appname + "/databases/" + DATABASE_NAME;

        Boolean a;
        try{
            File file = new File(DB_PATH);
            a= file.delete();
        }catch(Exception ex)
        {}
    }


}
