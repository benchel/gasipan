package gasipan.confing;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import gasipan.bean.GasipanConstructer;
import gasipan.vo.UsersVo;

public class SessionManager {

	public SessionManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true);		
	}
	
	public static void setSeesionInfo(HttpSession session, UsersVo vo) throws Exception {
		if (null == vo) {
			throw new Exception("Not defined login ID.");
		}		
		session.setAttribute(GasipanConstructer.SESSION_USER_ID, vo.getUserId());
		session.setAttribute(GasipanConstructer.SESSION_USER_NAME, vo.getUserNum());
		session.setAttribute(GasipanConstructer.SESSION_USER_EMAIL, vo.getUserEmail());
		session.setAttribute(GasipanConstructer.SESSION_USER_AUTHORITY, vo.getAuthority());
	}
	
	public static void removeSeesionInfo(HttpSession session) {
		session.removeAttribute(GasipanConstructer.SESSION_USER_ID);
		session.removeAttribute(GasipanConstructer.SESSION_USER_NAME);
		session.removeAttribute(GasipanConstructer.SESSION_USER_EMAIL);
		session.removeAttribute(GasipanConstructer.SESSION_USER_AUTHORITY);
	}
}
