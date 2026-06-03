package com.hcsc.claims;

import java.math.BigDecimal;

public class BdsCopayLookupService {

    public enum ServiceType {
        PRIMARY_CARE,
        SPECIALIST,
        EMERGENCY
    }

    public static class PlanConfig {
        private BigDecimal pcCopay = new BigDecimal("20.00");
        private BigDecimal spCopay = new BigDecimal("50.00");
        private BigDecimal erCopay = new BigDecimal("150.00");

        public BigDecimal getPcCopay() { return pcCopay; }
        public BigDecimal getSpCopay() { return spCopay; }
        public BigDecimal getErCopay() { return erCopay; }
    }

    private PlanConfig planConfig = new PlanConfig();

    public BigDecimal lookupCopay(ServiceType serviceType) {
        // BUG: EMERGENCY falls to default ($20)
        switch (serviceType) {
            case PRIMARY_CARE:
                return planConfig.getPcCopay();   // $20
            case SPECIALIST:
                return planConfig.getSpCopay();  // $50
            default:  // ER lands here → $20 wrong!
                return planConfig.getPcCopay();
        }
    }
}
