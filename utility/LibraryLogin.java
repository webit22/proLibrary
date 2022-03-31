package proLibrary.utility;

import proLibrary.preview.LibraryView;

import java.util.HashMap;
import java.util.Map;

//    Login 실패 시 ? signup() 호출

public class LibraryLogin {
    private String uName;
    private String uPW;
    Map<String, String> map = new HashMap<>();

//  View: name, pw 입력받음 -> Login 호출
    public LibraryLogin(String uName, String uPW) {
        this.uName = uName;
        this.uPW = uPW;
        start();
    }

//    LibraryView() 호출 -> 대여/반납 선택하기
    public void start(){
        map.put(uName, uPW);
        LibraryView.start();
    }
}
