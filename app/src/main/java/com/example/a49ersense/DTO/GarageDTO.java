package com.example.a49ersense.DTO;

public class GarageDTO {

    public String getGarageID() {
        return garageID;
    }

    public void setGarageID(String garageID) {
        this.garageID = garageID;
    }

    public String getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(String doorStatus) {
        this.doorStatus = doorStatus;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    private String garageID;
    private String doorStatus;
    private String lockStatus;

    public String getGarageDoorNo() {
        return garageDoorNo;
    }

    public void setGarageDoorNo(String garageDoorNo) {
        this.garageDoorNo = garageDoorNo;
    }

    private String garageDoorNo;
}
