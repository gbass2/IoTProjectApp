package com.example.a49ersense.DTO;

import java.io.Serializable;
import java.util.List;

public class HouseDTO {

    private String houseID;
    private String securityStatus;
    private String floors;
    private String garageDoors;
    private Boolean frontLockStatus;
    private Boolean backLockStatus;
    private Boolean garageLockStatus;
    private List<FloorDTO> floorDTO;

    public List<GarageDTO> getGarageDTO() {
        return garageDTO;
    }

    public void setGarageDTO(List<GarageDTO> garageDTO) {
        this.garageDTO = garageDTO;
    }

    private List<GarageDTO> garageDTO;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private String userID;

    public String getHouseID() {
        return houseID;
    }

    public void setHouseID(String houseID) {
        this.houseID = houseID;
    }

    public String getSecurityStatus() {
        return securityStatus;
    }

    public void setSecurityStatus(String securityStatus) {
        this.securityStatus = securityStatus;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getGarageDoors() {
        return garageDoors;
    }

    public void setGarageDoors(String garagedoors) {
        this.garageDoors = garagedoors;
    }

    public Boolean getFrontLockStatus() {
        return frontLockStatus;
    }

    public void setFrontLockStatus(Boolean frontLockStatus) {
        this.frontLockStatus = frontLockStatus;
    }

    public Boolean getBackLockStatus() {
        return backLockStatus;
    }

    public void setBackLockStatus(Boolean backLockStatus) {
        this.backLockStatus = backLockStatus;
    }

    public Boolean getGarageLockStatus() {
        return garageLockStatus;
    }

    public void setGarageLockStatus(Boolean garageLockStatus) {
        this.garageLockStatus = garageLockStatus;
    }

    public List<FloorDTO> getFloorDTO() {
        return floorDTO;
    }

    public void setFloorDTO(List<FloorDTO> floorDTO) {
        this.floorDTO = floorDTO;
    }


}
