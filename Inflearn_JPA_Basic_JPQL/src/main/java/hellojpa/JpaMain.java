package hellojpa;

import hellojpa.jpql.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("관리자1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            em.persist(member2);

            em.flush();
            em.clear();

//            String query = "select m.team From Member m"; // 묵시적 내부 조인 발생
            String query = "select t.members From Team t"; // 컬렉션 값 연관 경로 묵시적 내부 조인 발생
            
            Collection result = em.createQuery(query, Collection.class)
                    .getResultList();

            for (Object o : result) {
                System.out.println("o = " + o);
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
