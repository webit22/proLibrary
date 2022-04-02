package proLibrary.utility;

public class LibraryUtil {

    public LibraryUtil(){};

    public void start(int choice){

        if(choice == 1)
            Rent();
        else if(choice == 2)
            TurnIn();
        else if(choice == 3)
            System.out.println("프로그램을 종료합니다.");
        else
            System.out.println("1 ~ 3번 중 다시 입력해주십시오.");
    }

//  대여 서비스
    public void Rent(){
        System.out.println("대여");
    }

//  반납 서비스
    public void TurnIn(){
        System.out.println("반납");
    }
}
