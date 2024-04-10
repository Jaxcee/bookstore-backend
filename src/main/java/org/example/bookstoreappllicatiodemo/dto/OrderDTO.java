
package org.example.bookstoreappllicatiodemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
   public class OrderDTO {


        private Long cartId;
        private String address;
    }



