package testcases.addressbook.model_based_dataset.po;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import config.Settings;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import testcases.addressbook.model_based_dataset.sql.SQL_Process;

public class Group {


    String group_header;
    String group_footer;
    String group_name;

    public Group(String group_name, String group_header, String group_footer) {
        this.group_name = group_name;
        this.group_header = group_header;
        this.group_footer = group_footer;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_header() {
        return group_header;
    }

    public void setGroup_header(String group_header) {
        this.group_header = group_header;
    }

    public String getGroup_footer() {
        return group_footer;
    }

    public void setGroup_footer(String group_footer) {
        this.group_footer = group_footer;
    }

    ArrayList<Group> groupList = new ArrayList<>();
    String filePath = Settings.PROJECT_PATH + "datadir\\group.csv";


    public ArrayList<Group> readGroupDat123a() {
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
            for (int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols) cols = tmp;
                }
            }
            for (int r = 0; r < rows; r++) {
                row = sheet.getRow(r);
                if (row != null && r != 0) {
                    String group_name = "";
                    String group_header = "";
                    String group_footer = "";

                    for (int c = 0; c < cols; c++) {
                        System.out.println("Coulms Size: - " + cols);
                        cell = row.getCell((short) c);
                        if (cell != null) {
                            if (c == 0) {
                                group_name = String.valueOf(cell);
                            } else if (c == 1) {
                                group_header = String.valueOf(cell);
                            } else if (c == 2) {
                                group_footer = String.valueOf(cell);
                            }
                        }
                    }
                    groupList.add(new Group(group_name, group_header, group_footer));
                }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return groupList;
    }


    public ArrayList<Group> readGroupData() {
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
                groupList.add(new Group(var[0],var[1],var[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return groupList;
    }

    public static void main(String[] args) {
        System.out.println(new Group(null, null, null)
                .readGroupData());
    }


    @Override
    public String toString() {
        return "Group{" +
                "group_header='" + group_header + '\'' +
                ", group_footer='" + group_footer + '\'' +
                ", group_name='" + group_name + '\'' +
                '}';
    }

    public static void addGroup(String group_name) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containGroup(group_name)){
            sql_process.deleteGroup(group_name);
        }
        sql_process.addGroup(group_name,"","");
        sql_process.Sql_close();
    }

    public static void addGroup(String group_name,String group_header,String group_footer) throws SQLException, IOException, ClassNotFoundException {
        SQL_Process sql_process = new SQL_Process();
        if (sql_process.containGroup(group_name)){
            sql_process.deleteGroup(group_name);
        }
        sql_process.addGroup(group_name,group_header,group_footer);
        sql_process.Sql_close();
    }

}
	
	

