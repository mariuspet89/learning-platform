package eu.accesa.learningplatform.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserDto {
    private Long id;
    @NotNull(message = "First name can't be null")
    private String firstName;
    @NotNull(message = "Last name can't be null")
    private String lastName;
    @Email(message = "Must be a valid email")
    @NotNull(message = "Email can't be null")
    private String email;
    private String imageUrl;
    @NotNull(message = "Password can't be null")
    private String password;
    @NotNull
    private Long competenceAreaId;
    @NotNull
    private Long jobTitleId;
    @NotNull
    private Long userTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCompetenceAreaId() {
        return competenceAreaId;
    }

    public void setCompetenceAreaId(Long competenceAreaId) {
        this.competenceAreaId = competenceAreaId;
    }

    public Long getJobTitleId() {
        return jobTitleId;
    }

    public void setJobTitleId(Long jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", password='" + password + '\'' +
                ", competenceAreaId=" + competenceAreaId +
                ", jobTitleId=" + jobTitleId +
                ", userTypeId=" + userTypeId +
                '}';
    }
}
