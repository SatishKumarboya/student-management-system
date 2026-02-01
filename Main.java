import java.sql.*;

public class Main {

    private static final String URL =
    "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            insertStudent("Satish", "satish@gmail.com", "CSE");
            fetchStudents();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void insertStudent(String name, String email, String course) {
        String sql = "INSERT INTO students(name, email, course) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.executeUpdate();

            System.out.println("Student inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void fetchStudents() {
        String sql = "SELECT * FROM students";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Student Records:");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("email") + " | " +
                    rs.getString("course")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
