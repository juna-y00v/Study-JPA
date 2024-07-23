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

            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
//            member.changeTeam(team); //setTeam 메서드안에 team.getMembers().add(this) 넣기 연관관계 편의 메서드
            em.persist(member);

            team.addMember(member); // 연관관계 편의 메서드

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); //1차 캐시
            List<Member> members = findTeam.getMembers();

            System.out.println("==========");
            System.out.println("members = " +findTeam); // 스택오버플로우 발생 toString()을 서로 호출
            System.out.println("==========");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
