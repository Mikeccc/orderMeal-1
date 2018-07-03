package app.cn.qtt.bm.scheduler.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBManager {

	private String dirverName = "com.mysql.jdbc.Driver"; // JDBC驱动

	private String URL = "jdbc:mysql://localhost:3306/caiman"; // 数据库连接URL

	private String userName = "root"; // 用户名

	private String password = "root";// 密码

	private Connection conn = null; // 数据库连接

	private Statement stmt = null;

	private PreparedStatement prepstmt = null;

	/**
	 * 构造方法_初始化连接
	 * 
	 */
	public DBManager() {
		try {
			getDataSource();
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.print("conn.createStatement()发生错误!");
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接
	 * 
	 */
	public void getDataSource() {
		try {
			// 建议：使用连接池通过JNDI获取连接

			Class.forName(dirverName);
			conn = DriverManager.getConnection(URL, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得数据库连接
	 * 
	 * @return Connection
	 * @throws GeneralException
	 */
	public Connection getConnection() throws Exception {
		if (conn == null) {
			getDataSource();
		}
		return conn;
	}

	/**
	 * 根据指定连接创建Statement
	 * 
	 * @param conn
	 *            Connection 数据库连接
	 * @return Statement 得到的Statement
	 * @throws SQLException
	 */
	public Statement getStatement(Connection conn) throws Exception {
		Statement stmt = null;
		if (conn != null) {
			try {
				stmt = conn.createStatement();
			} catch (SQLException ex) {
				throw ex;
			}
		}
		return stmt;
	}

	/**
	 * 根据指定连接创建PreparedStatement
	 * 
	 * @param conn
	 *            Connection 数据库连接
	 * @param sql
	 *            String SQL语句
	 * @return PreparedStatement 得到的PreparedStatement
	 * @throws SQLException
	 */
	public PreparedStatement getPrepareStatement(Connection conn, String sql) throws Exception {
		PreparedStatement pstmt = null;
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
			} catch (SQLException ex) {
				throw ex;
			}
		}
		return pstmt;
	}

	/**
	 * 查询数据
	 * 
	 * @param conn
	 *            Connection 数据库连接
	 * @param sql
	 *            String 查询语句
	 * @return ResultSet 查询得到的结果集
	 * @throws SQLException
	 */
	public ResultSet query(Connection conn, String sql) throws Exception {
		if (conn == null) {
			return null;
		}

		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = this.getStatement(conn);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			throw ex;
		}
		return rs;
	}

	/**
	 * 更新数据记录
	 * 
	 * @param conn
	 *            Connection 数据库连接
	 * @param sql
	 *            String 查询语句
	 * @return int 更新的记录数
	 * @throws UniversalException
	 */
	public int update(Connection conn, String sql) throws Exception {
		if (conn == null)
			return -1;
		int ret = -1;
		Statement stmt = null;
		try {
			stmt = this.getStatement(conn);
			ret = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			throw ex;
		} finally {
			this.closeStatement(stmt);
		}
		return ret;
	}

	/**
	 * 批量更新
	 * 
	 * @param conn
	 *            Connection 数据库连接
	 * @param sqlList
	 *            List 要执行的SQL语列表
	 * @return int[] 每个SQL执行后更新的记录数
	 * @throws SQLException
	 */
	public int[] updateBatch(Connection conn, List sqlList) throws Exception {
		if (sqlList == null || conn == null) {
			return null;
		}

		int[] ret = null;
		Statement stmt = null;
		try {
			if (sqlList.size() > 0) {
				stmt = this.getStatement(conn);
				for (int ii = 0; ii < sqlList.size(); ii++) {
					stmt.addBatch((String) sqlList.get(ii));
				}
				ret = stmt.executeBatch();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			this.closeStatement(stmt);
		}
		return ret;
	}

	/**
	 * 关闭数据结果集
	 * 
	 * @param rs
	 *            ResultSet
	 */
	public void closeResultSet(ResultSet rs) {
		Statement stmt = null;
		if (rs != null) {
			try {
				stmt = rs.getStatement();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			;
			try {
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		rs = null;
		stmt = null;
	}

	/**
	 * 关闭Statement
	 * 
	 * @param stmt
	 *            Statement
	 */
	public void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		stmt = null;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 *            Connection
	 */
	public void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		conn = null;
	}

	/**
	 * 关闭数据库对象
	 * 
	 * @throws SQLException
	 */
	public void close() {
		try {
			if (prepstmt != null) {
				prepstmt.close();
				prepstmt = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
