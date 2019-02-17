package structure;

import enums.Flow;
import xlinterpreter.Dela23interpreter;

import java.io.Serializable;
import java.util.*;

public class Household implements Serializable{
  private List<Member> members;

  private Map<Integer, Receipt> receipts;

  private Map<Integer, Transaction> unresolvedTransactionsMap;
  private Map<Integer, Transaction> resolvedTransactionsMap;

  private int transactionNumber;
  private int receiptNumber;

  Dela23interpreter interpreter;
  public Household(String filename){
    members = new ArrayList<>();
    resolvedTransactionsMap = new HashMap<>();
    unresolvedTransactionsMap = new HashMap<>();
    updateTransactionNumber();
    updateReceiptNumber();
    interpreter = new Dela23interpreter(filename);
  }

  private void updateTransactionNumber() {
    transactionNumber = resolvedTransactionsMap.size() + unresolvedTransactionsMap.size();
  }

  private void updateReceiptNumber(){
    receiptNumber = receipts.size();
  }

  public List<Member> getMembers() {
    return members;
  }

  public Map<Integer, Receipt> getReceipts() {
    return receipts;
  }

  public List<Transaction> getResolvedTransactionsMap() {
    return (List<Transaction>) resolvedTransactionsMap.values();
  }

  public List<Transaction> getUnresolvedTransactions() {
    return (List<Transaction>) (unresolvedTransactionsMap.values());
  }

  public List<Transaction> getAllTransactions(){
    List<Transaction> allTransactions = new ArrayList<>();

    allTransactions.addAll(getUnresolvedTransactions());
    allTransactions.addAll(getResolvedTransactionsMap());

    Collections.sort(allTransactions);
    return allTransactions;
  }


  public boolean addMember(Member member){
    return members.add(member);
  }

  public boolean addReceiptAllHousehold(Member payer, Member receiver,
                                        double amount, ModernDate date, double allergyCost,
                                        String description)
  {

    Transaction initialTransaction = new Transaction(payer, receiver, amount, date);
    resolvedTransactionsMap.put(transactionNumber++, initialTransaction);

    List<Member> people = new ArrayList<>();

    for(Member member : members){
      if(member != payer)
        people.add(member);
    }

    Receipt receipt = new Receipt(initialTransaction, people, allergyCost, description);
    receipts.put(receiptNumber, receipt);
    addListToUnresolvedTransactionsMap(receipt.getSubsequentTransactions());

    interpreter.addHouseholdTransaction(receiptNumber, receipt);
    receiptNumber++;

    return true;
  }

  private boolean addListToUnresolvedTransactionsMap(List<Transaction> transactions){
    for(Transaction t : transactions){
      unresolvedTransactionsMap.put(transactionNumber++, t);
    }
    return true;
  }

  public boolean makePayment(int transactionNumber){
    Transaction t = unresolvedTransactionsMap.get(transactionNumber);

    if(!isValidTransaction(t)){
      return false;
    }

    t.getReceiver().makeTransaction(t, Flow.OUT);

    unresolvedTransactionsMap.remove(transactionNumber);
    resolvedTransactionsMap.put(transactionNumber, t);

    return true;
  }

  private boolean isValidTransaction(Transaction t) {
    return t != null && (t.getPayer().getOwedFromOthers().contains(t) && t.getReceiver().getOwesToOthers().contains(t));
  }

  public boolean makePayment(Transaction transaction){

    int transactionNumber = unresolvedTransactionsMap.

  }
}
