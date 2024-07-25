package hellojpa;

import hellojpa.jpql.*;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.setType(MemberType.ADMIN);
            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

//            String query = "select coalesce(m.username, '이름 없는 회원') as username " +
//                    "from Member m";
            String query = "select nullif(m.username, '관리자') as username " +
                    "from Member m";


            List<String> result = em.createQuery(query, String.class)
                    .getResultList();
            for (String string : result) {
                System.out.println("string = " + string);
            }
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
