package com.ghazy.goldwing3;

public class User {
    private String fullName;
    private String email;
    private String password;
    private String birthday;
    private String photo;

    public User(){

    }

    public User(String fullName, String email, String password, String birthday, String photo) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.photo = photo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
