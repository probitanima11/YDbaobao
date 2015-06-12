package com.ydbaobao.apicontroller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.support.JSONResponseUtil;
import com.ydbaobao.dao.GradeDao;

@Controller
@RequestMapping("/api/grades")
public class ApiGradeController {
	@Resource
	private GradeDao gradeDao;
	
	@RequestMapping()
	public ResponseEntity<Object> show() {
		return JSONResponseUtil.getJSONResponse(gradeDao.readGrades(), HttpStatus.OK);
	}

	@RequestMapping(value="/{gradeId}/{discount}", method=RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable int gradeId, @PathVariable int discount) {
		//TODO 입력값이 integer인지 판별 로직 추가.
		gradeDao.updateGrade(gradeId, discount);
		return JSONResponseUtil.getJSONResponse("", HttpStatus.OK);
	}
}
