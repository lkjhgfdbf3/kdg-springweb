package com.kdg.letter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kdg.article.Article;
import com.kdg.letter.Letter;

public class LetterDao {
	/**
	 * 받은 목록
	 */
	static final String RECEIVE_LIST = "select letterId, title, receiverId, receiverName, cdate from letter where reciverId = ?";
	/**
	 * 보낸 목록
	 */
	static final String SEND_LIST = "select letterId, title, senderId, senderName, cdate from letter where reciverId = ?";
	/**
	 * 편지보기
	 */
	static final String GET_LETTER = "select letterId, title, content, senderId, senderName, receiverId, receiverName, cdate from letter where letterId = ? and (senderId  = ? or receiverId = ? )";   
	/**
	 * 편지쓰기 화면
	 */
	static final String ADD_LETTER = "insert letter(title,content,senderId, senderName, receiverId, receiverName) values(?,?,?,?,?,?)";
	/**
	 * 편지저장
	 */
	static final String SAVE_LETTER = "insert letter(title,content,senderId, senderName, receiverId, receiverName) values(?,?,?,?,?,?)";
	/**
	 * 편지삭제
	 */
	static final String DELETE_LETTER = "delete from article where letterId = ? and (senderId  = ? or receiverId = ? )";
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<Letter> letterRowMapper = new BeanPropertyRowMapper<>(
			Letter.class);

	public int receiveList(Letter letter) {
		return jdbcTemplate.update(RECEIVE_LIST,letter.getLetterId());
		}
	
	public int sendList(Letter letter) {
		return jdbcTemplate.update(SEND_LIST,letter.getLetterId());
		}
	
	public Letter getLetter(String letterId,String senderId,String receiverId) {
		return jdbcTemplate.queryForObject(GET_LETTER, letterRowMapper,letterId, senderId,receiverId);
	}
	
	public int addLetter(Letter letter) {
		return jdbcTemplate.update(ADD_LETTER, letter.getTitle(),letter.getContent(),letter.getSenderId(),letter.getSenderName(),letter.getReceiverId(),letter.getReceiverName());
	}
	
	
}