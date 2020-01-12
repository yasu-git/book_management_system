package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name ="books")

@NamedQueries({
    @NamedQuery(
            name = "getAllBooks",
            query = "SELECT b FROM  Book AS b ORDER BY b.id "
            ),
    @NamedQuery(
            name = "getBooksCount",
            query = "SELECT COUNT(b) FROM Book AS b"
            ),
    @NamedQuery(
            name = "getMyAllBooks",
            query = "SELECT b FROM Book AS b WHERE b.user = :user ORDER BY b.id DESC"
            ),
    @NamedQuery(
            name = "getMyBooksCount",
            query = "SELECT COUNT(b) FROM Book AS b WHERE b.user = :user"
            ),
    @NamedQuery(
            name = "getMyBooksDuplicationCount",
            query = "SELECT COUNT(b) FROM Book AS b WHERE b.user = :user AND b.isbn = :isbn"
            ),
    @NamedQuery(
            name = "getMyBooksSearch",
            query = "SELECT b FROM Book AS b WHERE b.user = :user AND ( b.author1 like :au1 OR b.author2 like :au2 OR b.author3 like :au3 OR b.publisher like :pu OR b.title like :tit ) ORDER BY b.id DESC "
            ),
    @NamedQuery(
            name = "getMyBooksSearchCount",
            query = "SELECT COUNT(b) FROM  Book AS b WHERE b.user = :user AND ( b.author1 like :au1 OR b.author2 like :au2 OR b.author3 like :au3 OR b.publisher like :pu OR b.title like :tit )"
            ),
    @NamedQuery(
            name = "getUserBooksId",
            query = "SELECT b.id FROM  Book AS b WHERE b.user = :user"
            ),


})
@Entity
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false)
    private  User user;

  //本の題名
    @Column(name = "title", length = 255 , nullable = false)
    private String title;

    //作者
    @Column(name = "author1", length = 30 , nullable = true)
    private String author1;

  //作者
    @Column(name = "author2", length = 30, nullable = true)
    private String author2 ;

  //作者
    @Column(name = "author3", length = 30 , nullable = true)
    private String author3 ;

    //出版日
    @Column(name = "publishedDate", nullable = true)
    private Date publishedDate;

    //出版社
    @Column(name = "publisher", length = 255 , nullable = true)
    private String publisher;

    //定価
    @Column(name = "listPrice", nullable = true)
    private Integer listPrice;

    //説明文
    @Lob
    @Column(name = "description", nullable = true)
    private String description;

    //ページ数
    @Column(name = "pageCount", nullable = false)
    private Integer pageCount;

    //smallサムネイル
    @Column(name = "smallThumbnail", nullable = false)
    private String smallThumbnail;

    //サムネイル
    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    //評価
    @Column(name = "evaluate", nullable = false)
    private Integer evaluate;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "isbn",nullable = true)
    private Long isbn;

    @Column(name = "infoLink", nullable = false)
    private String infoLink;

    @Column(name = "infoLinkImg", nullable = false)
    private String infoLinkImg;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

  //本の題名
    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //作者
    public String getAuthor1(){
        return author1;
    }

    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    public String getAuthor2(){
        return author2;
    }

    public void setAuthor2(String author2) {
        this.author2 = author2;
    }

    public String getAuthor3(){
        return author3;
    }

    public void setAuthor3(String author3) {
        this.author3 = author3;
    }

    //出版日
    public Date getPublishedDate(){
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    //出版社
    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    //定価
    public Integer getListPrice(){
        return listPrice;
    }

    public void setListPrice(Integer listPrice) {
        this.listPrice = listPrice;
    }

    //説明文
    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //ページ数
    public Integer getPageCount(){
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    //smallサムネイル
    public String getSmallThumbnail(){
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail(){
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getEvaluate(){
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getInfoLink(){
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }
    public String getInfoLinkImg(){
        return infoLinkImg;
    }

    public void setInfoLinkImg(String infoLinkImg) {
        this.infoLinkImg = infoLinkImg;
    }


}
