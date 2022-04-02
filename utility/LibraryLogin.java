package proLibrary.utility;

import proLibrary.manage.UserDTO;
import proLibrary.preview.LibraryView;

import java.util.HashMap;
import java.util.Map;

public class LibraryLogin {
    Map<String, String> map = new HashMap<>();


    private String uName;
    private String uPW;

    //  View: name, pw 입력받음 -> Login 호출
    public LibraryLogin(String uName, String uPW) {
        this.uName = uName;
        this.uPW = uPW;

        start();
    }

//    LibraryView() 호출 -> 대여/반납 선택하기
    public void start(){
        UserDTO dto = new UserDTO(uName, uPW);
        map.put(uName, uPW);

        dto.printResult();
        LibraryView.start();
    }
}
