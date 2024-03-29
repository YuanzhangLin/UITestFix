package testcases.addressbook.model_based_dataset.po;

import java.io.*;
import java.util.ArrayList;

import config.Settings;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Contact {
	
	String first_name;
	String last_name;
	String company;
	String address;
	String phone;
	String email;
	String day;
	String year;
	String month;
	public Contact(String first_name, String last_name, String company, String address, String phone, String email,
			String day, String month, String year) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.company = company;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.day = day;
		this.year = year;
		this.month = month;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	
	
ArrayList<Contact> studentList = new ArrayList<>();
	
	String filePath = Settings.PROJECT_PATH + "datadir\\contact.csv";

	public ArrayList<Contact> readCo3ntactData()
	{
	try {
	   POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
	   HSSFWorkbook wb = new HSSFWorkbook(fs);
	   HSSFSheet sheet = wb.getSheetAt(0);
	   HSSFRow row;
	   HSSFCell cell;

	   int rows; // No of rows
	   rows = sheet.getPhysicalNumberOfRows();

	   int cols = 0; // No of columns
	   int tmp = 0;

	   // This trick ensures that we get the data properly even if it doesn't start from first few rows
	   for(int i = 0; i < 10 || i < rows; i++) {
	       row = sheet.getRow(i);
	       if(row != null) {
	           tmp = sheet.getRow(i).getPhysicalNumberOfCells();
	           if(tmp > cols) cols = tmp;
	       }
	   }
	  for(int r = 0; r < rows; r++) {
	       row = sheet.getRow(r);
	       if(row != null && r!=0) {
	    	   String first_name="";
	    		String last_name="";
	    		String company="";
	    		String address="";
	    		String phone="";
	    		String email="";
	    		String day="";
	    		String year="";
	    		String month="";
	           for(int c = 0; c < cols; c++) {
	        	   System.out.println("Coulms Size: - "+cols);
	               cell = row.getCell((short)c);
	               if(cell != null) {
	                  if(c==0)
	                  {
	                	  first_name = String.valueOf(cell);
	                  }else if(c==1)
	                  {
	                	  last_name = String.valueOf(cell);
	                  }else if(c==2)
	                  {
	                	  company = String.valueOf(cell);
	                  }
	                  else if(c==3)
	                  {
	                	  address = String.valueOf(cell);
	                  }else if(c==4)
	                  {
	                	  phone = String.valueOf(cell);
	                  }
	                  else if(c==5)
	                  {
	                	  email = String.valueOf(cell);
	                  }else if(c==6)
	                  {
	                	  day = String.valueOf(cell);
	                  }
	                  else if(c==7)
	                  {
	                	  month = String.valueOf(cell);
	                	  System.out.println("Minth: - "+month);
	                  }else if(c==8)
	                  {
	                	  year = String.valueOf(cell);
	                	  System.out.println("Year: - "+year);
	                  }
	               }
	          }
	           studentList.add(new Contact(first_name,last_name,company,address,phone,email,day,month,year));
	       }
	   }
	} catch(Exception ioe) {
	   ioe.printStackTrace();
	}
	return studentList;
	}


	public ArrayList<Contact> readContactData() {
		File csv = new File(filePath);
		csv.setReadable(true);
		csv.setWritable(true);
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(new FileInputStream(csv), "UTF-8");
			br = new BufferedReader(isr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				String[] var = line.split(",");
				studentList.add(new Contact(var[0],var[1],var[2],var[3],var[4],var[5],var[6],var[7],var[8]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return studentList;
	}
	
	

}
