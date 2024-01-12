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
    private BigDecimal valor;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "daily_rate_id", nullable = false)
    private DailyRate dailyRate;

    public boolean isAccept() {
        return status.equals(PaymentStatus.ACCEPTED);
    }

    public Payment() {
    }

    public Payment(Long id, PaymentStatus status, BigDecimal valor, String transactionId, DailyRate dailyRate) {
        this.id = id;
        this.status = status;
        this.valor = valor;
        this.transactionId = transactionId;
        this.dailyRate = dailyRate;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public DailyRate getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(DailyRate dailyRate) {
        this.dailyRate = dailyRate;
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
        private DailyRate dailyRate;

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

        public PaymentBuilder dailyRate(DailyRate dailyRate) {
            this.dailyRate = dailyRate;
            return this;
        }

        public Payment build() {
            Payment payment = new Payment();
            payment.setId(id);
            payment.setStatus(status);
            payment.setValor(valor);
            payment.setTransactionId(transactionId);
            payment.setDailyRate(dailyRate);
            return payment;
        }
    }
}
