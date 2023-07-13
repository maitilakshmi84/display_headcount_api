package co.display_headcount.portal.dao.admin;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.display_headcount.portal.model.admin.HeadCount;



@Repository
public interface HeadCountRepository  extends JpaRepository<HeadCount, Long> {

	/*
	 * This methd fetch List as per
	 * nothing specified
	 */
	@Query("SELECT c FROM HeadCount c ")
	public List<HeadCount> getAllHeadCountData() throws Exception;
}

