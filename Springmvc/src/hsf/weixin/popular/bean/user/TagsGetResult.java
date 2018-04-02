package hsf.weixin.popular.bean.user;

import java.util.List;

import hsf.weixin.popular.bean.BaseResult;

/**
 * 标签
 * 
 * @author LiYi
 * 
 */
public class TagsGetResult extends BaseResult {

	private List<Tag> tags;

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
}
