import java.util.Scanner;

public class AIIntelligence {

    public static String getResponse(String input) {
        input = input.toLowerCase();

        if (input.contains("hello") || input.contains("hi")) {
            return "Hello! How can I assist you today?";
        } else if (input.contains("how are you")) {
            return "I'm functioning within normal parameters. How can I help?";
        } else if (input.contains("your name")) {
            return "I'm AIIntelligence, your virtual assistant.";
        } else if (input.contains("bye")) {
            return "Goodbye! Have a great day.";
        } else if (input.contains("weather")) {
            return "I can't access live weather updates yet, but it’s always a good day to code!";
        } else {
            return "Sorry, I didn't understand that. Could you please rephrase?";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("AIIntelligence: Hello! I am your assistant. Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("AIIntelligence: Goodbye!");
                break;
            }

            String response = getResponse(input);
            System.out.println("AIIntelligence: " + response);
        }

        scanner.close();
    }
}
