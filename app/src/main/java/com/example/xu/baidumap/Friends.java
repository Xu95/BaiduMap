package com.example.xu.baidumap;

import java.io.Serializable;

/**
 * Created by Xu on 2016/10/17.
 */
public class Friends implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;

    private String name;
    private String number;
    private String longitude;
    private String latitude;
    private String altitude;
    private String accuracy;
    private String city;
    private String last_update;
    private String next_update;

    public Friends() {}

    public Friends(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getname() {return name;}
    public void setname(String name) {this.name = name;}

    public String getnumber() {return number;}
    public void setnumber(String number) {this.number = number;}

    public String getlongitude() {return longitude;}
    public void setlongitude(String longitude) {this.longitude = longitude;}

    public String getlatitude() {return latitude;}
    public void setlatitude(String latitude) {this.latitude = latitude;}

    public String getaltitude() {return altitude;}
    public void setaltitude(String altitude) {this.altitude = altitude;}

    public String getaccuracy() {
        return accuracy;
    }
    public void setaccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getcity() {
        return city;
    }
    public void setcity(String city) {
        this.city = city;
    }

    public String getlast_update() {
        return last_update;
    }
    public void setlast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getnext_update() {
        return next_update;
    }
    public void setnext_update(String next_update) {
        this.next_update = next_update;
    }
}





