package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //영속
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            em.detach(member); // 특정 개체 준영속 상태
            em.clear(); // 영속성 전부 초기화

            Member member2 = em.find(Member.class, 150L);
            
            System.out.println("===============");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
