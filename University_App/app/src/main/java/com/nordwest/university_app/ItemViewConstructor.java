package com.nordwest.university_app;

public class ItemViewConstructor {

    int background;
    String profileName;
    int profilePhoto;
    String floorDescription;



    public ItemViewConstructor() {
    }

    public ItemViewConstructor(int background, String profileName, int profilePhoto, String nbFollowers) {
        this.background = background;
        this.profileName = profileName;
        this.profilePhoto = profilePhoto;
        this.floorDescription = nbFollowers;
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

    public String getFloorDescription() {
        return floorDescription;
    }

    public void setFloorDescription(String floorDescription) {
        this.floorDescription = floorDescription;
    }
}
