package proLibrary.preview;

import proLibrary.util.LibraryLogin;
import proLibrary.util.LibraryUtil;
import java.util.Scanner;

public class LibraryView {

    public static void start(){
        Scanner sc = new Scanner(System.in);
        LibraryUtil libUtil = new LibraryUtil();

        System.out.print("선택하세요 (1. 대여, 2. 반납, 3. 종료) : ");

        int choice = Integer.parseInt(sc.nextLine());
        libUtil.start(choice);
    }

    public static void login() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Kopo Library!");
        System.out.println("\n--------< Login >--------");

        System.out.print("User ID : ");
        String uID = sc.nextLine();

        System.out.print("Password : ");
        String uPW = sc.nextLine();

        System.out.println("\n도서관리 프로그램을 시작합니다.");
        LibraryLogin libLogin = new LibraryLogin(uID, uPW);
    }
}
