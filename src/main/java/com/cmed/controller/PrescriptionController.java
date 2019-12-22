package com.cmed.controller;

import com.cmed.dto.PrescriptionListRequest;
import com.cmed.dto.PrescriptionListResponse;
import com.cmed.dto.PrescriptionRequest;
import com.cmed.dto.PrescriptionResponse;
import com.cmed.service.LoginUser;
import com.cmed.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    LoginUser loginUser;

    @RequestMapping(value = "/getPrescriptionList", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    public @ResponseBody
    ResponseEntity<PrescriptionListResponse> getPrescriptionList(@RequestBody PrescriptionListRequest prescriptionListRequest) {

        if (loginUser.isLoggedIn(prescriptionListRequest.getUserId(), prescriptionListRequest.getLoginId())) {
            List<PrescriptionResponse> prescriptionList = prescriptionService.getPrescription(prescriptionListRequest);

            if (prescriptionList.size() > 0) {
                PrescriptionListResponse responseList = new PrescriptionListResponse();
                responseList.setPrescriptionList(prescriptionList);
                responseList.setLoggedIn(true);
                ResponseEntity<PrescriptionListResponse> response = new ResponseEntity<>(responseList, HttpStatus.OK);
                return response;
            } else {
                PrescriptionListResponse responseList = new PrescriptionListResponse();
                responseList.setPrescriptionList(Collections.EMPTY_LIST);
                responseList.setLoggedIn(true);
                ResponseEntity<PrescriptionListResponse> response = new ResponseEntity<>(responseList, HttpStatus.OK);
                return response;
            }
        } else {
            PrescriptionListResponse responseList = new PrescriptionListResponse();
            responseList.setPrescriptionList(Collections.EMPTY_LIST);
            responseList.setLoggedIn(false);
            ResponseEntity<PrescriptionListResponse> response = new ResponseEntity<>(responseList, HttpStatus.OK);
            return response;
        }
    }

    @RequestMapping(value = "/createOrUpdatePrescription", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    public @ResponseBody
    ResponseEntity<PrescriptionResponse> createOrUpdatePrescription(@RequestBody PrescriptionRequest prescriptionRequest) {

        if (loginUser.isLoggedIn(prescriptionRequest.getUserId(), prescriptionRequest.getLoginId())) {
            PrescriptionResponse prescriptionResponse = prescriptionService.createOrUpdatePrescription(prescriptionRequest);

            ResponseEntity<PrescriptionResponse> response = new ResponseEntity<>(prescriptionResponse, HttpStatus.OK);
            return response;
        } else {
            ResponseEntity<PrescriptionResponse> response = new ResponseEntity<>(HttpStatus.OK);
            return response;
        }
    }

    @RequestMapping(value = "/deletePrescription/{prescriptionId}", method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody
    ResponseEntity<Boolean> deletePrescription(@PathVariable int prescriptionId) {
        Boolean result = prescriptionService.deletePrescription(prescriptionId);
        ResponseEntity<Boolean> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/prescriptionReport", method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody
    ResponseEntity<Map<String, Integer>> prescriptionReport() {
        Map<String, Integer> result = prescriptionService.prescriptionReport();
        ResponseEntity<Map<String, Integer>> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }
}
