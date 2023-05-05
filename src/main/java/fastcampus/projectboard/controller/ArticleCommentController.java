package fastcampus.projectboard.controller;

import fastcampus.projectboard.domain.request.ArticleCommentRequest;
import fastcampus.projectboard.dto.UserAccountDto;
import fastcampus.projectboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest){
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
                "que", "asdf1234", "que@mail.com", "que", "memo"
        )));
        return "redirect:/articles/"+articleCommentRequest.articleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticle(@PathVariable Long commentId, Long articleId) {
        articleCommentService.deleteArticleComment(commentId);
        return "redirect:/articles/"+articleId;
    }


}
