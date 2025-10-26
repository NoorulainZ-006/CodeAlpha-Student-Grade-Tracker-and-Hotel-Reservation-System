//hotel reservation system.    
import java.util.ArrayList;
import java.util.Scanner;

// ===== ROOM CLASS =====
class Room {
    int roomNumber;
    String roomType;
    double price;
    boolean isBooked;

    Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isBooked = false;
    }

    void displayRoomInfo() {
        System.out.println("Room No: " + roomNumber + " | Type: " + roomType + " | Price per day: " + price + " | Booked: " + isBooked);
    }
}

// ===== RESERVATION CLASS =====
class Reservation {
    int reservationId;
    String customerName;
    Room room;
    int days;
    double totalAmount;

    Reservation(int reservationId, String customerName, Room room, int days) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.room = room;
        this.days = days;
        this.totalAmount = room.price * days;
    }

    void displayReservation() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Customer: " + customerName);
        System.out.println("Room No: " + room.roomNumber + " (" + room.roomType + ")");
        System.out.println("Days: " + days);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("------------------------------------");
    }
}

// ===== HOTEL CLASS =====
class Hotel {
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Reservation> reservations = new ArrayList<>();
    int nextReservationId = 1;

    // Constructor to add some sample rooms
    Hotel() {
        rooms.add(new Room(101, "Standard", 4000));
        rooms.add(new Room(102, "Standard", 4000));
        rooms.add(new Room(201, "Deluxe", 7000));
        rooms.add(new Room(202, "Deluxe", 7000));
        rooms.add(new Room(301, "Suite", 10000));
    }

    void showAvailableRooms(String type) {
        boolean found = false;
        for (Room r : rooms) {
            if (r.roomType.equalsIgnoreCase(type) && !r.isBooked) {
                r.displayRoomInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No available rooms for type: " + type);
    }

    void bookRoom(String customerName, String type, int days) {
        for (Room r : rooms) {
            if (r.roomType.equalsIgnoreCase(type) && !r.isBooked) {
                r.isBooked = true;
                Reservation res = new Reservation(nextReservationId++, customerName, r, days);
                reservations.add(res);
                System.out.println("Room booked successfully!");
                System.out.println("Payment of " + res.totalAmount + " simulated successfully.");
                return;
            }
        }
        System.out.println("No available " + type + " rooms at the moment.");
    }

    void cancelReservation(int reservationId) {
        for (Reservation res : reservations) {
            if (res.reservationId == reservationId) {
                res.room.isBooked = false;
                reservations.remove(res);
                System.out.println("Reservation ID " + reservationId + " cancelled successfully!");
                return;
            }
        }
        System.out.println("Reservation not found.");
    }

    void viewAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation res : reservations) {
                res.displayReservation();
            }
        }
    }
}

// ===== MAIN CLASS =====
public class HotelSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Hotel hotel = new Hotel();
        int choice;

        do {
            System.out.println("\n----------------------------------");
            System.out.println("=== Hotel Reservation System ===");
            System.out.println("----------------------------------");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. View All Reservations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter room type (Standard/Deluxe/Suite): ");
                    input.nextLine(); // clear buffer
                    String type = input.nextLine();
                    hotel.showAvailableRooms(type);
                    break;

                case 2:
                    input.nextLine(); // clear buffer
                    System.out.print("Enter your name: ");
                    String name = input.nextLine();
                    System.out.print("Enter room type (Standard/Deluxe/Suite): ");
                    String roomType = input.nextLine();
                    System.out.print("Enter number of days: ");
                    int days = input.nextInt();
                    hotel.bookRoom(name, roomType, days);
                    break;

                case 3:
                    System.out.print("Enter Reservation ID to cancel: ");
                    int rid = input.nextInt();
                    hotel.cancelReservation(rid);
                    break;

                case 4:
                    hotel.viewAllReservations();
                    break;

                case 5:
                    System.out.println("Exiting... Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        input.close();
    }
}
