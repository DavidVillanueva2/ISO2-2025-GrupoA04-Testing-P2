package es.uclm.iso2.becas;

/**
 * Immutable student profile. All data is validated in the constructor, so an
 * existing Student object is always valid.
 */
public class Student {

    public static final double MIN_GPA = 0.0;
    public static final double MAX_GPA = 10.0;
    public static final int MIN_DISABILITY = 0;
    public static final int MAX_DISABILITY = 100;

    private final double gpa;
    private final double familyIncome;
    private final double distanceKm;
    private final int disabilityDegree;
    private final int siblings;

    /**
     * @param gpa              grade point average, in [0, 10]
     * @param familyIncome     annual family income in EUR, &gt;= 0
     * @param distanceKm       distance from home to campus in km, &gt;= 0
     * @param disabilityDegree certified disability degree in %, in [0, 100]
     * @param siblings         number of siblings, &gt;= 0
     * @throws InvalidStudentDataException if any value is out of range
     */
    public Student(double gpa, double familyIncome, double distanceKm, int disabilityDegree, int siblings) {
        if (!Double.isFinite(gpa) || gpa < MIN_GPA || gpa > MAX_GPA) {
            throw new InvalidStudentDataException("GPA must be between 0 and 10: " + gpa);
        }
        if (!Double.isFinite(familyIncome) || familyIncome < 0) {
            throw new InvalidStudentDataException("Family income cannot be negative: " + familyIncome);
        }
        if (!Double.isFinite(distanceKm) || distanceKm < 0) {
            throw new InvalidStudentDataException("Distance cannot be negative: " + distanceKm);
        }
        if (disabilityDegree < MIN_DISABILITY || disabilityDegree > MAX_DISABILITY) {
            throw new InvalidStudentDataException("Disability degree must be between 0 and 100: " + disabilityDegree);
        }
        if (siblings < 0) {
            throw new InvalidStudentDataException("Number of siblings cannot be negative: " + siblings);
        }
        this.gpa = gpa;
        this.familyIncome = familyIncome;
        this.distanceKm = distanceKm;
        this.disabilityDegree = disabilityDegree;
        this.siblings = siblings;
    }

    public double getGpa() {
        return gpa;
    }

    public double getFamilyIncome() {
        return familyIncome;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public int getDisabilityDegree() {
        return disabilityDegree;
    }

    public int getSiblings() {
        return siblings;
    }
}
