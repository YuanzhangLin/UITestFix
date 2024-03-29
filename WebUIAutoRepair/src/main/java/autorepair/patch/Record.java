package autorepair.patch;

public class Record {
    /**A recorded event, including a comparison of the old version and the new version*/
    public String oldLocator;
    public String repairLocator;
    public String standardRepairLocator;
    public String validaResult;
    public String differenceResult;
    public String checkResult;
    public String oldImagePath;
    public String newImagePath;
    public String message;
    public String method;
    public String oldEventPath;
    public String newEventPath;

    public Record(String oldLocator, String repairLocator, String standardRepairLocator,
                  String validaResult, String checkResult,String differenceResult,
                  String oldImagePath, String newImagePath,
                  String oldEventPath, String newEventPath,
                  String method) {
        this.differenceResult = differenceResult;
        this.oldLocator = oldLocator;
        this.repairLocator = repairLocator;
        this.standardRepairLocator = standardRepairLocator;
        this.validaResult = validaResult;
        this.checkResult = checkResult;
        this.oldImagePath = oldImagePath;
        this.newImagePath = newImagePath;
        this.oldEventPath = oldEventPath;
        this.newEventPath = newEventPath;
        this.method  = method;
        message = "";
    }

    public Record(String oldLocator, String repairLocator, String standardRepairLocator,
                  String validaResult, String differenceResult,String checkResult,
                  String oldImagePath, String newImagePath,String method) {
        this.differenceResult = differenceResult;
        this.oldLocator = oldLocator;
        this.repairLocator = repairLocator;
        this.standardRepairLocator = standardRepairLocator;
        this.validaResult = validaResult;
        this.checkResult = checkResult;
        this.oldImagePath = oldImagePath;
        this.newImagePath = newImagePath;
        this.method  = method;
        message = "";
    }


    public Record(String message){
        this.message = message;
        this.oldLocator = "";
        this.repairLocator = "";
        this.standardRepairLocator = "";
        this.validaResult = "*";
        this.checkResult = "";
        this.oldImagePath = "";
        this.newImagePath = "";
        this.method = "";
    }

    public Record(String message,String standardRepairLocator){
        this.message = message;
        this.oldLocator = "";
        this.repairLocator = "";
        this.standardRepairLocator = standardRepairLocator;
        this.validaResult = "**";
        this.checkResult = "";
        this.oldImagePath = "";
        this.newImagePath = "";
        this.method = "";
    }
    public Record(String message,String oldLocator,String oldImagePath,String method){
        this.message = message;
        this.oldLocator = oldLocator;
        this.repairLocator = "";
        this.standardRepairLocator ="";
        this.validaResult = "**";
        this.checkResult = "";
        this.oldImagePath = oldImagePath;
        this.newImagePath = "";
        this.method = method;
    }

}
