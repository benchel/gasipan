package gasipan.confing;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import gasipan.constant.GasipanConstructer;
import gasipan.vo.AdminVO;
import gasipan.vo.UserVO;

public class SessionManager {

	public SessionManager() {
		// TODO Auto-generated constructor stub
	}
	
	public static HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true);		
	}
	
	public static void setSeesionInfo(HttpSession session, UserVO vo) throws Exception {
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
	
	public static void setSeesionInfoByAdmin(HttpSession session, AdminVO vo) throws Exception {
		if (null == vo) {
			throw new Exception("Not defined login ID.");
		}		
		session.setAttribute(GasipanConstructer.SESSION_ADMIN_ID, vo.getAdminId());
		session.setAttribute(GasipanConstructer.SESSION_ADMIN_NAME, vo.getAdminName());
		session.setAttribute(GasipanConstructer.SESSION_ADMIN_EMAIL, vo.getAdminEmail());
		session.setAttribute(GasipanConstructer.SESSION_ADMIN_AUTHORITY, vo.getAuthority());
	}
	
	public static void removeSeesionInfoByAdmin(HttpSession session) {
		session.removeAttribute(GasipanConstructer.SESSION_ADMIN_ID);
		session.removeAttribute(GasipanConstructer.SESSION_ADMIN_NAME);
		session.removeAttribute(GasipanConstructer.SESSION_ADMIN_EMAIL);
		session.removeAttribute(GasipanConstructer.SESSION_ADMIN_AUTHORITY);
	}
	
}
