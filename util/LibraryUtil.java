package proLibrary.util;

import proLibrary.manage.Book;
import proLibrary.manage.FileManager;
import proLibrary.preview.LibraryView;

import java.util.*;
import java.lang.System;

public class LibraryUtil {
    FileManager fm = new FileManager();
    Scanner sc = new Scanner(System.in);

    private final String exit;
    private Integer bSeq; // 도서번호
    private int cnt;

    public LibraryUtil(){
        exit = "\n도서 관리 프로그램을 종료합니다.";
        bSeq = 0;
        cnt = 0;
    }

    public void start(int choice){
        int totalCnt;
        switch (choice) {
            case 1 -> rent();
            case 2 -> {
                Book.checkEmpty();
                totalCnt = Book.rentList.size();
                turnIn(totalCnt);
            }
            case 3 -> System.out.println(exit);
            default -> restart();
        }
    }

//  대여 서비스
    public void rent(){
        System.out.println("\n------< 도서 목록 >------");
        fm.readBookInfo();

        System.out.print("대여할 책 개수 : ");
        cnt = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < cnt; i++) {
            if(cnt == 1)
                System.out.print("대여할 책 도서번호 : ");
            else
                System.out.print((i+1) + "번째로 대여할 책 도서번호 : ");

            bSeq = Integer.parseInt(sc.nextLine());
            Book.add(bSeq);
        }
        fm.writeUserInfo(printResult());
        moreRent();
    }


//  반납 서비스
    public void turnIn(int totalCnt){
        String temp;

        printResult();
        cnt = numTurnIn(totalCnt); // 반납할 책 개수

        if(cnt == totalCnt) {
            System.out.print("전부 삭제하시겠습니까?(y/n)");
            temp = sc.nextLine();

            if (temp.equals("y") || temp.equals("Y")) {
                Book.delete();
                totalCnt = 0;
                System.out.println("대여 목록이 비어있습니다 (0개 남음)");
            }
        } else{
            turnInService(cnt);
            totalCnt--;
        }

        if(totalCnt != 0) moreTurnIn(totalCnt);
        else restart();
    }

    private int numTurnIn(int totalCnt) {
        System.out.print("반납할 책 개수 : ");
        int num = Integer.parseInt(sc.nextLine());

        if (num == 0) {
            restart();
        } else if(num < 0 || num > (totalCnt)) {
            System.out.println("범위를 벗어났습니다. 다시 입력하세요.\n");
            return numTurnIn(totalCnt);
        }
        return num;
    }

    private void turnInService(int cnt) {
        boolean chk = false;
        String item, str;

        for(int i = 0; i < cnt; i++){
            if(cnt == 1)
                System.out.print("반납할 책 도서번호 : ");
            else
                System.out.print((i+1) + "번째로 반납할 책 도서번호 : ");

            bSeq = Integer.parseInt(sc.nextLine());
            chk = seqCheck(bSeq); // list에 도서번호(key) 유무 확인
//            if(!chk){
//                turnIn();
//                return;
//            }

            item = Book.rentList.get(bSeq);
            System.out.print("\n" + item + " 을(를) 삭제하시겠습니까?(y/n) ");

            str = sc.nextLine();
            if(str.equals("y") || str.equals("Y")){
                Book.delete(bSeq);
                fm.writeUserInfo(printResult());
            } else {
                System.out.println("삭제를 취소합니다.");
            }
        }
    }

    private Boolean seqCheck(int seq){
        boolean b = false;

        if(Book.rentList.containsKey(seq)){
            b = true;
        } else{
            System.out.println("다시 입력하세요.");
        }
        return b;
    }

    private String printResult() {
        StringBuilder str = new StringBuilder("\n");
        str.append("-----< 대여 list >-----\n");
        str.append("도서번호\tAuthor\tTitle\n");
        str.append("----------------------\n");

        for (Map.Entry<Integer, String> entry : Book.rentList.entrySet()) {
            str.append(entry.getKey()).append("\t\t").append(entry.getValue()).append("\n");
        }
        System.out.println(str);
        return str.toString();
    }

    private void moreRent(){
        System.out.print("더 대여하시겠습니까?(y/n) : ");
        String str = sc.nextLine();

        if(str.equals("y") || str.equals("Y")) {
            rent();
        } else if(str.equals("n") || str.equals("N")) {
            restart();
        } else {
            System.out.println("\n다시 입력해주세요.");
            this.moreRent();
        }
    }

    public void moreTurnIn(int totalCnt){
        System.out.print("더 반납하시겠습니까? (y/n) : ");
        String str = sc.nextLine();

        if(str.equals("y") || str.equals("Y")) {
            turnIn(totalCnt);
        } else if(str.equals("n") || str.equals("N")) {
            restart();
        } else {
            System.out.println("\n다시 입력해주세요.");
            this.moreTurnIn(totalCnt);
        }
    }

    public static void restart(){
        System.out.println("이전으로 되돌아갑니다.\n");
        LibraryView.start();
    }

}
