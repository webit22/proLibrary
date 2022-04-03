package proLibrary.manage;

import java.io.*;

// file read, write (crud)
public class FileManager{
    private String uID;
    private String uPW;
    private String filePath;

    public FileManager(){
        this.uID = UserDTO.getUID();
        this.uPW = UserDTO.getUPW();
        filePath = null;
    }


// ------------------------------------------------------------
//    Read()
// ------------------------------------------------------------

//    user 계정 정보 가져오기 (read only)
    public void readUserInfo() {
        filePath = "libFiles/info_" + this.uID + ".txt";
        read(filePath);
    }

    //    도서관 도서 목록 (read only)
    public void readBookInfo(){
        filePath = "libFiles/bookList.txt";
        read(filePath);
    }

    public void read(String path){
        FileInputStream fis = null; // 파일 스트림
        try {
            fis = new FileInputStream(path);// 파일 스트림 생성
            byte[ ] readBuffer = new byte[fis.available()];

            while (fis.read(readBuffer) != -1){ // 더이상 읽어올게 없으면 -1 반환
//                if(path.equals("libFiles/bookList.txt"))
                    System.out.println(new String(readBuffer)); //출력
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileClose.close(fis);
        }
    }

// ------------------------------------------------------------
//    Write()
// ------------------------------------------------------------

    public void writeUserInfo(){
        filePath = "libFiles/info_" + this.uID + ".txt";
        String str = "N/A";
        write(filePath, str);
    }

//    @Override
//    save as "seq;author;title\n"
    public void writeUserInfo(String str){
        filePath = "libFiles/info_" + this.uID + ".txt";
        write(filePath, str);
    }

    public void writeUserInfo(String bSerialNum, String bAuthor, String bTitle) {
        filePath = "libFiles/info_" + this.uID + ".txt";

        String str = "도서 번호 : " + bSerialNum + "\t";
        str += "도서 저자 : " + bAuthor + "\t";
        str += "도서 제목 : " + bTitle + "\n";

        write(filePath, str);
    }

    public void write(String path, String str){
        BufferedOutputStream bs = null;

        try {
            bs = new BufferedOutputStream(new FileOutputStream(path));
            bs.write(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileClose.close(bs);
        }
    }

}