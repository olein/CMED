package com.cmed.dto;

import com.cmed.entity.Prescription;
import com.cmed.util.DateUtil;

public class PrescriptionResponse {

    private int prescriptionId;
    private String prescriptionDate;
    private String patientName;
    private int patientAge;
    private String gender;
    private String diagnosis;
    private String medicine;
    private String nextVisitDate;

    public PrescriptionResponse() {
    }

    public PrescriptionResponse(Prescription prescription) {
        this.prescriptionId = prescription.getPrescriptionId();
        this.prescriptionDate = DateUtil.convertToString(prescription.getPrescriptionDate());
        this.patientName = prescription.getPatientName();
        this.patientAge = prescription.getPatientAge();
        this.gender = prescription.getGender();
        this.diagnosis = prescription.getDiagnosis();
        this.medicine = prescription.getMedicine();
        this.nextVisitDate = DateUtil.convertToString(prescription.getNextVisitDate());
    }


    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(String prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(String nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }
}
