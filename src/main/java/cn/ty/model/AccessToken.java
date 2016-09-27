package cn.ty.model;

/**
 * 微信通用接口凭证 
 * @author tangyi
 */
public class AccessToken {

	//获取到的凭证
	private String acessToken;
	
	//凭证有效时间：单位 S
	private int expiresIn;

	public String getAcessToken() {
		return acessToken;
	}

	public void setAcessToken(String acessToken) {
		this.acessToken = acessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
