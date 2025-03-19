package org.api.dto.responseDTO;


import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {

    private String username;
    private String avatar;

//    public AuthorDTO() {
//    }
//
//    public AuthorDTO(String username) {
//        this.username = username;
//    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }
//
//
//    @Override
//    public String toString() {
//        return "AuthorDTO{" +
//                "username='" + username + '\'' +
//                ", avatar='" + avatar + '\'' +
//                '}';
//    }
}
