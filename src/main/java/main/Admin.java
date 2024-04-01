//yaman
package main;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Admin extends User {

    // Default constructor
    public Admin() {
        super();
        setUserType(1);
    }

    // Constructor
    public Admin(int id, String name, String password, int userType) {
        super(id, name, password, 1);
    }

    public void addRoomBooking(int userId, int roomId, LocalDate bookingDate, LocalTime startTime, LocalTime endTime) {
        String sql = "INSERT INTO RoomBookings (user_id, room_id, booking_date, start_time, end_time) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, roomId);
            pstmt.setDate(3, Date.valueOf(bookingDate));
            pstmt.setTime(4, Time.valueOf(startTime));
            pstmt.setTime(5, Time.valueOf(endTime));
            pstmt.executeUpdate();
            System.out.println("Room booking successfully added.");
        } catch (SQLException e) {
            System.out.println("Error adding room booking: " + e.getMessage());
        }
    }

    public void logEquipmentMaintenance(int equipmentId, LocalDate checkupDate, boolean isAvailable) {
        String sql = "INSERT INTO EquipmentMaintenance (equipment_id, checkup_date, is_available) VALUES (?, ?, ?)";
        try (Connection conn = DbUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, equipmentId);
            pstmt.setDate(2, Date.valueOf(checkupDate));
            pstmt.setBoolean(3, isAvailable);
            pstmt.executeUpdate();
            System.out.println("Equipment maintenance log successfully added.");
        } catch (SQLException e) {
            System.out.println("Error logging equipment maintenance: " + e.getMessage());
        }
    }

    public void addClassSchedule(String className, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, int roomId) {
        String sql = "INSERT INTO ClassSchedules (class_name, schedule_date, start_time, end_time, room_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, className);
            pstmt.setDate(2, Date.valueOf(scheduleDate));
            pstmt.setTime(3, Time.valueOf(startTime));
            pstmt.setTime(4, Time.valueOf(endTime));
            pstmt.setInt(5, roomId);
            pstmt.executeUpdate();
            System.out.println("Class schedule successfully added.");
        } catch (SQLException e) {
            System.out.println("Error adding class schedule: " + e.getMessage());
        }
    }

    public void processPayment(int userId, BigDecimal amountDue) {
        String sql = "INSERT INTO BillingInfo (user_id, amount_due, payment_status, payment_date) VALUES (?, ?, 'Paid', ?)";
        try (Connection conn = DbUtil.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setBigDecimal(2, amountDue);
            pstmt.setDate(3, Date.valueOf(LocalDate.now())); // Assuming payment is processed immediately
            pstmt.executeUpdate();
            System.out.println("Payment processed successfully.");
        } catch (SQLException e) {
            System.out.println("Error processing payment: " + e.getMessage());
        }
    }


}

