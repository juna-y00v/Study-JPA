package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Order;
import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Order order = em.find(Order.class, 1L);
//            Long memberId = order.getMemberId();
//            Member member = em.find(Member.class,memberId); 관계형 디비에 맞춰서 설계

            Member member = order.getMember(); // 객체에 맞춰서 설계
            tx.commit();
            ;

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
