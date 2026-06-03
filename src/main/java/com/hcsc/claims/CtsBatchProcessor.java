package com.hcsc.claims;

import java.math.BigDecimal;
import java.util.List;

public class CtsBatchProcessor {

    public static class Claim {
        private BigDecimal clmAmt;
        public BigDecimal getClmAmt() {
            return clmAmt;
        }
        public void setClmAmt(BigDecimal clmAmt) {
            this.clmAmt = clmAmt;
        }
    }

    public interface Db2Repo {
        List<Claim> getPendingClaims();
    }

    private Db2Repo db2Repo;

    public void processPendingClaims() {
        // BUG: List modified during for-each
        List<Claim> claims = db2Repo.getPendingClaims();
        for (Claim c : claims) {     // CME here
            if (c == null || c.getClmAmt() == null) {
                claims.remove(c);    // modifies source list
            }
            processClaim(c);
        }
    }

    private void processClaim(Claim c) {
        // processing logic
    }
}
