package com.github.riannegreiros.ExpressCleaning.core.specifications;

import com.github.riannegreiros.ExpressCleaning.core.enums.DailyStatus;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily;
import com.github.riannegreiros.ExpressCleaning.core.models.Daily_;
import com.github.riannegreiros.ExpressCleaning.core.models.User_;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public class DailySpecification {
    public static Specification<Daily> withCandidatesNumberEqualsTo(int candidatesNum) {
        return (root, query, criteriaBuilder) -> {
            var candidatesQuantity = criteriaBuilder.size(root.get(Daily_.candidates));
            return criteriaBuilder.equal(candidatesQuantity, candidatesNum);
        };
    }

    public static Specification<Daily> withCandidatesNumberLessThan(int candidatesNum) {
        return (root, query, criteriaBuilder) -> {
            var candidatesQuantity = criteriaBuilder.size(root.get(Daily_.candidates));
            return criteriaBuilder.lessThan(candidatesQuantity, candidatesNum);
        };
    }

    public static Specification<Daily> withCandidatesNumberBiggerOrEqualsTo(int candidatesNum) {
        return (root, query, criteriaBuilder) -> {
            var candidatesQuantity = criteriaBuilder.size(root.get(Daily_.candidates));
            return criteriaBuilder.greaterThanOrEqualTo(candidatesQuantity, candidatesNum);
        };
    }

    public static Specification<Daily> isPaid() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Daily_.status), DailyStatus.PAID);
        };
    }

    public static Specification<Daily> isWithoutPayment() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Daily_.status), DailyStatus.NO_PAYMENT);
        };
    }

    public static Specification<Daily> withoutCleaner() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isNull(root.get(Daily_.cleaner));
        };
    }

    public static Specification<Daily> withMore24HoursSinceCreation() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(Daily_.createdAt),
                    LocalDateTime.now().minusHours(24)
            );
        };
    }

    public static Specification<Daily> withoutCandidates() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isEmpty(root.get(Daily_.candidates));
        };
    }

    public static Specification<Daily> withFewer24HoursForService() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(
                    root.get(Daily_.attendanceDate),
                    LocalDateTime.now().plusHours(24)
            );
        };
    }

    public static Specification<Daily> clientFullNameContains(String searchValue) {
        return (root, query, criteriaBuilder) -> {
            if (searchValue == null || searchValue.isEmpty()) {
                return criteriaBuilder.and();
            }
            var client = root.join(Daily_.client, JoinType.LEFT);
            var fullNameSmallCase = criteriaBuilder.lower(client.get(User_.fullName));
            return criteriaBuilder.like(fullNameSmallCase, "%" + searchValue.toLowerCase() + "%");
        };
    }

    public static Specification<Daily> statusIn(List<DailyStatus> status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null || status.isEmpty()) {
                return criteriaBuilder.and();
            }
            return root.get(Daily_.status).in(status);
        };
    }
}
