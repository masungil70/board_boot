package kr.or.kosa.board.boot.entity.board;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardVO  {
	
	private Long 	id;
	private String 	title;
	private String 	content;
	private int 	read_cnt;
	private String 	register_id;
	private String 	register_time;
	private String 	update_time;
	
}
