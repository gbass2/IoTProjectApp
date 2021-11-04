package com.example.a49ersense.DTO;

import java.util.List;

public class FloorDTO {

    private String floorID;
    private String floorNO;
    private String tMode;
    private String tFan;
    private String tCurrent;
    private String tControl;
    private Boolean tStatus;
    List<LightsDTO> lightsDetails;

    public Boolean gettAccess() {
        return tAccess;
    }

    public void settAccess(Boolean tAccess) {
        this.tAccess = tAccess;
    }

    private Boolean tAccess;

    public String getFloorID() {
        return floorID;
    }

    public void setFloorID(String floorID) {
        this.floorID = floorID;
    }

    public String getFloorNO() {
        return floorNO;
    }

    public void setFloorNO(String floorNO) {
        this.floorNO = floorNO;
    }

    public String gettMode() {
        return tMode;
    }

    public void settMode(String tMode) {
        this.tMode = tMode;
    }

    public String gettFan() {
        return tFan;
    }

    public void settFan(String tFan) {
        this.tFan = tFan;
    }

    public String gettCurrent() {
        return tCurrent;
    }

    public void settCurrent(String tCurrent) {
        this.tCurrent = tCurrent;
    }

    public String gettControl() {
        return tControl;
    }

    public void settControl(String tControl) {
        this.tControl = tControl;
    }

    public Boolean gettStatus() {
        return tStatus;
    }

    public void settStatus(Boolean tStatus) {
        this.tStatus = tStatus;
    }

    public List<LightsDTO> getLightsDetails() {
        return lightsDetails;
    }

    public void setLightsDetails(List<LightsDTO> lightsDetails) {
        this.lightsDetails = lightsDetails;
    }


}
