package com.company;

import java.io.Serial;
import java.io.Serializable;

public class MyClassToBePersisted implements Serializable {
    @Serial
    private static final long serialVersionUID = 777L;
    private String profile;
    private String group;
    public MyClassToBePersisted() {
        profile = "";
        group = "";
    }
    public MyClassToBePersisted(String profile, String group) {
        this.profile = profile;
        this.group = group;
    }
    public String getProfile() {
        return profile;
    }
    public String getGroup() {
        return group;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    @Override
    public String toString() {
        return "MyClassToBePersisted{" + "\n" +
                "profile='" + profile + "\'," + "\n" +
                "group='" + group + "\'," + "\n" +
                '}' + "\n";
    }
}
