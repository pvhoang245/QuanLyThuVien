package quanlythuvien.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Embeddable
@Entity
@AllArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    private String id;
    private String name;
    private String author;
    private int totalNumber;
    private int borrowNumber;
    private String location;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "book")
    private List<BookBorrow> bookBorrowList;

    public Book() {

    }
}
