package org.example.bookstoreappllicatiodemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
    private Long bookId;
    private String bookName;
    private String authorName;
    private String bookLogo;  // URL to the book image
    private Long bookPrice;
}
