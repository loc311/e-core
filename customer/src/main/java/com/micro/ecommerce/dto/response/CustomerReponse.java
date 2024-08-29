package com.micro.ecommerce.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.micro.ecommerce.model.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReponse {

        private int user_id;
        private String fullName;
        private String email;

        public CustomerReponse(int user_id) {
                this.user_id = user_id;
        }
}
