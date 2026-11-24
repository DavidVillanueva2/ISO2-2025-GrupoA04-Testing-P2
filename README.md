# ISO2-2025-GrupoA04-Testing-P2

ET.02.03 Second Problem: an algorithm that picks the most appropriate scholarship for a university student from their profile (GPA, family income, distance to campus, disability degree and number of siblings).

## Build and run

```bash
mvn clean package
java -cp target/classes es.uclm.iso2.becas.Main
```

Run the tests and generate the reports (Surefire and Jacoco):

```bash
mvn test                  # Jacoco report: target/site/jacoco/index.html
mvn surefire-report:report  # Surefire report: target/site/surefire-report.html
```

## Structure (`es.uclm.iso2.becas`)

| Class | Responsibility |
|---|---|
| `Student` | Immutable profile. Validates every field in its constructor. |
| `ScholarshipEvaluator` | Business logic. One public predicate per scholarship plus `evaluate()`. |
| `ScholarshipType` | Enum with the possible results and their benefits. |
| `InvalidStudentDataException` | Thrown for invalid or missing data. |
| `Main` | Console I/O only (excluded from Jacoco coverage). |

## Business rules

| Scholarship | Condition |
|---|---|
| Excellence | `gpa >= 8.5 && income < 30000` |
| Residence | `gpa >= 6.5 && gpa < 8.5 && income < 20000 && distance > 50` |
| Inclusion | `disability >= 33 && gpa >= 5.0` |
| Large Family | `gpa >= 5.0 && income < 15000 && siblings >= 3` |
| None | none of the above |

## Assumptions and design decisions

1. **Overlapping scholarships.** A student may meet several rules (e.g. GPA 9, income 10,000, 4 siblings, 40% disability). Only one scholarship is awarded: **the most valuable one**, in this order:
   `EXCELLENCE` (tuition + 1,000 EUR) > `RESIDENCE` (tuition + housing) > `INCLUSION` (tuition + assistive technology) > `LARGE_FAMILY` (tuition only).
   Residence ranks above Inclusion because housing is a recurring expense that is usually larger than one-off assistive technology funding.
2. **GPA gap 8.4–8.5.** The statement gives Residence as "6.5 to 8.4" and Excellence as ">= 8.5", which leaves values such as 8.45 uncovered. Residence is interpreted as `6.5 <= gpa < 8.5`, so there is no gap.
3. **"Below" / "under" / "exceeds"** are taken as strict (`<`, `>`). "Or higher" and "minimum" are taken as inclusive (`>=`).
4. **Valid input ranges.** Anything else throws `InvalidStudentDataException`:
   - GPA in [0, 10]
   - family income >= 0
   - distance >= 0
   - disability degree in [0, 100] (integer %)
   - siblings >= 0 (integer)
   - `NaN` and infinite values are rejected
   - a `null` student passed to `evaluate()` is rejected
5. A valid student who meets no rule gets `NONE`. This is a normal result, not an error.
