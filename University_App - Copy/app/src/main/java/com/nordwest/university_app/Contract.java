package com.nordwest.university_app;

public final class Contract {

    //to avoid accidental initialization of this class the constructor was declared private
    private Contract(){

    }
    //inner class with the DB schema

    public static class StudentEntry{

        public static final String TABLE_NAME = "_user_";
        public static final String STUDENT_FNAME = "_fn_";
        public static final String STUDENT_SNAME = "_sn_";
        public static final String STUDENT_EMAIL = "_email_";
        public static final String STUDENT_ID = "_student_id_";
        public static final String STUDENT_GROUP = "_group_";
        public static final String STUDENT_PASWD = "_password_";

        public static String actualUserFirstName;
        public static String actualUserSecondName;
        public static String actualUserGroupName;
        public static String actualUserEmail;
        public static String actualUserStudentID;



    }

}

