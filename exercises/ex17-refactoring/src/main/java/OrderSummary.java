import java.util.Locale;

/**
 * Exercise (Chapter 13: Refactoring Techniques) — cleaning up a long method.
 *
 * <p>This example demonstrates refactoring a long method while preserving its
 * behavior. {@link #summarize(String, String[], double[])} delegates calculations
 * and report formatting to focused helper methods, and
 * {@code OrderSummaryTest} verifies the exact output.
 *
 * <p>Refactorings demonstrated:
 *
 * <ul>
 *   <li><em>Extract Method</em> (13.2) for calculations and report formatting.
 *   <li><em>Split Loop</em> (13.5) so subtotaling and premium-item counting are
 *       separate operations.
 *   <li><em>Slide Statements</em> (13.6) so local variables are declared near
 *       their first use.
 *   <li>Replace magic numbers with named constants.
 * </ul>
 *
 * <p>Relevant reading: 13.2 Extract Method, 13.5 Split Loop, 13.6 Slide
 * Statements.
 */
public class OrderSummary {
  private static final double TAX_RATE = 0.13;
  private static final double DISCOUNT_THRESHOLD = 200.0;
  private static final double DISCOUNT_RATE = 0.10;
  private static final double PREMIUM_ITEM_THRESHOLD = 100.0;

  /**
   * Builds a human-readable, multi-line summary of an order.
   *
   * <p>An order gets a 10% discount when its subtotal is more than $200, and tax
   * is then charged on the discounted amount. An item costing $100 or more counts
   * as a "premium" item.
   *
   * @param customer the customer's name
   * @param itemNames the name of each item, in order
   * @param itemPrices the price of each item, in the same order as {@code itemNames}
   * @return the formatted summary, with lines separated by {@code \n}
   */
  public static String summarize(String customer, String[] itemNames, double[] itemPrices) {
    double subtotal = calculateSubtotal(itemPrices);
    int premiumCount = countPremiumItems(itemPrices);
    double discount = subtotal > DISCOUNT_THRESHOLD ? subtotal * DISCOUNT_RATE : 0.0;
    double taxable = subtotal - discount;
    double tax = taxable * TAX_RATE;
    double total = taxable + tax;

    return buildReport(customer, itemNames, itemPrices, premiumCount, subtotal, discount, tax, total);
  }

  private static double calculateSubtotal(double[] itemPrices) {
    double subtotal = 0.0;
    for (double itemPrice : itemPrices) {
      subtotal += itemPrice;
    }
    return subtotal;
  }

  private static int countPremiumItems(double[] itemPrices) {
    int premiumCount = 0;
    for (double itemPrice : itemPrices) {
      if (itemPrice >= PREMIUM_ITEM_THRESHOLD) {
        premiumCount++;
      }
    }
    return premiumCount;
  }

  private static String buildReport(
      String customer,
      String[] itemNames,
      double[] itemPrices,
      int premiumCount,
      double subtotal,
      double discount,
      double tax,
      double total) {
    StringBuilder report = new StringBuilder();
    report.append("Order summary for ").append(customer).append("\n");
    report.append("----------------------\n");
    for (int i = 0; i < itemNames.length; i++) {
      report.append(formatItemLine(itemNames[i], itemPrices[i]));
    }
    report.append(String.format(Locale.US, "Items: %d\n", itemNames.length));
    report.append(String.format(Locale.US, "Premium items: %d\n", premiumCount));
    report.append(String.format(Locale.US, "Subtotal: $%.2f\n", subtotal));
    report.append(String.format(Locale.US, "Discount: $%.2f\n", discount));
    report.append(String.format(Locale.US, "Tax: $%.2f\n", tax));
    report.append(String.format(Locale.US, "Total: $%.2f", total));
    return report.toString();
  }

  private static String formatItemLine(String itemName, double itemPrice) {
    return String.format(Locale.US, "%s: $%.2f\n", itemName, itemPrice);
  }
}
