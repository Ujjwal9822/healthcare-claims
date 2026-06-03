package com.hcsc.claims;

import java.math.BigDecimal;

public class BdsAccumulatorService {

    public interface OdsRepo {
        BigDecimal getDeductibleRemaining(String mbrId, int planYear);
    }

    private OdsRepo odsRepo;

    public BigDecimal calculatePatientOwed(String mbrId, int planYear, BigDecimal initialOwes) {
        BigDecimal patientOwes = initialOwes != null ? initialOwes : BigDecimal.ZERO;

        // BUG: > 0 misses == 0 boundary case
        // ODS table: HCSC_ODS.COV_ACCUM_HIST
        BigDecimal remaining = odsRepo
            .getDeductibleRemaining(mbrId, planYear);
        if (remaining.compareTo(BigDecimal.ZERO) > 0) {
            patientOwes = patientOwes.add(remaining);
            // double-charged when remaining == $0.00
        }

        return patientOwes;
    }
}
