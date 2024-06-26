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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String job;
    private String phone;
    private String email;
    private String other;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<BookBorrow> bookBorrowList;
    public User() {

    }
}
