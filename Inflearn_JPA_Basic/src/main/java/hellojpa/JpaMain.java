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
           /* Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member);*/ // 등록

            Member findMember = em.find(Member.class, 1L); //조회
            List<Member> findMembers = em.createQuery("select m from Member as m ", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList(); // JPQL

            for (Member member : findMembers) {
                System.out.println("member.getName() = " + member.getName());
            }
//            findMember.setName("HelloJPA"); // 수정
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
