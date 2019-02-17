package structure;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
  private final Transaction transaction;
  private final List<Member> people;
  private double allergyCost;

  String description;

  private int numPeopleAllergic;

  public Receipt(Transaction transaction, List<Member> people, double allergyCost, String description) {
    this.transaction = transaction;
    this.people = people;
    this.allergyCost = allergyCost;

    this.description = description;
    allergyPeople();
  }

  private void allergyPeople() {
    for (int i = 0; i < people.size(); i++) {
      if (people.get(i).isAllergic()) numPeopleAllergic++;
    }
  }

  public Member getPayer() {
    return transaction.getPayer();
  }

  public ModernDate getDate() {
    return transaction.getDate();
  }

  public String getDescription() {
    return description;
  }

  public List<Member> getPeople() {
    return people;
  }

  public double getTotalCost() {
    return transaction.getAmount();
  }

  public double getAllergyCost() {
    return allergyCost;
  }

  public int getNumPeopleAllergic() {
    return numPeopleAllergic;
  }

  public double getCostForAllergicMembers() {
    return (getTotalCost() - getAllergyCost()) / (people.size() + 1);
  }

  public double getCostForNonAllergicMembers() {
    return (getTotalCost() - getAllergyCost()) * numPeopleAllergic / (people.size() + 1);
  }

  public List<Transaction> getSubsequentTransactions() {

    List<Transaction> subsequentTransactions = new ArrayList<>();
    for (Member m : people) {
      if (m.isAllergic()) {
        subsequentTransactions.add(new Transaction(m, getPayer(), getCostForAllergicMembers(), transaction.getDate()));
      } else {
        subsequentTransactions.add(new Transaction(m, getPayer(), getCostForNonAllergicMembers(), transaction.getDate()));
      }
    }

    return subsequentTransactions;
  }

}