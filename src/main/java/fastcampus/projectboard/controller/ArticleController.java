package fastcampus.projectboard.controller;

import fastcampus.projectboard.domain.type.SearchType;
import fastcampus.projectboard.response.ArticleResponse;
import fastcampus.projectboard.response.ArticleWithCommentsResponse;
import fastcampus.projectboard.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public String articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ){
        model.addAttribute(
                "articles",
                articleService.searchArticles(searchType,searchValue,pageable)
                .map(ArticleResponse::from)
        );
        return "articles/index";
    }
    @GetMapping("/{articleId}")
    public String detail(
            @PathVariable Long articleId,
            Model model
    ){
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticle(articleId));
        model.addAttribute("article", article);
        model.addAttribute("articleComments", article.articleCommentsResponses());
        return "articles/detail";
    }
}
