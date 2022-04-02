package proLibrary.manage;

public class UserDTO {

    private static String uID;
    private static String uPW;

    public UserDTO(String uID, String uPW){
        this.uID = uID;
        this.uPW = uPW;
    }

    public void printResult(){
        System.out.println("사용자 ID : " + uID + "\t사용자 PW : " + uPW);
    }


//-------------------< getters >-----------------------

    public static String getUID() {
        return uID;
    }

    public static String getUPW() {
        return uPW;
    }
}
