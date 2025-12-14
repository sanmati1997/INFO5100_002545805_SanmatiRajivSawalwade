import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExercise {

    public static void main(String[] args) {

        // 1. Email Pattern
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        testPattern("Email Pattern", emailPattern,
                new String[]{"test@example.com", "abc@xyz"},       // positive
                new String[]{"test@.com", "invalid-email"});       // negative

        // 2. US Phone Number (123-456-7890)
        String phonePattern = "^\\d{3}-\\d{3}-\\d{4}$";
        testPattern("Phone Number Pattern", phonePattern,
                new String[]{"123-456-7890"},                      // positive
                new String[]{"12-444-7890", "1234567890"});        // negative

        // 3. Strong Password (min 8 chars, at least one number, one special char)
        String passwordPattern = "^(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";
        testPattern("Strong Password Pattern", passwordPattern,
                new String[]{"MyPass#123"},                        // positive
                new String[]{"weakpass", "NoSpecialCharacter1"});  // negative

        // 4. Date Format (MM/DD/YYYY)
        String datePattern = "^(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/\\d{4}$";
        testPattern("Date Pattern", datePattern,
                new String[]{"12/25/2025"},                        // positive
                new String[]{"13/20/2020", "12/40/2020"});         // negative

        // 5. Java Identifier (variable names)
        String identifierPattern = "^[A-Za-z_$][A-Za-z0-9_$]*$";
        testPattern("Java Identifier Pattern", identifierPattern,
                new String[]{"myVariable", "_temp123"},            // positive
                new String[]{"123start", "var-name"});             // negative
    }

    // Generic tester for each pattern
    private static void testPattern(String title, String regex, String[] positive, String[] negative) {
        System.out.println("\n=== " + title + " ===");
        Pattern pattern = Pattern.compile(regex);

        System.out.println("Positive Cases:");
        for (String text : positive) {
            Matcher matcher = pattern.matcher(text);
            System.out.println(text + " --> " + matcher.matches());
        }

        System.out.println("Negative Cases:");
        for (String text : negative) {
            Matcher matcher = pattern.matcher(text);
            System.out.println(text + " --> " + matcher.matches());
        }
    }
}
