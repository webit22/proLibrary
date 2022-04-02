package proLibrary.manage;

public class UserDTO {

    private String uNM;
    private String uPW;
    private String bookNM;
    private int bookSerialNum;

    public UserDTO(String uNM, String uPW){
        this.uNM = uNM;
        this.uPW = uPW;
    }

    public void printResult(){
        System.out.println("사용자명 : " + uNM + "\t사용자 비번 : " + uPW);
    }

//-------------getters-------------
    public String getuNM() {
        return uNM;
    }

    public String getuPW() {
        return uPW;
    }

    public String getBookNM() {
        return bookNM;
    }

    public int getBookSerialNum() {
        return bookSerialNum;
    }
}
