package kr.or.kosa.board.boot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.kosa.board.boot.dao.board.BoardDAO;
import kr.or.kosa.board.boot.entity.board.BoardVO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	@Transactional
	public boolean save(BoardVO boardVO) throws Exception {
		return boardDAO.insertBoard(boardVO) != 0;
	}
	
	//param : pageNum, rowSize
	@Transactional(readOnly = true)
	public List<BoardVO> listBoard() throws Exception {
		return boardDAO.listBoard();
	}
	
	public BoardVO findById(Long id) throws Exception {
		
		boardDAO.updateBoardReadCntInc(id);
		
		return boardDAO.findById(id);
	}
	
	public boolean updateBoard(BoardVO boardVO) throws Exception {
		
		return boardDAO.updateBoard(boardVO) != 0;
	}
	
	public void deleteById(Long id) throws Exception {
		boardDAO.deleteById(id);
	}
	
	public void deleteAll(Long[] deleteIdList) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("deleteIdList", deleteIdList);
		
		boardDAO.deleteBoard(map);
	}
}
