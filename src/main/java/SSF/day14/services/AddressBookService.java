package SSF.day14.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SSF.day14.model.Contact;
import SSF.day14.repository.AddressBookRepository;


@Service
public class AddressBookService {
    @Autowired
    private AddressBookRepository addrbookRepo;

    public void saveContact (final Contact ctc) {
        addrbookRepo.save(ctc);
    }

    public Contact findContactById(String contactId) {
        return addrbookRepo.findById(contactId);
    }

    
    public List<Contact> getAllContact(int startIndex) {
        return addrbookRepo.findAll(startIndex);
    }

    
}
