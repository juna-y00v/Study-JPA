package hellojpa;

import hellojpa.jpql.Member;
import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            TypedQuery<Member> query1= em.createQuery("select m from Member m", Member.class);
            //타입 정보 명확
            TypedQuery<String> query2= em.createQuery("select m.username from Member m", String.class);
            //타입 정보 명확 TypedQuery 사용가능
            Query query3= em.createQuery("select m.username, m.age from Member m");
            //타입 정복 불명확하기에 Query 사용
            
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
