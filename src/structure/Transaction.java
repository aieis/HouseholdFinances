package structure;

public class Transaction implements Comparable<Transaction> {
  private final Member payer;
  private final Member receiver;

  private final double amount;
  private final ModernDate date;

  public Transaction(Member payer, Member receiver, double amount, ModernDate date) {
    this.payer = payer;
    this.receiver = receiver;
    this.amount = amount;

    this.date = date;
  }

  public Member getPayer() {
    return payer;
  }

  public Member getReceiver() {
    return receiver;
  }

  public double getAmount() {
    return amount;
  }

  public ModernDate getDate() {
    return date;
  }

  @Override
  public int compareTo(Transaction o) {
    return date.compareTo(o.getDate());
  }
}
