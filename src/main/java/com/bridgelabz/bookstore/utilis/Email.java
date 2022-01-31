package com.bridgelabz.bookstore.utilis;
        import lombok.Data;
        import org.springframework.stereotype.Component;

        import java.io.Serializable;
@Data
@Component
public class Email implements Serializable {
    String to;
    String from;
    String subject;
    String body;
}