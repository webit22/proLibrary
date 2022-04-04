package proLibrary.utility;

import proLibrary.manage.Book;
import proLibrary.manage.FileManager;
import proLibrary.preview.LibraryView;

import java.util.*;
import java.lang.System;

public class LibraryUtil {
    FileManager fm = new FileManager();
//    Book bookRecord = new Book();
    Scanner sc = new Scanner(System.in);

    private final String exit;
    private Integer bSeq; // 도서번호
    private int cnt;
    private static int totalCnt;

    public LibraryUtil(){
        exit = "\n도서 관리 프로그램을 종료합니다.";
        bSeq = 0;
        cnt = 0; totalCnt = 0;
    }

    public void start(int choice){

        switch (choice) {
            case 1 -> rent();
            case 2 -> {
                Book.checkEmpty();
                turnIn();
            }
            case 3 -> System.out.println(exit); // 안되면 return으로 바꾸기!
            default -> restart();
        }
    }

//  대여 서비스

    public void rent(){

        System.out.println("\n------< 도서 목록 >------");
        fm.readBookInfo();

        System.out.print("대여할 책 개수 : ");
        cnt = Integer.parseInt(sc.nextLine());
        totalCnt += cnt;

        for(int i = 0; i < cnt; i++) {
            if(cnt == 1)
                System.out.print("대여할 책 도서번호 : ");
            else
                System.out.print((i+1) + "번째로 대여할 책 도서번호 : ");

            bSeq = Integer.parseInt(sc.nextLine());
            Book.add(bSeq); // list에 키가 없는 경우 추가 (아직은 list 중복 가능성 있음)
        }
        fm.writeUserInfo(printResult());
        moreRent();
    }

    //  반납 서비스
    public void turnIn(){
        printResult();

        System.out.print("반납할 책 개수 : ");
        cnt = Integer.parseInt(sc.nextLine());
        totalCnt += cnt;

        for(int i = 0; i < cnt; i++){

            if(cnt == 1)
                System.out.print("반납할 책 도서번호 : ");
            else
                System.out.print((i+1) + "번째로 반납할 책 도서번호 : ");

            bSeq = Integer.parseInt(sc.nextLine());

//          만약 키가 있다면 list에서 제거
            System.out.print("\n" + Book.rentList.get(bSeq) + "을(를) 삭제하시겠습니까?(y/n) ");
            String str = sc.nextLine();

            if(str.equals("y") || str.equals("Y")){
                Book.delete(bSeq);
            } else {
                System.out.println("삭제를 취소하고 되돌아갑니다.");
                LibraryView.start();
            }
        }

        fm.writeUserInfo(printResult());
        moreTurnIn();
    }

    private String printResult() {
        System.out.println("총 대여한 도서는 " + totalCnt + "권 입니다.\n");
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
        System.out.print("더 대여하시겠습니까? (y/n) : ");
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

    public void moreTurnIn(){
        System.out.print("더 반납하시겠습니까? (y/n) : ");
        String str = sc.nextLine();

        if(str.equals("y") || str.equals("Y")) {
            turnIn();
        } else if(str.equals("n") || str.equals("N")) {
            restart();
        } else {
            System.out.println("\n다시 입력해주세요.");
            this.moreTurnIn();
        }
    }

    public static void restart(){
        totalCnt = 0;
        System.out.println("restart..");
//        System.out.println("이전으로 되돌아갑니다.\n");
        LibraryView.start();
    }

}
