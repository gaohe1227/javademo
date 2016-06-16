package 模式.责任链;

public abstract class PriceHandler {
	/**
	 * 直接后继，用于传递请求
	 */
	protected PriceHandler successor;

	public PriceHandler getSuccessor() {
		return successor;
	}

	public void setSuccessor(PriceHandler successor) {
		this.successor = successor;
	}
    /**
     * 处理折扣申请
     * @param discount
     */
	public abstract void processCount(float discount);
}
