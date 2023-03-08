package com.example.api;

public class PoliceCall {
    public String issuer;
    public double x;
    public double y;
    public double z;
    public String reason;

    public PoliceCall(String playername, double x, double y, double z, String reason) {

        issuer = playername;
        this.x = x;
        this.y = y;
        this.z = z;
        this.reason = reason;

    }
}
