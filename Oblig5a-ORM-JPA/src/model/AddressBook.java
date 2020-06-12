package model;

import java.util.Collection;
import java.util.Iterator;

/**
 * AddressBook grensesnitt som representerer en adressebook. Alle metodene er implisitt offentlige og
 * abstrakte.
 */

public interface AddressBook {
    boolean addContact(ContactDetails contact);
    boolean removeContact(String phoneNumber);
    Collection<ContactDetails> getAllContacts();
    Iterator<ContactDetails> iterator();
    void close();
}
