package activityManagement.userModular.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import activityManagement.userModular.entity.User;
import connection.DBConnection;

public class UserDao {
	public User queryByUserId(Integer userId) {
		if (userId == null) {
			return null;
		}

		Connection conn = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from user where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				return null;
			}
			return resultSet2User(rs);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public User queryByUserName(String userName) {
		if (userName == null) {
			return null;
		}
		System.out.printf(userName);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from user where user_name = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, userName);
			rs = ps.executeQuery();
			if (rs.next() == false) {
				return null;
			}
			return resultSet2User(rs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private User resultSet2User(ResultSet rs) {
		User user = new User();
		try {
			user.setId(rs.getInt("id"));
			user.setUserName(rs.getString("user_name"));
			user.setPassword(rs.getString("password"));
			user.setNickname(rs.getString("nickname"));
			user.setRegisterDate(rs.getDate("register_date"));
			user.setSex(rs.getString("sex"));
			user.setPhoneNum(rs.getString("phone_num"));
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
		return null;

	}

	public Boolean save(User user) {
		if (user == null) {
			return false;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "insert	into user(user_name,nickname,password,register_date,sex,phone_num) values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getNickname());
			ps.setString(3, user.getPassword());
			ps.setTimestamp(4, new Timestamp(user.getRegisterDate().getTime()));
			ps.setString(5, user.getSex());
			ps.setString(6, user.getPhoneNum());

			return ps.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public List<User> queryAllList() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select * from user";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			User userTemp = null;
			List<User> userList = new ArrayList<User>();
			while (rs.next()) {
				userTemp = resultSet2User(rs);
				userList.add(userTemp);
			}
			return userList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Boolean update(User user) {
		if (user == null) {
			return false;
		}

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DBConnection.getConnection();
			String sql = "update	user	set user_name=?,nickname=?,password=?,sex=?,phone_num=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getNickname());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getSex());
			ps.setString(5, user.getPhoneNum());
			ps.setInt(6, user.getId());

			return ps.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
