package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    //The method calls the addComment() method in the Repository and passes the Comment object i.e.comment  to be added in the database
    public Comment addComment(Comment comment){ return commentRepository.addComment(comment);}

    //public List<Comment> retrieveComments(Image image){ return commentRepository.retrieveComments(image);}
}
