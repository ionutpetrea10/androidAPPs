package com.nordwest.university_app;

public class ItemViewConstructor {

    int background;
    String profileName;
    int profilePhoto;
    String nbFollowers;



    public ItemViewConstructor() {
    }

    public ItemViewConstructor(int background, String profileName, int profilePhoto, String nbFollowers) {
        this.background = background;
        this.profileName = profileName;
        this.profilePhoto = profilePhoto;
        this.nbFollowers = nbFollowers;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getNbFollowers() {
        return nbFollowers;
    }

    public void setNbFollowers(String nbFollowers) {
        this.nbFollowers = nbFollowers;
    }
}
