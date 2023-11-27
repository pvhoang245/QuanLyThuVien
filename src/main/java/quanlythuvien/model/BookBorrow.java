package quanlythuvien.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@Embeddable
@Entity
@AllArgsConstructor
@Table(name = "book_borrow")
public class BookBorrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dateBorrow;
    private Date dateReturn;
    private String status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    public BookBorrow() {

    }
}