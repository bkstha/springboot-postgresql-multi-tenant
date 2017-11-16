package com.journal.app.models.enums;


public enum SubscriptionPlan {
    TD("Trading", 250.00),  S("Services", 250.00),  HS("Hostel", 250.00);
    private String plan;
    private Double amount;

    SubscriptionPlan(String plan, Double amount) {
        this.plan = plan;
        this.amount=amount;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
