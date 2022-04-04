package proLibrary.manage;

public class BookDTO {
    private Integer bSeq;
    private String bAuthor;
    private String bTitle;

//    생성자 - 대여
    public BookDTO() {}

    public BookDTO(Integer bSeq, String bAuthor, String bTitle){
        this.bSeq = bSeq;
        this.bAuthor = bAuthor;
        this.bTitle = bTitle;
    }

// ----------------< getters >------------------

    public Integer getBSeq() {
        return bSeq;
    }

    public String getBAuthor() {
        return bAuthor;
    }

    public String getBTitle() {
        return bTitle;
    }

}
