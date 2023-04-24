package com.devcamp.eztour.common;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class PrepareData extends com.siot.IamportRestClient.request.PrepareData {
    @SerializedName("merchant_uid")
    private String merchant_uid;

    @SerializedName("amount")
    private BigDecimal amount;

    public PrepareData(String merchant_uid, BigDecimal amount) {
        super(merchant_uid, amount);
    }

    public String getMerchant_uid() {
        return merchant_uid;
    }

    public void setMerchant_uid(String merchant_uid) {
        this.merchant_uid = merchant_uid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}