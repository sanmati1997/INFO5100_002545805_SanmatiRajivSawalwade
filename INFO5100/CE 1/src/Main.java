import java.util.*;

// =======================
// Base Class: Student
// =======================
abstract class Student {
    protected String name;
    protected List<Integer> quizzes;

    public Student(String name) {
        this.name = name;
        this.quizzes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addQuizScore(int score) {
        quizzes.add(score);
    }

    public List<Integer> getQuizzes() {
        return quizzes;
    }

    public double getQuizAverage() {
        if (quizzes.isEmpty()) return 0;
        double sum = 0;
        for (int q : quizzes) sum += q;
        return sum / quizzes.size();
    }
}

// =======================
// Derived Class: Part-Time Student
// =======================
class PartTimeStudent extends Student {
    public PartTimeStudent(String name) {
        super(name);
    }
}

// =======================
// Derived Class: Full-Time Student
// =======================
class FullTimeStudent extends Student {
    private int exam1;
    private int exam2;

    public FullTimeStudent(String name, int exam1, int exam2) {
        super(name);
        this.exam1 = exam1;
        this.exam2 = exam2;
    }

    public int getExam1() {
        return exam1;
    }

    public int getExam2() {
        return exam2;
    }
}

// =======================
// Session Class
// =======================
class Session {
    private List<Student> students;

    public Session() {
        students = new ArrayList<>();
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    // 1. Print quiz averages
    public void printQuizAverages() {
        System.out.println("\n=== Quiz Averages Per Student ===");
        for (Student s : students) {
            System.out.println(s.getName() + " → " + s.getQuizAverage());
        }
    }

    // 2. Print all quiz scores in ascending order
    public void printAllQuizzesSorted() {
        System.out.println("\n=== All Quiz Scores (Ascending Order) ===");

        List<Integer> all = new ArrayList<>();
        for (Student s : students) {
            all.addAll(s.getQuizzes());
        }

        Collections.sort(all);
        System.out.println(all);
    }

    // 3. Print part-time students
    public void printPartTimeStudents() {
        System.out.println("\n=== Part-Time Students ===");
        for (Student s : students) {
            if (s instanceof PartTimeStudent) {
                System.out.println(s.getName());
            }
        }
    }

    // 4. Print full-time exam scores
    public void printFullTimeExamScores() {
        System.out.println("\n=== Full-Time Students Exam Scores ===");
        for (Student s : students) {
            if (s instanceof FullTimeStudent ft) {
                System.out.println(ft.getName() +
                        " → Exam1: " + ft.getExam1() +
                        ", Exam2: " + ft.getExam2());
            }
        }
    }
}

// =======================
// Main Program
// =======================
public class Main {
    public static void main(String[] args) {

        Session session = new Session();
        Random rand = new Random();

        // Add 20 students with dummy data
        for (int i = 1; i <= 20; i++) {

            Student s;
            boolean full = (i % 2 == 0); // even = full-time

            if (full) {
                s = new FullTimeStudent("Student" + i,
                        rand.nextInt(41) + 60, // exam1: 60–100
                        rand.nextInt(41) + 60); // exam2: 60–100
            } else {
                s = new PartTimeStudent("Student" + i);
            }

            // Add 15 quiz scores (50–100)
            for (int q = 0; q < 15; q++) {
                s.addQuizScore(rand.nextInt(51) + 50);
            }

            session.addStudent(s);
        }

        // Call all session methods
        session.printQuizAverages();
        session.printAllQuizzesSorted();
        session.printPartTimeStudents();
        session.printFullTimeExamScores();
    }
}