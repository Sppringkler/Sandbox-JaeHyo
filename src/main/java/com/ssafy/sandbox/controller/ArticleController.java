package com.ssafy.sandbox.controller;

import com.ssafy.sandbox.controller.request.MakeArticlesReq;
import com.ssafy.sandbox.controller.response.ReadArticlesWithPagingCursorResp;
import com.ssafy.sandbox.controller.response.ReadArticlesWithPagingResp;
import com.ssafy.sandbox.domain.article.dto.response.ReadArticlesWithPagingRespDto;
import com.ssafy.sandbox.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/paging/offset")
    public ResponseEntity<ReadArticlesWithPagingResp> readArticlesWithPagingOffset(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page) {

        Page<ReadArticlesWithPagingRespDto> respDtoPage = articleService.readArticlesWithPagingOffset(size, page);

        return ResponseEntity.ok(
                new ReadArticlesWithPagingResp(respDtoPage)
        );
    }

    @GetMapping("/paging/cursor")
    public ResponseEntity<ReadArticlesWithPagingCursorResp> readArticlesWithPagingCursor(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "cursorId") int cursorId) {

        Page<ReadArticlesWithPagingRespDto> respDtoPage = articleService.readArticlesWithPagingCursor(size, cursorId);

        return ResponseEntity.ok(
                new ReadArticlesWithPagingCursorResp(respDtoPage,cursorId)
        );
    }

    @PostMapping("/make")
    public ResponseEntity<String> saveArticles(@RequestBody MakeArticlesReq request) {
        articleService.makeArticles(request.getArticles());

        return ResponseEntity.ok("success");
    }
}
