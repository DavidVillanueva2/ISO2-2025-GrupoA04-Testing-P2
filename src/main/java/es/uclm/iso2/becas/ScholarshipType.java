package es.uclm.iso2.becas;

/**
 * Scholarship categories, declared from most to least valuable.
 */
public enum ScholarshipType {

    EXCELLENCE("Excellence Scholarship", "Full tuition waiver plus a 1,000 EUR stipend"),
    RESIDENCE("Residence Scholarship", "Full tuition waiver plus a housing allowance"),
    INCLUSION("Inclusion Scholarship", "Full tuition waiver plus assistive technology funding"),
    LARGE_FAMILY("Large Family Scholarship", "Full tuition waiver"),
    NONE("No scholarship", "The student does not meet the requirements of any scholarship");

    private final String displayName;
    private final String benefits;

    ScholarshipType(String displayName, String benefits) {
        this.displayName = displayName;
        this.benefits = benefits;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getBenefits() {
        return benefits;
    }
}
