package com.cmed.service;

import com.cmed.dto.PrescriptionListRequest;
import com.cmed.dto.PrescriptionRequest;
import com.cmed.dto.PrescriptionResponse;

import java.util.List;
import java.util.Map;

public interface PrescriptionService {

    List<PrescriptionResponse> getPrescription(PrescriptionListRequest prescriptionListRequest);

    PrescriptionResponse createOrUpdatePrescription(PrescriptionRequest prescriptionRequest);

    Boolean deletePrescription(int prescriptionId);

    Map<String,Integer> prescriptionReport();
}
