/**
 * 
 */
package org.geek.pipe.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import org.geek.pipe.api.ResourceException;
import org.geek.pipe.api.optional.NeedRelease;


/**
 * @author haichuan
 * 2012-2-2
 */
public class DataBaseTransactionPolicy extends TransactionPolicy<Connection> implements NeedRelease {

	private Connection conn = null;
	
	@Override
	public void setInternalTran(Connection tran) {
		this.conn = tran;
	}

	@Override
	public void commintInternal()  throws TransactionalFailException{
		try {
			if(conn != null) conn.commit();
		} catch (SQLException e) {
			throw new TransactionalFailException(e);
		}
	}

	@Override
	public void rollbackInternal()  throws TransactionalFailException{
		try {
			if(conn != null) conn.rollback();
		} catch (SQLException e) {
			throw new TransactionalFailException(e);
		}
		
	}

	@Override
	public void release() throws ResourceException {
		try {
			if(conn != null) conn.close();
		} catch (SQLException e) {
			throw new ResourceException(e);
		}
	}
	
	
}
