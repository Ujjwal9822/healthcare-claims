package com.hcsc.claims;

import java.math.BigDecimal;

public class CoreAdjApiWrapper {

    public static class Claim {
        private String mbrId;
        public String getMbrId() {
            return mbrId;
        }
        public void setMbrId(String mbrId) {
            this.mbrId = mbrId;
        }
    }

    public static class Coverage {
        private boolean active;
        public boolean isActive() {
            return active;
        }
        public void setActive(boolean active) {
            this.active = active;
        }
    }

    public static class AdjResult {
        private String status;
        private String message;

        public static AdjResult denied(String message) {
            AdjResult result = new AdjResult();
            result.status = "DENIED";
            result.message = message;
            return result;
        }

        public String getStatus() { return status; }
        public String getMessage() { return message; }
    }

    public interface Db2Repo {
        Coverage findActiveCoverage(String mbrId);
    }

    private Db2Repo db2Repo;

    public AdjResult adjudicateClaim(Claim claim) {
        // BUG: No null check before isActive()
        Coverage cov = db2Repo.findActiveCoverage(
            claim.getMbrId());
        if (!cov.isActive()) { // NPE when cov == null
            return AdjResult.denied("No coverage");
        }
        
        // Return dummy success if active
        AdjResult success = new AdjResult();
        success.status = "APPROVED";
        return success;
    }
}
