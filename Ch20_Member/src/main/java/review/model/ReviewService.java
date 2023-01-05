package review.model;

import java.util.ArrayList;
import java.util.HashMap;

public interface ReviewService {
	//리뷰쓰기
	void writeReview(ReviewVO vo);
	//리뷰수정
	HashMap<String, Object> updateReview(ReviewVO vo);
	//리뷰삭제
	HashMap<String, Object> DeleteReview(ReviewVO vo);
	//리뷰보기
	ArrayList<ReviewVO> ReviewList(ReviewVO vo);
}
