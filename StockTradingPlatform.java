import java.util.*;

class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Market {
    private final Map<String, Stock> stocks = new HashMap<>();

    public Market() {
        stocks.put("AAPL", new Stock("AAPL", 150.0));
        stocks.put("GOOG", new Stock("GOOG", 2800.0));
        stocks.put("TSLA", new Stock("TSLA", 700.0));
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol.toUpperCase());
    }

    public void displayMarket() {
        System.out.println("\n--- Market Data ---");
        for (Stock stock : stocks.values()) {
            System.out.printf("%s: $%.2f%n", stock.symbol, stock.price);
        }
    }
}

class Portfolio {
    private final Map<String, Integer> holdings = new HashMap<>();
    private double cash;

    public Portfolio(double initialCash) {
        this.cash = initialCash;
    }

    public void buyStock(Stock stock, int quantity) {
        double totalCost = stock.price * quantity;
        if (totalCost <= cash) {
            holdings.put(stock.symbol, holdings.getOrDefault(stock.symbol, 0) + quantity);
            cash -= totalCost;
            System.out.printf("Bought %d shares of %s%n", quantity, stock.symbol);
        } else {
            System.out.println("Not enough cash to buy stock.");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        int owned = holdings.getOrDefault(stock.symbol, 0);
        if (owned >= quantity) {
            holdings.put(stock.symbol, owned - quantity);
            cash += stock.price * quantity;
            System.out.printf("Sold %d shares of %s%n", quantity, stock.symbol);
        } else {
            System.out.println("Not enough shares to sell.");
        }
    }

    public void displayPortfolio(Market market) {
        System.out.println("\n--- Portfolio Summary ---");
        System.out.printf("Cash: $%.2f%n", cash);
        double totalValue = cash;

        for (String symbol : holdings.keySet()) {
            int quantity = holdings.get(symbol);
            double price = market.getStock(symbol).price;
            double value = price * quantity;
            totalValue += value;
            System.out.printf("%s: %d shares (Value: $%.2f)%n", symbol, quantity, value);
        }

        System.out.printf("Total Portfolio Value: $%.2f%n", totalValue);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Market market = new Market();
        Portfolio portfolio = new Portfolio(10000.0); // Starting with $10,000

        while (true) {
            System.out.println("\n===== Stock Trading Platform =====");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear input buffer

            switch (choice) {
                case 1:
                    market.displayMarket();
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int buyQty = scanner.nextInt();
                    Stock buyStock = market.getStock(buySymbol);
                    if (buyStock != null) {
                        portfolio.buyStock(buyStock, buyQty);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int sellQty = scanner.nextInt();
                    Stock sellStock = market.getStock(sellSymbol);
                    if (sellStock != null) {
                        portfolio.sellStock(sellStock, sellQty);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 4:
                    portfolio.displayPortfolio(market);
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
