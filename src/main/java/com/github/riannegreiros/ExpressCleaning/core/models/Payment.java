package com.github.riannegreiros.ExpressCleaning.core.models;

import com.github.riannegreiros.ExpressCleaning.core.enums.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Payment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "daily_id", nullable = false)
    private Daily daily;

    public boolean isAccept() {
        return status.equals(PaymentStatus.ACCEPTED);
    }

    public Payment() {
    }

    public Payment(Long id, PaymentStatus status, BigDecimal value, String transactionId, Daily daily) {
        this.id = id;
        this.status = status;
        this.value = value;
        this.transactionId = transactionId;
        this.daily = daily;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Daily getDailyRate() {
        return daily;
    }

    public void setDailyRate(Daily daily) {
        this.daily = daily;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                '}';
    }

    public static class PaymentBuilder {
        private Long id;
        private PaymentStatus status;
        private BigDecimal valor;
        private String transactionId;
        private Daily daily;

        private PaymentBuilder() {
        }

        public static PaymentBuilder builder() {
            return new PaymentBuilder();
        }

        public PaymentBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PaymentBuilder status(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public PaymentBuilder valor(BigDecimal valor) {
            this.valor = valor;
            return this;
        }

        public PaymentBuilder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public PaymentBuilder dailyRate(Daily daily) {
            this.daily = daily;
            return this;
        }

        public Payment build() {
            Payment payment = new Payment();
            payment.setId(id);
            payment.setStatus(status);
            payment.setValue(valor);
            payment.setTransactionId(transactionId);
            payment.setDailyRate(daily);
            return payment;
        }
    }
}
