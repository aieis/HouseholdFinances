package structure;

public class ModernDate implements Comparable<ModernDate>{
  private final int day;
  private final int month;
  private final int year;

  public ModernDate(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public static ModernDate getModernDate(String date){
    return new ModernDate(Integer.parseInt(date.substring(0, 2)),
            Integer.parseInt(date.substring(2, 4)),
            Integer.parseInt(date.substring(4, date.length())));
  }

  public String toString(){
    return String.format("%02d", Integer.toString(day)) + "-"
        + String.format("%02d", Integer.toString(month)) + "-"
        + Integer.toString(year);
  }

  @Override
  public int compareTo(ModernDate o) {

    if(year > o.year){
      return 1;
    }
    else if(year > o.year){
      return -1;
    }
    else if(month > o.month){
      return 1;
    }
    else if(month < o.month){
      return -1;
    }
    else if(day > o.day){
      return 1;
    }
    else if(day < o.day){
      return 01;
    }
    else
      return 0;

    /*
    int comp = (365 * (year - o.year) + 30 * (month - o.month) + day - o.day);

    return comp;
    */
  }
}
