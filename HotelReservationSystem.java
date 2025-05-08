import java.util.*;

class Room {
    int roomNumber;
    String type;
    double price;
    boolean isBooked;

    Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }
}

class Booking {
    String guestName;
    Room room;
    String bookingId;

    Booking(String guestName, Room room) {
        this.guestName = guestName;
        this.room = room;
        this.bookingId = UUID.randomUUID().toString().substring(0, 8); // Short ID
    }

    public void displayBooking() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Number: " + room.roomNumber);
        System.out.println("Room Type: " + room.type);
        System.out.println("Price: $" + room.price);
    }
}

public class HotelReservationSystem {
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void initializeRooms() {
        rooms.add(new Room(101, "Single", 100.0));
        rooms.add(new Room(102, "Double", 150.0));
        rooms.add(new Room(103, "Suite", 300.0));
        rooms.add(new Room(104, "Single", 100.0));
        rooms.add(new Room(105, "Double", 150.0));
    }

    public static void searchRooms() {
        System.out.print("Enter room type (Single/Double/Suite): ");
        String type = scanner.nextLine();
        boolean found = false;
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked && room.type.equalsIgnoreCase(type)) {
                System.out.printf("Room %d - $%.2f%n", room.roomNumber, room.price);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available rooms of this type.");
        }
    }

    public static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter desired room type (Single/Double/Suite): ");
        String type = scanner.nextLine();

        for (Room room : rooms) {
            if (!room.isBooked && room.type.equalsIgnoreCase(type)) {
                System.out.printf("Room %d available for $%.2f. Proceed with booking? (yes/no): ", room.roomNumber, room.price);
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    room.isBooked = true;
                    Booking booking = new Booking(name, room);
                    bookings.add(booking);
                    processPayment(room.price);
                    System.out.println("Booking Confirmed!");
                    booking.displayBooking();
                    return;
                } else {
                    System.out.println("Booking cancelled.");
                    return;
                }
            }
        }

        System.out.println("No available rooms of this type.");
    }

    public static void viewBookings() {
        System.out.print("Enter your name to view bookings: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Booking booking : bookings) {
            if (booking.guestName.equalsIgnoreCase(name)) {
                booking.displayBooking();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bookings found under this name.");
        }
    }

    public static void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + "...");
        System.out.println("Payment successful!");
    }

    public static void main(String[] args) {
        initializeRooms();

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Search for Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View My Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    searchRooms();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewBookings();
                    break;
                case 4:
                    System.out.println("Thank you for using the system!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
