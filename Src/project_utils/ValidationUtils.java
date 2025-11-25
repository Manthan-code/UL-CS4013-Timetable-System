package project_utils;
public class ValidationUtils {
    //constructor
    public ValidationUtils() {}

    //placeholder validation checks
    public boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public boolean isInteger(String value) {
        //will expand with try/catch later
        return true;
    }

    public boolean isValidCode(String moduleCode) {
        //format check later: e.g., "CS4013"
        return true;
    }
}
