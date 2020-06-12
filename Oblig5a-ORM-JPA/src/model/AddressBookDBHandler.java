
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.*;

public class AddressBookDBHandler implements AddressBook, Serializable {
    private static final long serialVersionUID = 1L;
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("contacts-pu-mysql");

    public AddressBookDBHandler() {
    }

    public EntityManager createEM() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    private void closeEM(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    @Override
    public boolean addContact(ContactDetails contact) {
        boolean isSuccess = false;
        EntityManager em = createEM();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(contact);
            et.commit();
            isSuccess = true;
        } catch (Exception e) {
            if (et != null)
                et.rollback();
        } finally {
            closeEM(em);
            return isSuccess;
        }
    }

    @Override
    public boolean removeContact(String phoneNumber) {
        boolean isSuccess = false;
        EntityManager em = createEM();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            String query = "SELECT c FROM ContactDetails c WHERE c.phone = :phoneNumber";
            ContactDetails contact = em.createQuery(query, ContactDetails.class)
                                        .setParameter("phoneNumber", phoneNumber)
                                        .getSingleResult();

            em.remove(contact);
            et.commit();
        } catch(Exception e) {
            if(et != null)
                et.rollback();
        } finally {
            closeEM(em);
            return isSuccess;
        }
    }

    public boolean removeContact(ContactDetails contact) {
        boolean isSuccess = false;
        EntityManager em = createEM();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            if(!em.contains(contact)) {
                contact = em.merge(contact);
            }
            em.remove(contact);
            et.commit();
            isSuccess = true;
        } catch(Exception e) {
            if(et != null)
                et.rollback();
        } finally {
            closeEM(em);
            return isSuccess;
        }
    }

    public boolean removeContact2(String phoneNumber) {
        boolean isSuccess = false;
        EntityManager em = createEM();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            ContactDetails contact = em.find(ContactDetails.class, phoneNumber);
            em.remove(contact);
            et.commit();
        } catch(Exception e) {
            if(et != null) {
                et.rollback();
            }
        } finally {
            closeEM(em);
            return isSuccess;
        }
    }

    public boolean editContact(String newName, String newPhone, String newAdress, String phoneNumber) {
        boolean isSuccess = false;
        EntityManager em = createEM();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            String query = "SELECT c FROM ContactDetails c WHERE c.phone = :phoneNumber";
            //ContactDetails contact = em.find(ContactDetails.class, phoneNumber);
            ContactDetails contact = em.createQuery(query, ContactDetails.class)
                    .setParameter("phoneNumber", phoneNumber)
                    .getSingleResult();
            contact.setName(newName);
            contact.setPhone(newPhone);
            contact.setAddress(newAdress);
            em.persist(contact);
            et.commit();
        } catch(Exception e) {
            if(et != null) {
                et.rollback();
            }
        } finally {
            closeEM(em);
            return isSuccess;
        }

    }


    @Override
    public Collection<ContactDetails> getAllContacts() {
        EntityManager em = createEM();
        String query = "SELECT c FROM ContactDetails c WHERE c.phone IS NOT NULL";
        TypedQuery<ContactDetails> tq = em.createQuery(query, ContactDetails.class);
        try {
            return tq.getResultList();
        } catch(NoResultException e) {
            e.printStackTrace();
        } finally {
            closeEM(em);
        }
        return null;
    }

    public Collection<ContactDetails> getAllContacts2() {
        EntityManager em = createEM();
        try {
            Query query = em.createQuery("SELECT Object(c) from ContactDetails c", ContactDetails.class);
            return query.getResultList();
        } finally {
            closeEM(em);
        }
    }

    @Override
    public Iterator<ContactDetails> iterator() {
        return getAllContacts().iterator();
    }

    @Override
    public void close() {
        if (ENTITY_MANAGER_FACTORY.isOpen() && ENTITY_MANAGER_FACTORY != null) {
            ENTITY_MANAGER_FACTORY.close();
        }
    }
}




