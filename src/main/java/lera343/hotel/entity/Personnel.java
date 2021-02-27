package lera343.hotel.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Personnel {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String patronic;
    private String number;
    private String email;
    private String description;
    private Date birthday;
    private Date startedWork;
    private String position;
}
