package guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import guestbook.repository.GuestbookLogRepository;
import guestbook.repository.GuestbookRepository;
import guestbook.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private PlatformTransactionManager transactionManager;

	private final DataSource dataSource;
	private final GuestbookRepository guestbookRepository;
	private final GuestbookLogRepository guestbookLogRepository;

	public GuestbookService(GuestbookRepository guestbookRepository, GuestbookLogRepository guestbookLogRepository,
			DataSource dataSource) {
		this.guestbookRepository = guestbookRepository;
		this.guestbookLogRepository = guestbookLogRepository;
		this.dataSource = dataSource;

	}

	public List<GuestbookVo> getContentsList() {
		return guestbookRepository.findAll();
	}

	public void deleteContents(Long id, String password) {
		
		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			GuestbookVo vo = guestbookRepository.findById(id);
			if (vo == null) {
				return;
			}

			int count = guestbookRepository.deleteByIdAndPassword(id, password);
			if (count == 1) {
				guestbookLogRepository.update(vo.getRegDate());
			}
			
			transactionManager.commit(txStatus);
		} catch (Throwable ex) {
			transactionManager.rollback(txStatus);
			
			throw new RuntimeException(ex);
		}
		
	}

	public void addContents(GuestbookVo vo) {
		// 트랜잭션 동기화

		TransactionSynchronizationManager.initSynchronization();
		Connection conn = DataSourceUtils.getConnection(dataSource);

		try {
			// TX : begin
			conn.setAutoCommit(false);
			int count = guestbookLogRepository.update();

			if (count == 0) {
				guestbookLogRepository.insert();
			}
			guestbookRepository.insert(vo);
			// TX : end (success)
			conn.commit();

		}

		catch (SQLException e) {
			// TX : end (fail)
			try {
				conn.rollback();
			} catch (SQLException ignore) {
				// TODO: handle exception
			}
		} finally {
			DataSourceUtils.releaseConnection(conn, dataSource);

		}
	}
}
