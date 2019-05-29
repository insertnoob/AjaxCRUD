package cafe.jjdev.ajaxcrud.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cafe.jjdev.ajaxcrud.member.service.MemberService;
import cafe.jjdev.ajaxcrud.member.vo.Member;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	// 목록
	@GetMapping("/getMembers")
	public Map<String,Object> getMembers(@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage){
		System.out.println("/getMembers 요청");
		Map<String,Object> resultMap = memberService.getMembers(currentPage);
		System.out.println("MemberController.getMembers GET resultMap : "+resultMap);
		return resultMap;
	}
	// 추가
	@PostMapping("/addMember")
	public void addMember(Member member){
		System.out.println("/addMember 요청");
		System.out.println("MemberController.addMember GET member : "+member);
		memberService.addMember(member);
	}
	// 수정
	@PostMapping("/modifyMember")
	public void modifyMember(Member member){
		System.out.println("/modifyMember 요청");
		System.out.println("MemberController.modifyMember GET member : "+member);
		memberService.modifyMember(member);
	}
	// 삭제
	@PostMapping("/removeMember")
	public void removeMember(@RequestParam(value = "ck[]") String[] ck){ // List<String> ck
		System.out.println("/removeMember 요청");
		System.out.println("MemberController.removeMember GET ck : "+ck);
		memberService.removeMember(ck);
	}
}
