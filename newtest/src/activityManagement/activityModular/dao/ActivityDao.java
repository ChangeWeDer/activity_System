package activityManagement.activityModular.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import activityManagement.activityModular.entity.Activity;
import connection.DBConnection;

public class ActivityDao {
	public Activity queryById(Integer actId) {
		if (actId == null) {
			return null;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from activity where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, actId);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				return null;
			}
			return resultSet2Activity(rs);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Boolean saveOrUpdate(Activity act) {
		if (act == null) {
			return false;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnection.getConnection();
			String sql = null;
			if (act.getId() == null) {
				sql = "insert	into Activity(act_name,act_picture,sponsor,co_organizer,sign_up_start_date,sign_up_end_date, act_start_date,act_end_date,act_content) values(?,?,?,?,?,?,?,?,?)";
			} else {
				sql = "update	Activity	set act_name=?,act_picture=?,sponsor=?,co_organizer=?,sign_up_start_date=?,sign_up_end_date=?,act_start_date=?,act_end_date=?,act_content=? where id=?";
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, act.getActName());
			ps.setString(2, act.getActPicture());
			System.out.println("名字" + act.getActName());
			System.out.println("时间" + act.getSignUpStartDate());
			ps.setString(3, act.getSponsor());
			ps.setString(4, act.getCoOrganizer());
			ps.setTimestamp(5, new Timestamp(act.getSignUpStartDate().getTime()));
			ps.setTimestamp(6, new Timestamp(act.getSignUpEndDate().getTime()));
			ps.setTimestamp(7, new Timestamp(act.getActStartDate().getTime()));
			ps.setTimestamp(8, new Timestamp(act.getActEndDate().getTime()));
			ps.setString(9, act.getActContent());

			if (act.getId() != null) {
				ps.setInt(10, act.getId());
			}

			return ps.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		return false;
	}

	public List<Activity> queryAllList() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from Activity";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			Activity temp = null;
			List<Activity> actList = new ArrayList<Activity>();
			while (rs.next()) {
				temp = resultSet2Activity(rs);
				actList.add(temp);
			}
			return actList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<Activity> querySignUpList(Integer userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select act.* from Activity act " + "join activity_sign_up sign on act.id=sign.activity_id "
					+ "where sign.user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();

			Activity temp = null;
			List<Activity> actList = new ArrayList<Activity>();
			while (rs.next()) {
				temp = resultSet2Activity(rs);
				actList.add(temp);
			}
			return actList;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private Activity resultSet2Activity(ResultSet rs) {
		Activity act = new Activity();
		try {
			act.setId(rs.getInt("id"));
			act.setActName(rs.getString("act_name"));
			act.setActPicture(rs.getString("act_picture"));
			act.setSponsor(rs.getString("sponsor"));

			act.setCoOrganizer(rs.getString("co_organizer"));
			act.setSignUpStartDate(rs.getDate("sign_up_start_date"));
			act.setSignUpEndDate(rs.getDate("sign_up_end_date"));
			act.setActStartDate(rs.getDate("act_start_date"));
			act.setActEndDate(rs.getDate("act_end_date"));
			act.setActContent(rs.getString("act_content"));
			return act;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean signUp(Integer userId, Integer actId) {
		if (userId == null || actId == null) {
			return false;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		try {

			conn = DBConnection.getConnection();
			String sql = "insert into activity_sign_up(user_id,activity_id,sign_up_time)values(?,?,?)";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, actId);
			ps.setTimestamp(3, new Timestamp(new Date().getTime()));

			return ps.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public Boolean signIn(Integer userId, Integer actId) {
		if (userId == null || actId == null) {
			return false;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		try {

			conn = DBConnection.getConnection();
			String sql = "insert into activity_sign_in(user_id,activity_id,sign_in_time)values(?,?,?)";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, actId);
			ps.setTimestamp(3, new Timestamp(new Date().getTime()));

			return ps.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public Boolean isSignUp(Integer userId, Integer actId) {
		if (userId == null || actId == null) {
			return false;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from activity_sign_up where user_id=? and activity_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, actId);

			rs = ps.executeQuery();
			return rs.next();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Boolean isSignIn(Integer userId, Integer actId) {
		if (userId == null || actId == null) {
			return false;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from activity_sign_in where user_id=? and activity_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, actId);

			rs = ps.executeQuery();
			return rs.next();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Integer getSingUpNum(Integer actId) {
		if (actId == null) {
			return 0;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select count(*) from activity_sign_up where activity_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, actId);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public List<Map<String, String>> getSingUpListByActId(Integer actId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
			conn = DBConnection.getConnection();
			String sql = "select id,nickname,phone_num,sign_up_time " + "from activity_sign_up s "
					+ "join `user` u on s.user_id=u.id " + "where s.activity_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, actId);
			rs = ps.executeQuery();

			List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("nickname", rs.getString("nickname"));
				map.put("phone_num", rs.getString("phone_num"));
				map.put("sign_up_time", df.format(rs.getDate("sign_up_time")));
				map.put("id", rs.getString("id"));
				userList.add(map);
			}
			return userList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Boolean cancelSign(Integer userId, Integer actId) {
		if (userId == null || actId == null) {
			return false;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from activity_sign_up where user_id=? and activity_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, actId);

			return ps.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Boolean cancelSignIn(Integer userId, Integer actId) {
		if (userId == null || actId == null) {
			return false;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from activity_sign_in where user_id=? and activity_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, actId);

			return ps.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Boolean deletActivity(Integer actId) {
		if (actId == null) {
			return false;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from activity where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, actId);

			return ps.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public Boolean deletUser(Integer userId) {
		if (userId == null) {
			return false;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "delete from user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			return ps.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}