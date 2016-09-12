package cn.bigzhu.entity;

import java.util.List;


public class NewsMessage extends BaseMessage{
	private int ArticleCount;
	private List<Item> Articles;
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Item> getArticles() {
		return Articles;
	}
	public void setArticles(List<Item> articles) {
		Articles = articles;
	}
}
