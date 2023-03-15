package kr.or.kosa.board.boot.dao.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.kosa.board.boot.entity.member.MemberVO;

@Mapper
@Repository("memberDAO")
public interface MemberDAO {

	public int updateMemberLastLogin(String email);
	public MemberVO findByEmail(String email);
	
}

