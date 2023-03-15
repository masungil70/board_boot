package kr.or.kosa.board.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.kosa.board.boot.entity.board.BoardVO;
import kr.or.kosa.board.boot.entity.member.MemberVO;
import kr.or.kosa.board.boot.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/list.do")
	public String list(Model model) throws Exception {
		
		try {
			model.addAttribute("list", boardService.listBoard());
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
		return "/board/list";
	}
	
	@GetMapping("/board/writeForm.do")
	public String writeForm(Model model) {
		return "/board/writeForm";
	}
	
	@GetMapping("/board/view.do")
	public String view(Model model, @RequestParam() Long id) throws Exception {
		
		System.out.println("id->" + id);
		try {
			if (id != null) {
				model.addAttribute("boardVO", boardService.findById(id));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
		return "/board/view";
	}
	
	@PostMapping("/board/update.do")
	public String update(Model model, BoardVO boardVO) throws Exception {
		
		try {
			System.out.println(boardVO);
			if (!boardService.updateBoard(boardVO)) {
				throw new Exception("#Exception boardUpdateAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
		return "redirect:/board/list.do";
	}
	
	
	@PostMapping("/board/write.do")
	public String write(Model model, BoardVO boardVO, Authentication authentication) throws Exception {
		
		try {
			//spring boot security로 로그인 객체를 얻는다 
			MemberVO memberVO = (MemberVO) authentication.getPrincipal();
			boardVO.setRegister_id(memberVO.getEmail());
			
			if (!boardService.save(boardVO)) {
				throw new Exception("#Exception boardWriteAction!");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
		return "redirect:/board/list.do";
	}
	
	@PostMapping("/board/delete.do")
	public String delete(Model model, @RequestParam() Long id) throws Exception {
		
		try {
			if (id != null) {
				boardService.deleteById(id);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
		return "redirect:/board/list.do";
	}
	
	@PostMapping("/board/deletes.do")
	public String boardDeleteAction(Model model, @RequestParam() Long[] deleteId) throws Exception {
		
		try {
			boardService.deleteAll(deleteId);
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
		return "redirect:/board/list.do";
	}
}
