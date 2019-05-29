package cafe.jjdev.ajaxcrud.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.ajaxcrud.member.mapper.MemberMapper;
import cafe.jjdev.ajaxcrud.member.vo.Member;

@Service
@Transactional
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	
	// 목록
	public Map<String,Object> getMembers(int currentPage){
		System.out.println("MemberService.getMembers currentPage : "+currentPage);
		// 페이지당 목록 갯수
		final int ROW_PER_PAGE = 10;
		// 페이지를 몇 번째 멤버부터 보여줄 건지 정하는 변수 LIMIT(a, b)의 a
		int beginRow = (currentPage - 1) * ROW_PER_PAGE;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", ROW_PER_PAGE);
		// memberList 리턴값
		List<Member> list = memberMapper.selectMemberList(map);
		// 멤버 총 갯수 리턴값 
		int totalCount = memberMapper.selectListTotalCount();
		// 마지막 페이지
		int totalPage = totalCount / ROW_PER_PAGE;
		if(totalCount % ROW_PER_PAGE > 0){
			totalPage++;
		}
		// 총 블록 값을 구하는 변수
		int totalBlock = totalPage / ROW_PER_PAGE;
		if(totalPage % ROW_PER_PAGE > 0){
			totalBlock++;
		}
		// 현재 블록 값을 구하는 변수
		int blockOfPage = (currentPage - 1) / ROW_PER_PAGE + 1;
		
		// 한 블록당 첫 페이지 번호를 구하는 변수 
		// currentPage = 0~10 : startPage=1,
		// currentPage = 11~20 : startPage=11
		int startPage = ((currentPage - 1) / ROW_PER_PAGE) * ROW_PER_PAGE + 1;
		// 한 블록당 마지막 페이지 번호를 구하는 변수
		int endPage = startPage + ROW_PER_PAGE - 1;
		// 마지막 페이지보다 현재 페이지 값이 크다면 현재 페이지는 마지막 페이지가 된다
		if (currentPage > totalPage){
			currentPage = totalPage;
		}
		// 한 블럭의 마지막 페이지가 전체의 마지막 페이지보다 크다면 한 블럭의 마지막 페이지는 전체의 마지막 페이지가 된다
		if (endPage > totalPage) {
		    endPage = totalPage;
		}
		// 최종 리턴 값
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list); // memberList
		resultMap.put("totalPage", totalPage); // 마지막 페이지
		resultMap.put("currentPage", currentPage); // 현재 페이지
		resultMap.put("totalBlock", totalBlock); // 총 블록 값
		resultMap.put("blockOfPage", blockOfPage); // 현재 블록 값
		resultMap.put("startPage", startPage); // 블록의 첫 페이지
		resultMap.put("endPage", endPage); // 블록의 마지막 페이지
		resultMap.put("rowPerPage", ROW_PER_PAGE); // 페이지당 목록 갯수
		return resultMap;
	}
	// 추가
	public void addMember(Member member){
		System.out.println("MemberService.addMember member : "+member);
		memberMapper.insertMember(member);
	}
	// 수정
	public void modifyMember(Member member){
		System.out.println("MemberService.modifyMember member : "+member);
		memberMapper.updateMember(member);
	}
	// 삭제
	public void removeMember(String[] ck){ // List<String> ck
		System.out.println("MemberService.removeMember ck : "+ck);
		for(String id : ck){
			Member member = new Member();
			member.setId(id);
			memberMapper.deleteMember(member);
		}
	}
}
