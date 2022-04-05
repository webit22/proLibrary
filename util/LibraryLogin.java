package proLibrary.util;

import proLibrary.manage.FileManager;
import proLibrary.manage.UserDTO;
import proLibrary.preview.LibraryView;

public class LibraryLogin {
    private String uID;
    private String uPW;

//    View: name, pw 입력받음 -> Login 호출
    public LibraryLogin(String uID, String uPW) {
        this.uID = uID;
        this.uPW = uPW;

        start();
    }

    public void start(){
        UserDTO uDTO = new UserDTO(uID, uPW);
        FileManager fm = new FileManager();
        fm.writeUserInfo();

        LibraryView.start();
    }
}
