package com.github.riannegreiros.ExpressCleaning.core.models;

import com.github.riannegreiros.ExpressCleaning.core.enums.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Payment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "daily_rate", nullable = false)
    private DailyRate dailyRate;

    public boolean isAccept() {
        return status.equals(PaymentStatus.ACCEPTED);
    }
}
