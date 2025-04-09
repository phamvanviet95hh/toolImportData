package com.example.demo.entitys;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name = "verify_transactions")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifyTransactionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String activityId;
    private int cardInformationId;
    private String code;
    private String destinationApi;
    private String deviceType;
    private Float discount;

    @Column(columnDefinition = "TEXT")
    private String exception;

    private int httpStatus;
    private Boolean isFromCard;
    private Boolean isValidIdCard;
    private int latencyTime;
    private Float originalPrice;
    private String partnerType;
    private Float price;
    private String providerConfigCode;


    @Column(columnDefinition = "TEXT")
    private String requestBody;

    private String requestId;
    private LocalDateTime requestTime;
    private String requestType;

    @Column(columnDefinition = "TEXT")
    private String responseBody;

    @Column(columnDefinition = "TEXT")
    private String signature;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partner_id", nullable = false)
    @JsonManagedReference
    private PartnerEntity partner;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "verify_quota_id", nullable = true)
    @JsonManagedReference
    private VerifyQuota verifyQuota;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sub_partner_id", nullable = false)
    @JsonManagedReference
    private SubPartner subPartner;


}
