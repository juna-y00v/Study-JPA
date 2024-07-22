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
        /*
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            em.persist(member);
            Member findMember = em.find(Member.class, 101);
        */ //이런 경우 1차 캐시에서 조회

//            Member findMember1 = em.find(Member.class, 101); // 다바에서 가져온 값을 영속성에 올려둠
//            Member findMember2 = em.find(Member.class, 101); // 영속성 안에 있는 1차 캐시에서 조회
//            System.out.println("result = " + (findMember1 == findMember2)); //true 동일성 보장

            /*
            //영속 //persist 할때 쿼리가 날라가는게 아니고 commit 할때 한꺼번에 쿼리를 날린다.
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);

            System.out.println("================");
            */

            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZ");
            //em.persist(member); //변경 감지로 인해서 필요 X

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
