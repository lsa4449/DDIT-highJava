package kr.or.ddit.mp.view.join;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JoinRegulation {
	private boolean result = false;
	
	/**
	 * 아이디 정규화
	 * @param mem_id
	 * @return
	 */
	public boolean regExMemId(String mem_id) {
		Pattern p = Pattern.compile("\\w*@[a-z]*\\.[a-z]{3}");
		Matcher m = p.matcher(mem_id);
		result = m.matches();
		return result;
	}
	
	
	/**
	 * 비밀번호 정규화
	 * @param mem_pw
	 * @return
	 */
	
	public boolean regExMemPw(String mem_pw) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9]{5,16}$");
		Matcher m = p.matcher(mem_pw);
		result = m.matches();
		return result;
	}
	
	
	/**
	 * 이름 정규화
	 * @param mem_name
	 * @return
	 */
	public boolean regExMemName(String mem_name) {
		Pattern p = Pattern.compile("^[가-힣]{2,5}$");
		Matcher m = p.matcher(mem_name);
		result = m.matches();
		return result;
	}
	
	
	/**
	 * 생년월일 정규화
	 * @param mem_birth
	 * @return
	 */
	public boolean regExBirth(String mem_birth) {
		Pattern p = Pattern.compile("^[0-9]{6}");
		Matcher m = p.matcher(mem_birth);
		result = m.matches();
		return result;
	}
	
	/**
	 * 폰 번호 정규화
	 * @param mem_HP
	 * @return
	 */
	public boolean regExPhone(String mem_hp) {
		Pattern p = Pattern.compile("[0-9]{3}-[0-9]{3,4}-[0-9]{4}");
		Matcher m = p.matcher(mem_hp);
		result = m.matches();
		return result;
	}
	
	
	/**
	 * 닉네임 정규화
	 * @param mem_nick
	 * @return
	 */
	public boolean regExNick(String mem_nick) {
		Pattern p = Pattern.compile("^[가-힣]{2,5}$");
		Matcher m = p.matcher(mem_nick);
		result = m.matches();
		return result;
	}
}
