package SSF.day14.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


public class Contact implements Serializable{

    @NotNull(message="Name cannot be null")
    @Size(min=3, max=64, message="Name must be between 3 to 64 chars")
    private String name;

    @Email(message="Invalid email")
    private String email;

    @Size(min=7, message="Phone number must be at least 7 digits")
    private String phoneNo;

    private String id;

    @Past(message="Date of birth must not be future")
    @NotNull(message="Date of birth is mandatory")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate birthday;

    @Min(value=10, message="Must be above 10 years old")
    @Max(value=100, message="Must be below 100 years old")
    private int age;

    public Contact() {
        this.id = generateId(8);
    
    }

    public Contact(String name, String email, String phoneNo, LocalDate birthday) {
        this.id = generateId(8);
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.birthday = birthday;

    }

    public Contact(String id, String name, String email, String phoneNo, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.birthday = birthday;

    }
    // 
    public synchronized String generateId(int numOfChars) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while(sb.length() < numOfChars) {
            sb.append(Integer.toHexString(r.nextInt()));

        }
        return sb.toString().substring(0, numOfChars);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        int calculateAge = 0;
        if (null != birthday) {
            calculateAge = Period.between(birthday, LocalDate.now()).getYears();
        }

        this.birthday = birthday;
        this.age = calculateAge;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    
}
