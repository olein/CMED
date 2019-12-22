package com.cmed.database;

import com.cmed.dto.PrescriptionListRequest;
import com.cmed.entity.Prescription;
import com.cmed.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class PrescriptionData {

    List<Prescription> prescriptionList = new ArrayList<>();
    public static Integer count = 0;

    public PrescriptionData(){
        for(int i=0;i<10;i++) {
            int random = new Random().nextInt(100);
            int random2 = new Random().nextInt(10);
            Prescription prescription = new Prescription();
            prescription.setPrescriptionId(i);
            prescription.setPatientName("Patient " + i);
            prescription.setPatientAge(random);
            prescription.setGender("Male");
            prescription.setDiagnosis("Diagnosis " + i);
            prescription.setMedicine("Medicine " + i);
            prescription.setNextVisitDate(java.sql.Date.valueOf(LocalDate.now().plusDays(random)));
            prescription.setPrescriptionDate(java.sql.Date.valueOf(LocalDate.now().minusDays(random2)));
            prescriptionList.add(prescription);
            ++count;
        }
        Collections.sort(prescriptionList);
    }

    public List<Prescription> findAllPrescription() {
        return prescriptionList;
    }

    public List<Prescription> findPrescriptionByDateRange(PrescriptionListRequest prescriptionListRequest){

        if(StringUtils.isBlank(prescriptionListRequest.getStartDate()) &&
                StringUtils.isBlank(prescriptionListRequest.getEndDate())) {
            return prescriptionList;
        } else {
            List<Prescription> dateRangeList = new ArrayList<>();

            Date start = DateUtil.convertToDate(prescriptionListRequest.getStartDate());
            Date end = DateUtil.convertToDate(prescriptionListRequest.getEndDate());

            for(Prescription prescription: prescriptionList) {
                if(start.before(prescription.getPrescriptionDate()) &&
                        end.after(prescription.getPrescriptionDate())) {
                    dateRangeList.add(prescription);
                }
            }
            return dateRangeList;
        }
    }

    public Prescription findPrescriptionByPrescriptionId(int prescriptionId) {
        Optional<Prescription> prescription = prescriptionList.stream().filter(p -> p.getPrescriptionId() == prescriptionId).findFirst();

        if(prescription.get()!=null) return prescription.get();
        else return null;
    }

    public synchronized int getNextId() {
        return count;
    }

    public boolean addPrescription(Prescription prescription) {
        return prescriptionList.add(prescription);
    }

    public boolean updatePrescription(Prescription prescription) {
        for(int i=0;i<prescriptionList.size();i++) {
            if(prescriptionList.get(i).getPrescriptionId() == prescription.getPrescriptionId()) {
                prescriptionList.set(i, prescription);
                return true;
            }
        }
        return false;
    }

    public boolean deletePrescription(int prescriptionId) {
        Optional<Prescription> prescription = prescriptionList.stream()
                .filter(p -> p.getPrescriptionId() == prescriptionId).findFirst();

        if(prescription.get()==null) return false;

        return prescriptionList.remove(prescription.get());
    }
}
