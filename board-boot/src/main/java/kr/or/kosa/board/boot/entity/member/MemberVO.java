package kr.or.kosa.board.boot.entity.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberVO implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String pwd;
	private LocalDateTime lastLoginTime;
	
    @Builder
	public MemberVO(Long id, String email, String pwd, LocalDateTime lastLoginTime) {
		super();
		this.id = id;
		this.email = email;
		this.pwd = pwd;
		this.lastLoginTime = lastLoginTime;
	}
	
	@Override
	public String getPassword() {
		return this.getPwd();
	}
	
	@Override
	public String getUsername() {
		return this.getEmail();
	}
	
	 //계정이 갖고있는 권한 목록은 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> {
            return "계정별 등록할 권한";
        });
        
        //collectors.add(new SimpleGrantedAuthority("Role"));
        
        return collectors;
    }
    
	//계정이 만료되지 않았는지 리턴 (true: 만료 안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있는지 않았는지 리턴. (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되지 않았는지 리턴한다. (true: 만료 안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화(사용가능)인지 리턴 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
