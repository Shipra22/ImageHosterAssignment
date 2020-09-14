package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    //This method is called to add the comment to the image
    //here we receive the dynamic parameter in the incoming request URL in a string variable 'imageId',string variable 'imageTitle', comment as the part of the query and also the Model type object
    // fetch the details of User from session Object
    //Call the getImage() method in the business logic to fetch all the details of that image
    // set all the field of the comment object i.e user,text,createdDate,image and user
    //Call the addComment() method in the business logic to persist the comment in database
    //Add the image in the Model type object with 'image' as the key
    //Add the tags in the Model type object with 'tags' as the key
    //Return to 'redirect:/images"+"/"+imageId+"/"+imageTitle'

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments",method = RequestMethod.POST)
    public String addComments(Model model,@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, Comment comment, @RequestParam("comment") String commentText, HttpSession session){
        User user = (User) session.getAttribute("loggeduser");

        comment.setText(commentText);
        comment.setCreatedDate(new Date());
        comment.setUser(user);
        Image image=imageService.getImage(imageId);
        comment.setImage(image);
        commentService.addComment(comment);
        model.addAttribute("image", image);
        model.addAttribute("tags",image.getTags());
        return "redirect:/images"+"/"+imageId+"/"+imageTitle;


    }
}
