package hsf.weixin.popular.bean.card;

import hsf.weixin.popular.bean.card.AbstractCard;
import hsf.weixin.popular.bean.card.Gift;

/**
 * 兑换券
 * 
 * @author Moyq5
 *
 */
public class GiftCard extends AbstractCard {

	private Gift gift;

	public GiftCard() {
		super("GIFT");
	}

	public Gift getGift() {
		return gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

}
