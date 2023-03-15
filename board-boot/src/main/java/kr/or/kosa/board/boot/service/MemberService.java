package kr.or.kosa.board.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.kosa.board.boot.dao.member.MemberDAO;
import kr.or.kosa.board.boot.entity.member.MemberVO;

@Service
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberDAO memberDAO;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
		MemberVO member = memberDAO.findByEmail(email);
		
		if (member == null) throw new UsernameNotFoundException("Not Found account."); 
		
		return member;
	}
}