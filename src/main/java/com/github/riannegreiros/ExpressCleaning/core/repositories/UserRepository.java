package com.github.riannegreiros.ExpressCleaning.core.repositories;

import com.github.riannegreiros.ExpressCleaning.core.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByCpf(String cpf);

    Optional<User> findByPixKey(String pixKey);

    Page<User> findByCitiesServedIbgeCode(String ibgeCode, Pageable pageable);

    Boolean existsByCitiesServedIbgeCode(String ibgeCode);

    @Query("SELECT count(*) > 0 FROM User u WHERE u.email = :email and (:id is null or u.id != :id)")
    Boolean isEmailRegistered(String email, Long id);

    @Query(
            """
                    SELECT
                        AVG(u.reputation)
                    FROM
                        User u
                    WHERE
                        u.userType = com.github.riannegreiros.ExpressCleaning.core.enums.UserType.HOUSEKEEPER
                    """
    )
    Double getAverageReputationHousekeeper();

    default Boolean isEmailAlreadyRegistered(User user) {
        if (user.getEmail() == null) {
            return false;
        }

        return findByEmail(user.getEmail())
                .map(userFound -> !userFound.getId().equals(user.getId()))
                .orElse(false);
    }

    default Boolean isCpfIsAlreadyRegistered(User user) {
        if (user.getCpf() == null) {
            return false;
        }

        return findByCpf(user.getCpf())
                .map(userFound -> !userFound.getId().equals(user.getId()))
                .orElse(false);
    }

    default Boolean isPixKeyAlreadyRegistered(User user) {
        if (user.getPixKey() == null) {
            return false;
        }

        return findByPixKey(user.getPixKey())
                .map(userFound -> !userFound.getId().equals(user.getId()))
                .orElse(false);
    }

    boolean existsByEmail(String email);
}