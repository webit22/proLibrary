package proLibrary.utility;

import proLibrary.manage.BookDTO;
import proLibrary.manage.FileManager;
import proLibrary.preview.LibraryView;

import java.util.*;
import java.lang.System;

public class LibraryUtil {
    Scanner sc = new Scanner(System.in);
    FileManager fm = new FileManager();
    List<BookDTO> bookDTOList = new ArrayList<>(); // 도서관의 전체 도서 목록
    Map<Integer, String> rentList = new HashMap<>(); // 사용자가 대여한 도서 정보

//    BookDTO class에 접근할 수 있는 객체 생성
    private Integer bSeq = 0; // 도서번호
    private String exit = "\n도서 관리 프로그램을 종료합니다.";
    private int cnt = 0, totalCnt = 0;

    public LibraryUtil(){
        for(int i = 0; i < 4; i++){
            bookDTOList.add(new BookDTO(i,"Author"+i,"Title"+i));
        }
    };

    public void start(int choice){

        switch (choice) {
            case 1 :
                rent(); break;
            case 2 :
                if(rentList.isEmpty()) {
                    System.out.println("대여하신 책이 없습니다.");
                    restart();
                }
                turnIn();
                break;
            case 3 :
                System.out.println(exit);
                break;
            default:
                restart();
                break;
        }
    }

//  대여 서비스

    public void rent(){
        String temp;

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

//          중복체크 : 만약 키가 있다면 다시 도서번호 입력 받기
            if(rentList.containsKey(bSeq)){
                totalCnt--;
                continue;
            }

//          만약 키가 없다면 add to rent list
            BookDTO bDTO = bookDTOList.get(bSeq);
            temp = bDTO.getBAuthor() + "\t" + bDTO.getBTitle();
            rentList.put(bSeq, temp);

//            책이 있는지 확인 - 책 추가/제거 시 필요
//            if (bDTO.getMapList().containsKey(bDTO.getBSerialNum())) {
//                    // SerialNum;Author;Title 형태로 전달
//                bDTO.addToMap(seq);
//                fm.writeUserInfo(bDTO.sendBookInfo());
//            }
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

//          중복체크 : 만약 키가 있다면 다시 도서번호 입력 받기
//            if(!rentList.containsKey(bSeq)){
//                totalCnt--;
//                continue;
//            }

//          만약 키가 있다면 list에서 제거
            System.out.print("\n" + rentList.get(bSeq) + "을(를) 삭제하시겠습니까?(y/n) ");
            String str = sc.nextLine();

            if(str.equals("y") || str.equals("Y")){
                rentList.remove(bSeq);

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
        String str = "\n";
        str += "-----< 대여 list >-----\n";
        str += "도서번호\tAuthor\tTitle\n";
        str += "----------------------\n";

        for (Map.Entry<Integer, String> entry : rentList.entrySet()) {
            str += entry.getKey() + "\t\t" + entry.getValue() + "\n";
        }
        System.out.println(str);
        return str;
    }

    private void moreRent(){
        System.out.print("더 대여하시겠습니까? (y/n) : ");
        String str = sc.nextLine();

        if(str.equals("y") || str.equals("Y")) {
            rent();
        } else if(str.equals("n") || str.equals("N")) {
            totalCnt = 0;
            System.out.println("이전 화면으로 되돌아갑니다.\n");
            LibraryView.start();
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
            totalCnt = 0;
            System.out.println("이전 화면으로 되돌아갑니다.\n");
            LibraryView.start();
        } else {
            System.out.println("\n다시 입력해주세요.");
            this.moreTurnIn();
        }
    }

    private void restart(){
        System.out.println("\n1 ~ 3번 중 다시 입력해주세요.");
        LibraryView.start();
    }

}
