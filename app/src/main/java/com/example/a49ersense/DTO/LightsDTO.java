package com.example.a49ersense.DTO;

public class LightsDTO {

    private String lightID;
    private Boolean access;
    private String dimmerLevel;
    private Boolean status;

    public String getDimmerLevel() {
        return dimmerLevel;
    }

    public void setDimmerLevel(String dimmerLevel) {
        this.dimmerLevel = dimmerLevel;
    }

    public String getLightID() {
        return lightID;
    }

    public void setLightID(String lightID) {
        this.lightID = lightID;
    }

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }




}
