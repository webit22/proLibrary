package proLibrary.manage;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// 사용자가 대여한 책 정보
public class BookDTO {
    // bookSerialNumber, bookTitle, bookAuthor
    private String[][] arrBookInfo;
    Map<Integer, String> mapList = new HashMap<>(); // bookSerialNumber(key), arrBookInfo(value)

//    SerialNum type : int or string??
//    map key(seq) type : int
    private String bSerialNum;
    private String bAuthor;
    private String bTitle;
//    arrBookInfo index에 접근하기 위해 bSerialNum을 int형으로 바꾼 값
    private int bSN;

//    생성자
    public BookDTO(String bSerialNum){
        this.arrBookInfo = new String[][]{ {"author1", "title1"}
                                         , {"author1", "title2"}
                                         , {"author2", "title1"}
                                         , {"author2", "title2"} };
        this.bSerialNum = bSerialNum;
        bSN = Integer.parseInt(bSerialNum);
        this.bAuthor = arrBookInfo[bSN][0];
        this.bTitle = arrBookInfo[bSN][1];

        for(int i = 0; i < arrBookInfo.length; i++){ // array.length 맞는지 체크해보기!!!
            for(int j = 0; j < 2; j++){
                mapList.put(i, arrBookInfo[i][j]);
            }
        }

    }

// ----------------< getters >------------------

    public String getBSerialNum() {
        return bSerialNum;
    }

    public String getBAuthor() {
        return bAuthor;
    }

    public String getBTitle() {
        return bTitle;
    }

    public String[][] getArrBookInfo() {
        return arrBookInfo;
    }

    public Map<Integer, String> getMapList() {
        return mapList;
    }
}
