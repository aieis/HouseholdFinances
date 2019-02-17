package structure;

import enums.Flow;
import enums.Name;

import java.util.ArrayList;
import java.util.List;

public class Member{
  String name;
  String nickname;
  private boolean allergic;

  //money this member owes to others
  private List<Transaction> owesToOthers;

  //Money others owe this member
  private List<Transaction> owedFromOthers;

  private List<Transaction> moneyIn;
  private List<Transaction> moneyOut;

  public Member(String name, String nickname, boolean allergic) {
    this.name = name;
    this.nickname = nickname;
    this.allergic = allergic;

    owesToOthers = new ArrayList<>();
    owedFromOthers = new ArrayList<>();

    moneyIn = new ArrayList<>();
    moneyOut = new ArrayList<>();
  }

  public boolean isAllergic(){
    return allergic;
  }

  public double owesToOthers(Member member){
    double moneyOwed = 0;
    for(Transaction t : owesToOthers){
      if(t.getReceiver() == member){
        moneyOwed += t.getAmount();
      }
    }

    for(Transaction t : owedFromOthers){
      if(t.getPayer() == member){
        moneyOwed += t.getAmount();
      }
    }

    return moneyOwed;
  }

  public List<Transaction> getOwesToOthers() {
    return owesToOthers;
  }

  public List<Transaction> getOwedFromOthers() {
    return owedFromOthers;
  }

  public boolean makeTransaction(Transaction t, Flow flow){
    if(flow == Flow.IN){
      moneyIn.add(t);
      owedFromOthers.remove(t);
    } else {
      moneyOut.add(t);
      owesToOthers.remove(t);
    }
    return true;
  }

  public String getName() {
    return name;
  }

  public String getNickname() {
    return nickname;
  }
}
