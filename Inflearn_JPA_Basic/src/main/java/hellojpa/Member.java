package hellojpa;

import jakarta.persistence.*;


@Entity
@TableGenerator(name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
//@SequenceGenerator(name = "member_sqe_generator",sequenceName = "member_seq") 시퀀스 전략
public class Member {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "member_sqe_generator")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
