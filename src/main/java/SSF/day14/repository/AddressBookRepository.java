package SSF.day14.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import SSF.day14.model.Contact;

@Repository
public class AddressBookRepository {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    public void save(final Contact ctc) {
        System.out.println(" ctc.getId() " + ctc.getId());
        redisTemplate.opsForList().leftPush("contactlist", ctc.getId());
        redisTemplate.opsForHash().put("addressbookmap", ctc.getId(), ctc);
        
    }

    public Contact findById(String contactId) {
        return (Contact)redisTemplate.opsForHash().get("addressbookmap", contactId);
    }

    public List<Contact> findAll(int startIndex) {
        List<Object> contactList = redisTemplate.opsForList().range("contactlist", 
            startIndex, 10);
        List<Contact> ctcs = redisTemplate.opsForHash()
            .multiGet("addressbookmap", contactList)
            .stream()
            .filter(Contact.class::isInstance)
            .map(Contact.class::cast)
            .toList();

            return ctcs;
    }

}
