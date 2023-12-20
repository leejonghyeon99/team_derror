package com.example.demo.domain.event;

import com.example.demo.domain.user.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventData {
    private Long id;
    private Long member_id;
    private String master_id;
    private String title;
    private String national;
    private String created_date;
}
