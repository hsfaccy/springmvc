package hsf.weixin.popular.bean.wxa;

import java.awt.image.BufferedImage;

import hsf.weixin.popular.bean.BaseResult;

public class GetQrcodeResult extends BaseResult{

	private BufferedImage bufferedImage;

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	
}
