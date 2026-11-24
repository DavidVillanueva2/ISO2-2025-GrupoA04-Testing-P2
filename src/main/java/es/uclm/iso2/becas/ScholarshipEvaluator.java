package es.uclm.iso2.becas;

/**
 * Business logic: decides the most appropriate scholarship for a student.
 *
 * <p>If a student meets the requirements of several scholarships, the most
 * valuable one is awarded, in this order:
 * EXCELLENCE &gt; RESIDENCE &gt; INCLUSION &gt; LARGE_FAMILY &gt; NONE.
 */
public class ScholarshipEvaluator {

    // Excellence Scholarship
    public static final double EXCELLENCE_MIN_GPA = 8.5;
    public static final double EXCELLENCE_MAX_INCOME = 30000; // strictly below

    // Residence Scholarship
    public static final double RESIDENCE_MIN_GPA = 6.5;
    public static final double RESIDENCE_MAX_GPA = 8.5;       // strictly below (closes the 8.4-8.5 gap)
    public static final double RESIDENCE_MAX_INCOME = 20000;  // strictly below
    public static final double RESIDENCE_MIN_DISTANCE = 50;   // strictly above

    // Inclusion Scholarship
    public static final int INCLUSION_MIN_DISABILITY = 33;
    public static final double INCLUSION_MIN_GPA = 5.0;

    // Large Family Scholarship
    public static final double LARGE_FAMILY_MIN_GPA = 5.0;
    public static final double LARGE_FAMILY_MAX_INCOME = 15000; // strictly below
    public static final int LARGE_FAMILY_MIN_SIBLINGS = 3;

    /**
     * Returns the most valuable scholarship the student is entitled to.
     *
     * @throws InvalidStudentDataException if student is null
     */
    public ScholarshipType evaluate(Student student) {
        if (student == null) {
            throw new InvalidStudentDataException("Student cannot be null");
        }
        if (isExcellence(student)) {
            return ScholarshipType.EXCELLENCE;
        }
        if (isResidence(student)) {
            return ScholarshipType.RESIDENCE;
        }
        if (isInclusion(student)) {
            return ScholarshipType.INCLUSION;
        }
        if (isLargeFamily(student)) {
            return ScholarshipType.LARGE_FAMILY;
        }
        return ScholarshipType.NONE;
    }

    /** GPA &gt;= 8.5 AND income &lt; 30,000. */
    public boolean isExcellence(Student s) {
        return s.getGpa() >= EXCELLENCE_MIN_GPA
                && s.getFamilyIncome() < EXCELLENCE_MAX_INCOME;
    }

    /** 6.5 &lt;= GPA &lt; 8.5 AND income &lt; 20,000 AND distance &gt; 50 km. */
    public boolean isResidence(Student s) {
        return s.getGpa() >= RESIDENCE_MIN_GPA
                && s.getGpa() < RESIDENCE_MAX_GPA
                && s.getFamilyIncome() < RESIDENCE_MAX_INCOME
                && s.getDistanceKm() > RESIDENCE_MIN_DISTANCE;
    }

    /** Disability &gt;= 33% AND GPA &gt;= 5.0. */
    public boolean isInclusion(Student s) {
        return s.getDisabilityDegree() >= INCLUSION_MIN_DISABILITY
                && s.getGpa() >= INCLUSION_MIN_GPA;
    }

    /** GPA &gt;= 5.0 AND income &lt; 15,000 AND siblings &gt;= 3. */
    public boolean isLargeFamily(Student s) {
        return s.getGpa() >= LARGE_FAMILY_MIN_GPA
                && s.getFamilyIncome() < LARGE_FAMILY_MAX_INCOME
                && s.getSiblings() >= LARGE_FAMILY_MIN_SIBLINGS;
    }
}
