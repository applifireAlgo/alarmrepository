package com.app.server.repository.salesboundedcontext.sales;
import com.athena.server.repository.SearchInterface;
import com.athena.annotation.Complexity;
import com.athena.annotation.SourceCodeAuthorClass;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import java.util.List;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "", versionNumber = "1", comments = "Repository for SalesData Transaction table", complexity = Complexity.MEDIUM)
public interface SalesDataRepository<T> extends SearchInterface {

    public List<T> findAll() throws SpartanPersistenceException;

    public T save(T entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public List<T> save(List<T> entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public void delete(Integer id) throws SpartanPersistenceException;

    public void update(T entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public void update(List<T> entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public List<T> findByChannelId(String channelId) throws Exception, SpartanPersistenceException;

    public List<T> findByReatilercode(String reatilercode) throws Exception, SpartanPersistenceException;

    public List<T> findByMaterialcode(String materialcode) throws Exception, SpartanPersistenceException;

    public List<T> findByBrandcode(String brandcode) throws Exception, SpartanPersistenceException;

    public List<T> findByCategory(String category) throws Exception, SpartanPersistenceException;

    public T findById(Integer autoid) throws Exception, SpartanPersistenceException;
}
