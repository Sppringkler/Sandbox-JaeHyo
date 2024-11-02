package com.ssafy.sandbox.controller.request;

import com.ssafy.sandbox.domain.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateArticlesReq {
    private List<Article> list;
}
