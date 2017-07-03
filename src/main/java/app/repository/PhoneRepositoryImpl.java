package app.repository;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.controller.ContactController;
import app.model.Contact;

@Repository
public class PhoneRepositoryImpl implements PhoneRepository {
	@PersistenceContext
    private EntityManager em;
	
	private static final Logger log = LoggerFactory.getLogger(PhoneRepositoryImpl.class);
	
	@Transactional
	public void customDelete(Long id) {
		log.error("Shobhit: inside method");
		Query query = em.createQuery("select phone_id, contact_id from contact_phone where phone_id = ?1");
		query.setParameter(1, id);
        List<ABC> resultList = query.getResultList();
        log.error("Shobhit: inside method2");
        if(resultList.size()==0) {
        	log.error("Shobhit: inside method3");
        	Query query2 =em.createQuery("Delete from phones where phone_id = ?1");
    		query2.setParameter(1, id);
            int resultList2 = query2.executeUpdate();
            log.error("Shobhit: inside method4");
        }
	}
	
	public class ABC{
		private Long phone_id;
		private Long contact_id;
		public Long getPhone_id() {
			return phone_id;
		}
		public void setPhone_id(Long phone_id) {
			this.phone_id = phone_id;
		}
		public Long getContact_id() {
			return contact_id;
		}
		public void setContact_id(Long contact_id) {
			this.contact_id = contact_id;
		}
	}

	@Override
	public <S extends Contact> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Contact> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Contact> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Contact> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Contact entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Contact> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
}
