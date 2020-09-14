package ImageHoster.repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository{

    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method receives the Comment object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public Comment addComment(Comment comment){
        EntityManager em= emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        }catch(Exception exception){
            System.out.println(exception);
            transaction.rollback();
        }
        return comment;
    }


}
