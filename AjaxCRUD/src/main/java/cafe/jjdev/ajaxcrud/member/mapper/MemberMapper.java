package cafe.jjdev.ajaxcrud.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.ajaxcrud.member.vo.Member;

@Mapper
public interface MemberMapper {
	// 멤버 총 갯수
	public int selectListTotalCount();
	// 목록
	public List<Member> selectMemberList(Map<String, Object> map);
	// 추가
	public int insertMember(Member member);
	// 수정
	public int updateMember(Member member);
	// 삭제
	public int deleteMember(Member member);
}
