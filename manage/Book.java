package proLibrary.manage;

import proLibrary.util.LibraryUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book {


    public static Map<Integer, String> rentList = new HashMap<>(); // 사용자가 대여한 도서 정보

    public Book(){
    }

    public static void checkEmpty(){
        if(rentList.isEmpty()) {
            System.out.println("대여하신 책이 없습니다.");
            LibraryUtil.restart();
        }
    }

    public static void add(Integer bSeq){
        String temp = "";

        List<BookDTO> bookDTOList = new ArrayList<>(); // 도서관의 전체 도서 목록
        for(int i = 0; i < 4; i++){
            bookDTOList.add(new BookDTO(i,"Author"+i,"Title"+i));
        }

        BookDTO bDTO = bookDTOList.get(bSeq);

        temp = bDTO.getBAuthor() + "\t" + bDTO.getBTitle();
        rentList.put(bSeq, temp);
    }

    public static void delete(){
        rentList.clear();
    }

    public static void delete(Integer bSeq){
        rentList.remove(bSeq);
    }
}