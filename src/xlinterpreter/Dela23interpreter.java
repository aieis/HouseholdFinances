package xlinterpreter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import structure.Household;
import structure.Member;
import structure.Receipt;

import java.io.FileInputStream;
import java.io.IOException;

public class Dela23interpreter {
  HSSFWorkbook workbook;
  Household household;

  private final int NUM_COLUMNS = 9;

  public Dela23interpreter(Household household, String location){
    this.household = household;
    try {
      readWorkbook(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void readWorkbook(String location) throws IOException {
    String sheet_one = "SHEET1NAME";
    workbook = new HSSFWorkbook(new FileInputStream(location));
    HSSFSheet sheet = workbook.getSheet(sheet_one);

    int beginRow = 1; //BEGIN READING FROM HERE
    int numRows = sheet.getPhysicalNumberOfRows();

    for(; beginRow < numRows; beginRow++){

    }

  }

  public void addHouseholdTransaction(int receiptNumber, Receipt receipt){
    HSSFSheet sheet = workbook.getSheetAt(0);
    HSSFRow row = sheet.createRow(sheet.getPhysicalNumberOfRows());

    int i = 0;

    HSSFCell id = row.createCell(i++);
    HSSFCell date = row.createCell(i++);
    HSSFCell total_cost = row.createCell(i++);
    HSSFCell amount_allergic = row.createCell(i++);
    HSSFCell allergic_due = row.createCell(i++);
    HSSFCell regular_due = row.createCell(i++);
    HSSFCell debtors = row.createCell(i++);
    HSSFCell payer = row.createCell(i++);
    HSSFCell description = row.createCell(i++);

    id.setCellValue(receiptNumber);
    date.setCellValue(receipt.getDate().toString());
    total_cost.setCellValue(receipt.getTotalCost());
    amount_allergic.setCellValue(receipt.getAllergyCost());
    allergic_due.setCellValue(receipt.getCostForAllergicMembers());
    regular_due.setCellValue(receipt.getCostForNonAllergicMembers());
    debtors.setCellValue(getDebtorsString(receipt));
    payer.setCellValue(receipt.getPayer().getNickname());
    description.setCellValue(receipt.getDescription());

  }

  private String getDebtorsString(Receipt receipt){
    String debtors = "";
    for(Member m : receipt.getPeople()){
      debtors += "," + m.getNickname();
    }
    int n = 1;
    if(debtors.length() < 2){
      return "Y";
    }
    return debtors.substring(1);
  }

  private Receipt getReceipt(HSSFRow row){

    int i = 0;
    int num = (int) row.getCell(i++).getNumericCellValue();
    String date = row.getCell(i++).getStringCellValue();
    double total_cost = row.getCell(i++).getNumericCellValue();
    double amount_allergic = row.getCell(i++).getNumericCellValue();
    double regular_due

    return null;

  }
}
