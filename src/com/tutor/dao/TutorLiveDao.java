package com.tutor.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.tutor.db.DBHelper;

public class TutorLiveDao {
	
	public List<String> getRTMPkeyList() throws Exception{
		List<String> list = new ArrayList<String>();
		String sql="select rtmpkey from tutor_live where permission = 1";
		Connection conn=new DBHelper().getConn();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		while(rs.next()){
			list.add(rs.getString("rtmpkey"));
		}
		return list;
	}
}
