package com.wej.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wej.exam.vo.Article;

@Controller
public class UsrArticleController {	

	private int articlesLastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		articlesLastId = 0;
		articles = new ArrayList<>();
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		int id = articlesLastId + 1;
		Article article = new Article(id, title, body);
		
		articles.add(article);
		articlesLastId = id;
		
		return article;
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles(){
		return articles;
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id){
		
		Article article = getArticle(id);
		
		if(article == null) {
			return id + "번 글이 존재하지 않습니다.";
		}
			
		articles.remove(article);
		
		return "id : " + id + " 글이 삭제되었습니다.";
	
			
	}
	
	@RequestMapping("/usr/article/domodify")
	@ResponseBody
	public String domodify(int id, String title, String body){
		
		Article article = getArticle(id);
		article.setTitle(title);
		article.setBody(body);
		
		articles.set(id, article);
		return "수정완료";
	}
	
	private Article getArticle(int id) {
			
		for(Article article : articles){
			
			if(article.getId() == id) {
				return article;
			}
		}
		return null;				
	}

}



