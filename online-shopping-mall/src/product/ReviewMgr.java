package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class ReviewMgr {

	private DBConnectionMgr pool;
	private static final String UPLOAD = "C:/Jsp/online-shopping-mall-jsp/webContent/img/review/";
	private static final String ENCTYPE = "EUC-KR";
	private static final int MAXSIZE = 10*1024*1024;

	public ReviewMgr() {
		pool = DBConnectionMgr.getInstance();
	}
	
	/* ���� �ۼ� ������ �ֹ� �� �������� */
	public Vector <ReviewBean> getAvaReview(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector <ReviewBean> vlist = new Vector<ReviewBean>();
		
		try {
			con = pool.getConnection();
			sql = 	"SELECT od.o_index, p.p_main_pht_name, p.p_name, o.o_date, o.o_status, p.p_code " + 
					"FROM order_detail_tb od JOIN order_tb o ON od.o_index=o.o_index " + 
					"					     JOIN product_mst_tb p on od.p_code=p.p_code " + 
					"					     LEFT JOIN review_tb r ON od.o_index = r.o_index AND od.p_code = r.p_code " + 
					"WHERE CURDATE()-o.o_date<32 AND o.o_id=? AND r.r_content IS NULL ORDER BY o.o_date DESC;"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewBean reBean = new ReviewBean();
				reBean.setO_index(rs.getInt(1));
				reBean.setP_main_pht_name(rs.getString(2));
				reBean.setP_name(rs.getString(3));
				reBean.setO_date(rs.getString(4));
				reBean.setO_status(rs.getString(5));
				reBean.setP_code(rs.getInt(6));
				vlist.addElement(reBean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	/* get Review by Id */
	public Vector <ReviewBean> getReviewsById(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector <ReviewBean> vlist = new Vector<ReviewBean>();
		
		try {
			con = pool.getConnection();
			sql = 	"SELECT r.o_index, t.p_name, t.p_code, r_rate, r_content, r.id, o.o_date, r_pht_name, r_pht_size "
				  + "FROM review_tb r JOIN product_mst_tb t ON r.p_code=t.p_code "
				  + "JOIN order_tb o ON r.o_index=o.o_index "
				  + "WHERE r.id = ? ORDER BY o.o_date DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewBean reBean = new ReviewBean();
				reBean.setO_index(rs.getInt(1));
				reBean.setP_name(rs.getString(2));
				reBean.setP_code(rs.getInt(3));
				reBean.setR_rate(rs.getInt(4));
				reBean.setR_content(rs.getString(5));
				reBean.setId(rs.getString(6));
				reBean.setO_date(rs.getString(7));
				reBean.setR_pht_name(rs.getString(8));
				reBean.setR_pht_size(rs.getInt(9));
				vlist.addElement(reBean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	/* get Review by p_code */
	public Vector <ReviewBean> getReviewsByPCode(int pCode){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector <ReviewBean> vlist = new Vector<ReviewBean>();
		
		try {
			con = pool.getConnection();
			sql = 	"SELECT r.o_index, t.p_name, t.p_code, r_rate, r_content, r.id, o.o_date, r_pht_name, r_pht_size "
				  + "FROM review_tb r JOIN product_mst_tb t ON r.p_code=t.p_code "
				  + "JOIN order_tb o ON r.o_index=o.o_index "
				  + "WHERE t.p_code = ? ORDER BY o.o_date DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pCode);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewBean reBean = new ReviewBean();
				reBean.setO_index(rs.getInt(1));
				reBean.setP_name(rs.getString(2));
				reBean.setP_code(rs.getInt(3));
				reBean.setR_rate(rs.getInt(4));
				reBean.setR_content(rs.getString(5));
				reBean.setId(rs.getString(6));
				reBean.setO_date(rs.getString(7));
				reBean.setR_pht_name(rs.getString(8));
				reBean.setR_pht_size(rs.getInt(9));
				vlist.addElement(reBean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	/* insert Review */
	public boolean insertReview(ReviewBean reBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "INSERT into review_tb (o_index, p_code, id, r_content, r_rate) " + 
				  "VALUES (?, ?, ?, ?, ?);";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reBean.getO_index());
			pstmt.setInt(2, reBean.getP_code());
			pstmt.setString(3, reBean.getId());
			pstmt.setString(4, reBean.getR_content());
			pstmt.setInt(5, reBean.getR_rate());
			int count = pstmt.executeUpdate();
			if(count==1) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
}
