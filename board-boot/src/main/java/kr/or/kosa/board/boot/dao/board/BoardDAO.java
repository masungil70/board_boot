package kr.or.kosa.board.boot.dao.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.or.kosa.board.boot.entity.board.BoardVO;

@Mapper
@Repository("boardDAO")
public interface BoardDAO {

	List<BoardVO> listBoard();
	BoardVO findById(Long id);
	public int insertBoard(BoardVO boardVO);
	public int updateBoard(BoardVO boardVO);
	public int updateBoardReadCntInc(Long id);
	public int deleteById(Long id);
	public int deleteBoard(Map<String, Object> map);
	
}

