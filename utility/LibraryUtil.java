package proLibrary.utility;

import proLibrary.manage.BookDTO;
import proLibrary.manage.FileManager;
import proLibrary.preview.LibraryView;

import java.util.Scanner;
import java.lang.System;

public class LibraryUtil {
    Scanner sc = new Scanner(System.in);
    private String exit = "\n도서 관리 프로그램을 종료합니다.";
    int cnt = 0, totalCnt = 0;

    public LibraryUtil(){
    };

    public void start(int choice){
        switch (choice) {
            case 1 :
                rent(); break;
            case 2 :
                turnIn(); break;
            case 3 :
                System.out.println(exit);
                break;
            default:
                System.out.println("\n1 ~ 3번 중 다시 입력해주십시오.");
                LibraryView.start();
                break;
        }
    }

//  대여 서비스
    public void rent(){
        FileManager fm = new FileManager();
        fm.readUserInfo();

        System.out.println("\n------< 도서 목록 >------");
        fm.readBookInfo();

        System.out.print("대여할 책 개수 : ");
        cnt = Integer.parseInt(sc.nextLine());
        totalCnt += cnt;

        System.out.print("대여할 책 번호 (도서번호) : ");
        Integer seq = Integer.parseInt(sc.nextLine());
        BookDTO bDTO = new BookDTO(seq.toString());

        if(bDTO.getMapList().containsKey(seq)){
            fm.writeUserInfo(bDTO.getBSerialNum(), bDTO.getBAuthor(), bDTO.getBAuthor());
        }

        System.out.println("\n총 대여한 도서는 " + totalCnt + "권 입니다.");
        fm.readUserInfo();

        moreRent();
    }

//  반납 서비스
    public void turnIn(){
        System.out.println("\n반납");
    }

    private void moreRent(){
        System.out.print("더 대여하시겠습니까? (y/n) : ");
        String str = sc.nextLine();

        if(str.equals("y") || str.equals("Y")) {
            rent();
        } else if(str.equals("n") || str.equals("N")) {
            System.out.println(exit);
            System.exit(0);
        } else {
            System.out.println("\n다시 입력해주세요.");
            this.moreRent();
        }
    }

//    public void moreTurnIn(){}


}
