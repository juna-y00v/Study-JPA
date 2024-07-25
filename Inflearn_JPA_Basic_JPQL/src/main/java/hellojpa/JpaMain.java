package hellojpa;

import hellojpa.jpql.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

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

//            String query = "select concat('a', 'b') from Member m";

//            String query = "select 'a' || 'b' from Member m";

//            String query = "select substring(m.username,2,3) from Member m";

//            String query = "select locate('de','abcdefg') from Member m";

            String query = "select size(t.members) from Team t ";

            List<Integer> result = em.createQuery(query, Integer.class)
                    .getResultList();

            for (Integer s : result) {
                System.out.println("s = " + s);
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
