package quanlythuvien.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Embeddable
@Entity
@AllArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String roll;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "account")
    private User user;
    public Account() {

    }
}
