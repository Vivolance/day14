package SSF.day14.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import SSF.day14.model.Contact;
import SSF.day14.services.AddressBookService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class AddressBookController {
    @Autowired
    private AddressBookService addrbkSvc;

    @GetMapping("/")
    public String showContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contactform";
        
    }

    @PostMapping(path="/contact")
    public String saveContact(@Valid Contact contact, 
        BindingResult result, Model model, HttpServletResponse response) {

        if (result.hasErrors()) {
            return "contactform";
        }

        addrbkSvc.saveContact(contact);
        model.addAttribute("contact", contact);
        //To overwrite the status code
        response.setStatus(HttpServletResponse.SC_CREATED);

        return "contact";

    }

    @GetMapping("/contact")
    public String getAllContact(Model model, @RequestParam(name="startIndex") Integer startIndex) {
        List<Contact> result= addrbkSvc.getAllContact(startIndex);
        model.addAttribute("contacts", result);
        return "list";

    }

    @GetMapping(path="/contact/{contactId}")
    public String getContactDetails(Model model, @PathVariable(value="contactId") String contactId) {
        Contact ctc = addrbkSvc.findContactById(contactId);
        System.out.println(ctc.getId());
        //ctc.setId(contactId);
        model.addAttribute("contact", ctc);
        return "contact";
    }
    
}
