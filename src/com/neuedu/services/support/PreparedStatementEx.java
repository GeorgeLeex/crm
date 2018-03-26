package com.neuedu.services.support;

import com.neuedu.system.db.DBUtils;

import java.sql.PreparedStatement;

class PreparedStatementEx {
	/**
	 * PreparedStatement对象,编译好的赋好值的
	 */
    private PreparedStatement pstmt;
    /**
     * 是否为批处理的判断标记
     */
    private boolean isBatch;

    public PreparedStatementEx(PreparedStatement pstmt, boolean isBatch) {
        this.pstmt = pstmt;
        this.isBatch = isBatch;
    }

    /**
     * 根据isBatch执行PreparedStatement的executeBatch方法或executeUpdate方法
     * @throws Exception
     */
    public void executeTransaction() throws Exception {
        if (this.isBatch) {
            this.pstmt.executeBatch();
        } else {
            this.pstmt.executeUpdate();
        }
    }

    /**
     * 关闭PreparedStatement对象
     */
    public void closePreparedStatement() {
        DBUtils.Close(this.pstmt);
    }
}
