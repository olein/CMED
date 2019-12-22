package com.cmed.service;

import com.cmed.database.PrescriptionData;
import com.cmed.dto.PrescriptionListRequest;
import com.cmed.dto.PrescriptionRequest;
import com.cmed.dto.PrescriptionResponse;
import com.cmed.entity.Prescription;
import com.cmed.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    PrescriptionData prescriptionData;

    @Override
    public List<PrescriptionResponse> getPrescription(PrescriptionListRequest prescriptionListRequest) {

        try{
            List<Prescription> prescriptionList = prescriptionData.findPrescriptionByDateRange(prescriptionListRequest);

            List<PrescriptionResponse> prescriptionResponseList = new ArrayList<>();
            for(Prescription prescription : prescriptionList) {
                PrescriptionResponse prescriptionResponse = new PrescriptionResponse();
                prescriptionResponse.setPrescriptionId(prescription.getPrescriptionId());
                prescriptionResponse.setDiagnosis(prescription.getDiagnosis());
                prescriptionResponse.setGender(prescription.getGender());
                prescriptionResponse.setMedicine(prescription.getMedicine());
                prescriptionResponse.setPatientName(prescription.getPatientName());
                prescriptionResponse.setPatientAge(prescription.getPatientAge());
                if(prescription.getNextVisitDate()!=null) {
                    prescriptionResponse.setNextVisitDate(DateUtil.convertToString(prescription.getNextVisitDate()));
                }
                prescriptionResponse.setPrescriptionDate(DateUtil.convertToString(prescription.getPrescriptionDate()));

                prescriptionResponseList.add(prescriptionResponse);
            }
            //getJson();
            return prescriptionResponseList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public PrescriptionResponse createOrUpdatePrescription(PrescriptionRequest prescriptionRequest) {

        try {
            Prescription prescription = new Prescription();
            prescription.setPatientName(prescriptionRequest.getPatientName());
            prescription.setPatientAge(prescriptionRequest.getPatientAge());

            if(prescription.getNextVisitDate()!=null) {
                prescription.setNextVisitDate(DateUtil.convertToDate(prescriptionRequest.getNextVisitDate()));
            }
            prescription.setMedicine(prescriptionRequest.getMedicine());
            prescription.setDiagnosis(prescriptionRequest.getDiagnosis());
            prescription.setGender(prescriptionRequest.getGender());

            if(prescriptionRequest.getPrescriptionId()>0) {
                prescription.setPrescriptionId(prescriptionRequest.getPrescriptionId());
                Prescription previousPrescription = prescriptionData.findPrescriptionByPrescriptionId(prescriptionRequest.getPrescriptionId());
                prescription.setPrescriptionDate(previousPrescription.getPrescriptionDate());
                prescriptionData.updatePrescription(prescription);
            } else {
                prescription.setPrescriptionDate(DateUtil.currentDate());
                prescription.setPrescriptionId(prescriptionData.getNextId());
                prescriptionData.addPrescription(prescription);
            }

            PrescriptionResponse prescriptionResponse = new PrescriptionResponse(prescription);

            return prescriptionResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Boolean deletePrescription(int prescriptionId) {
        try{
            return prescriptionData.deletePrescription(prescriptionId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map<String, Integer> prescriptionReport() {
        try{
            Map<String, Integer> prescriptionReport = new HashMap<>();

            List<Prescription> prescriptionList = prescriptionData.findAllPrescription();

            for(Prescription prescription : prescriptionList) {
                String date = DateUtil.convertToString(prescription.getPrescriptionDate());
                if(prescriptionReport.containsKey(date)) {
                    int count = prescriptionReport.get(date);
                    prescriptionReport.put(date,++count);
                } else {
                    prescriptionReport.put(date,1);
                }
            }

            return prescriptionReport;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getJson() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=341248";
        String response = restTemplate.getForObject(url,String.class);

        System.out.println(response);

        return response;
    }
}
