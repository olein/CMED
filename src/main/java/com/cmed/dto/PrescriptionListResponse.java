package com.cmed.dto;

import java.util.List;

public class PrescriptionListResponse {

    List<PrescriptionResponse> prescriptionList;
    Boolean isLoggedIn;

    public List<PrescriptionResponse> getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(List<PrescriptionResponse> prescriptionList) {
        this.prescriptionList = prescriptionList;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
