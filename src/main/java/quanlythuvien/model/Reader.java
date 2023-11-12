package quanlythuvien.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
@Entity
@AllArgsConstructor
@Table(name = "reader")
public class Reader {
    @Id
    private String id;
    private String name;
    private String phone;
    private String email;
    private String address;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "reader")
    private List<BookBorrow> bookBorrowList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "reader")
    private List<BookReturn> bookReturnList;

    public Reader() {

    }
}