// Updated code with null check and retained class structure
public class CoverageChecker {
    private Coverage coverage;

    public CoverageChecker(Coverage coverage) {
        this.coverage = coverage;
    }

    public boolean hasActiveCoverage() {
        if (coverage == null) {
            throw new IllegalArgumentException("Coverage cannot be null");
        }
        return coverage.isActive();
    }
}

// Added documentation and unit tests
// Ensure HIPAA/PHI compliance in error messages