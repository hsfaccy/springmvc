package hsf.weixin.popular.bean.wxopen;

import java.util.List;

import hsf.weixin.popular.bean.BaseResult;

public class TemplateListResult extends BaseResult {

	private List<TemplateListItem> list;

	public List<TemplateListItem> getList() {
		return list;
	}

	public void setList(List<TemplateListItem> list) {
		this.list = list;
	}

}
