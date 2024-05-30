package com.bhoruka.bloodbank.model;

import java.util.Date;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class CampModel {

    private String id;

    private String partnerId;

    private Date dateOfCamp;

    private Long expectedNoOfDonor;

    private Long actualNoOfDonor;
}
