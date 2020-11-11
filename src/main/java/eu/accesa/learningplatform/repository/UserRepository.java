package eu.accesa.learningplatform.repository;

import eu.accesa.learningplatform.model.dto.UserDto;
import eu.accesa.learningplatform.model.entity.UserEntity;
import eu.accesa.learningplatform.model.entity.UserTypeEnum;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {



    List<UserDto> findAllByUserTypeEntity_Name(UserTypeEnum userType);
}
