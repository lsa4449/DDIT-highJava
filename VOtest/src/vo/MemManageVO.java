package vo;

import java.util.Date;

public class MemManageVO {

	private int memManageNum; // 회원 관리 번호
	private Date overTime; // 연체 기간
	private int overTimeCount; // 연체 횟수
	private boolean memState; // 회원 상태 
	private int memNum; // 회원 번호

	public int getMemManageNum() {
		return memManageNum;
	}

	public void setMemManageNum(int memManageNum) {
		this.memManageNum = memManageNum;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public int getOverTimeCount() {
		return overTimeCount;
	}

	public void setOverTimeCount(int overTimeCount) {
		this.overTimeCount = overTimeCount;
	}

	public boolean isMemState() {
		return memState;
	}

	public void setMemState(boolean memState) {
		this.memState = memState;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	@Override
	public String toString() {
		return "MemManageVO [memManageNum=" + memManageNum + ", overTime=" + overTime + ", overTimeCount="
				+ overTimeCount + ", memState=" + memState + ", memNum=" + memNum + "]";
	}

}
